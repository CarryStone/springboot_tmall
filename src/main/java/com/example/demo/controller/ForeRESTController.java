package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import com.example.demo.comparator.ProductAllComparator;
import com.example.demo.comparator.ProductDateComparator;
import com.example.demo.comparator.ProductPriceComparator;
import com.example.demo.comparator.ProductReviewComparator;
import com.example.demo.comparator.ProductSaleCountComparator;
import com.example.demo.pojo.Category;
import com.example.demo.pojo.Order;
import com.example.demo.pojo.OrderItem;
import com.example.demo.pojo.Product;
import com.example.demo.pojo.ProductImage;
import com.example.demo.pojo.PropertyValue;
import com.example.demo.pojo.Review;
import com.example.demo.pojo.User;
import com.example.demo.service.CategoryService;
import com.example.demo.service.OrderItemService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductImageService;
import com.example.demo.service.ProductService;
import com.example.demo.service.PropertyValueService;
import com.example.demo.service.ReviewService;
import com.example.demo.service.UserService;
import com.example.demo.util.Constants;
import com.example.demo.util.Result;

@RestController
public class ForeRESTController {

	@Autowired
	private CategoryService categoryservice;
	@Autowired
	private UserService userservice;
	@Autowired
	private ProductService productservice;
	@Autowired
	private ReviewService reviewservice;
	@Autowired
    private PropertyValueService propertyvalueservice;
	@Autowired
	private ProductImageService productimageservice;
	@Autowired
	private OrderItemService orderitemservice;
	@Autowired
	private OrderService orderservice;
	
	/**
	 * 首页
	 * @return
	 * @throws Exception
	 */
	@GetMapping("forehome")
	public List<Category> list() throws Exception {
		List<Category> listCategory = categoryservice.getCategoryList();
		categoryservice.fill(listCategory);
		categoryservice.fillByRow(listCategory);
		return listCategory;
	}
	
	/**
	 * 注册
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@PostMapping("foreregister")
	public Object register(@RequestBody User user) throws Exception {	
		String name = HtmlUtils.htmlEscape(user.getName());
		user.setName(name);
		User users = userservice.findUserByName(name);
		if(users!=null) {
			String message = "该昵称已存在，请重新注册！";
			return new Result(Result.FAILURE_CODE,message);
		}
		userservice.addUsers(user);
		return new Result(Result.SUCCESS_CODE,null);
	}
	
	
	
	/**
	 * 登录
	 * @param user
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@PostMapping("forelogin")
	public Object login(@RequestBody User user,HttpSession session) throws Exception {	
		String name = HtmlUtils.htmlEscape(user.getName());	
		User users = userservice.get(name,user.getPassword());
		if(users==null) {		
			return new Result(Result.FAILURE_CODE,"用户名或密码错误");
		}
		session.setAttribute("user", users);
		return new Result(Result.SUCCESS_CODE,null);
	}
	
	
    //校验是否登录
	@GetMapping("forecheckLogin")
	public Object checkLogin( HttpSession session) {
		User user = (User)session.getAttribute("user");
		if(user==null) {
			return new Result(Result.FAILURE_CODE,"未登录");
		}
		return new Result(Result.SUCCESS_CODE,null);
	}
	
	/**
	 * 产品页
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	@GetMapping("foreproduct/{pid}")
	public Map<String,Object>  product(@PathVariable("pid") int pid) throws Exception {	
		Product product = productservice.findProductById(pid);
		//获取销量和评价数量
		productservice.SetSaleCountAndReviewNumber(product);
		//获取评价详情
		List<Review> reviews = reviewservice.list(product);
		//获取商品属性详情
		List<PropertyValue> pvs = propertyvalueservice.getByProduct(product);
		//设置第一个图片
		productimageservice.setFirstProdutImage(product);
		//查找单个图片
		List<ProductImage> productSingleImages = productimageservice.findProductImageByProduct(product, "single");
		product.setProductSingleImages(productSingleImages);
		List<ProductImage> productDetailImages = productimageservice.findProductImageByProduct(product, "detail");
		product.setProductDetailImages(productDetailImages);

		Map<String,Object> map= new HashMap<>();
		map.put("product", product);
        map.put("pvs", pvs);
        map.put("reviews", reviews);
        
        return map;
	}
	
	/**
	 * 分类页
	 * @param cid
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	@GetMapping("forecategory/{cid}")
	public Object category(@PathVariable int cid,String sort) throws Exception {
		Category c = categoryservice.get(cid);
		categoryservice.fill(c);
		productservice.SetSaleCountAndReviewNumber(c.getProducts());
		
		//集合排序
		if(null!=sort){
			switch (sort) {
			case "review":
				Collections.sort(c.getProducts(), new ProductReviewComparator());
				break;
				
			case "date" :
                Collections.sort(c.getProducts(),new ProductDateComparator());
                break;
 
            case "saleCount" :
                Collections.sort(c.getProducts(),new ProductSaleCountComparator());
                break;
 
            case "price":
                Collections.sort(c.getProducts(),new ProductPriceComparator());
                break;
 
            case "all":
                Collections.sort(c.getProducts(),new ProductAllComparator());
                break;			
			}
		}
		return c;
	}
	
	/**
	 * 搜索
	 * @param keyword
	 * @return
	 * @throws Exception
	 */
	@GetMapping("foresearch")
	public List<Product> search(String keyword) throws Exception {
		if(null==keyword)
	        keyword = "";
		List<Product> ps= productservice.search(keyword,0,20);
		productimageservice.setFirstProdutImages(ps);
		productservice.SetSaleCountAndReviewNumber(ps);
		return ps;
	}
	
	//加入购物车
	@GetMapping("foreaddCart")
    public Object addCart(int pid, int num, HttpSession session) throws Exception{
		buyoneAndAddCart(pid,num,session);
		return Result.SUCCESS_CODE;
    }
	
	
	//立即购买
	@GetMapping("forebuyone")
    public Object buyone(int pid, int num, HttpSession session) throws Exception{
        return buyoneAndAddCart(pid,num,session);
    }
		
	/**
	 * 生成或者更新订单项	
	 * @param pid
	 * @param num
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public int buyoneAndAddCart(int pid, int num, HttpSession session) throws Exception{
		Product product = productservice.findProductById(pid);
	    int oiid = 0;
	 
	    User user =(User)  session.getAttribute("user");
	    boolean found = false;
	    List<OrderItem> ois = orderitemservice.listByUser(user);
	    for (OrderItem oi : ois) {
	        if(oi.getProduct().getId()==product.getId()){
	            oi.setNumber(oi.getNumber()+num);
	            orderitemservice.update(oi);
	            found = true;
	            oiid = oi.getId();
	            break;
	        }
	    }
	 
	    if(!found){
	        OrderItem oi = new OrderItem();
	        oi.setUser(user);
	        oi.setProduct(product);
	        oi.setNumber(num);
	        orderitemservice.add(oi);
	        oiid = oi.getId();
	    }		
	    return oiid;
	}
	
		
	
	/**
	 * 跳转到购物车页面
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@GetMapping("forecart")
	public Object cart(HttpSession session) throws Exception{
	    User user =(User)  session.getAttribute("user");
	    List<OrderItem> ois = orderitemservice.listByUser(user);
	    productimageservice.setFirstProdutImagesOnOrderItems(ois);
	    return ois;
	}
	
	
	
	/**
	 * 购物车页面修改订单项，增加或减少产品
	 * @param session
	 * @param pid
	 * @param num
	 * @return
	 * @throws Exception
	 */
	@GetMapping("forechangeOrderItem")
	public void changeOrderItem( HttpSession session, int pid, int num) throws Exception{
		User user =(User)  session.getAttribute("user");		
		List<OrderItem> ois = orderitemservice.listByUser(user);
		for(OrderItem item: ois) {
			int id = item.getProduct().getId();
			if(id==pid) {
				item.setNumber(num);
				orderitemservice.update(item);
				break;
			}
		}		
	}
	
	
	
	/**
	 * 购物车页面删除订单项
	 * @param session
	 * @param oiid
	 * @return
	 * @throws Exception
	 */
	@GetMapping("foredeleteOrderItem")
	public void deleteOrderItem(HttpSession session,int oiid) throws Exception{		
		orderitemservice.delete(oiid);		
	}
	
	
	
	/***
	 * 有两种情况：
	 * 1.立即购买直接跳转到结算页面，只有一个订单项
	 * 2.购物车页面跳转到结算页面，可能有多个订单项
	 * 所以统一用数组接收 String[] oiid
	 * @param oiid
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@GetMapping("forebuy")
	public Map<String,Object> buy(String[] oiid,HttpSession session) throws Exception{
		List<OrderItem> orderItems = new ArrayList();
		double total = 0;
		for (String strid : oiid) {
			int id = Integer.parseInt(strid);
			OrderItem oi= orderitemservice.get(id);
			total += oi.getProduct().getDiscountPrice()*oi.getNumber();
			orderItems.add(oi);
		}
		
		productimageservice.setFirstProdutImagesOnOrderItems(orderItems);
		
		//将订单项集合，总价添加到session中
		session.setAttribute("ois", orderItems);
		session.setAttribute("total", total);
		
		Map<String,Object> map = new HashMap<>();
		map.put("orderItems", orderItems);
		map.put("total", total);
		return map;
	}
	
	
	/**
	 * 提交订单
	 * @param order
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@GetMapping("forecreateOrder")
	public Map<String,Object> createOrder(@RequestBody Order order,HttpSession session) throws Exception{
		User user =(User)  session.getAttribute("user");
		String orderCode = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + RandomUtils.nextInt(10000);
		order.setOrderCode(orderCode);
		order.setCreateDate(new Date());
		order.setUser(user);
		//改为待付款状态
		order.setStatus(Constants.waitPay);
		//从session中获取订单项集合和总价
		List<OrderItem> ois= (List<OrderItem>)  session.getAttribute("ois");
		double total = (double)session.getAttribute("total");
		//订单项表添加订单关联，订单表添加订单
		orderservice.add(order,ois);
		Map<String,Object> map = new HashMap<>();
		map.put("oid", order.getId());
		map.put("total", total);
		return map;
	}
	
	
	/**
	 * 确认支付
	 * @param oid
	 * @return
	 */
	@GetMapping("forepayed")
	public Object payed(int oid) throws Exception{
	    Order order = orderservice.getOrderById(oid);
	    //改为待发货状态
	    order.setStatus(Constants.waitDelivery);
	    order.setPayDate(new Date());
	    orderservice.updateOrder(order);
	    return order;
	}
	
	
	
			
}
