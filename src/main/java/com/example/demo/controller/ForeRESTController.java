package com.example.demo.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
import com.example.demo.pojo.Product;
import com.example.demo.pojo.ProductImage;
import com.example.demo.pojo.PropertyValue;
import com.example.demo.pojo.Review;
import com.example.demo.pojo.User;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductImageService;
import com.example.demo.service.ProductService;
import com.example.demo.service.PropertyValueService;
import com.example.demo.service.ReviewService;
import com.example.demo.service.UserService;
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
	
	@GetMapping("forehome")
	public List<Category> list() throws Exception {
		List<Category> listCategory = categoryservice.getCategoryList();
		categoryservice.fill(listCategory);
		categoryservice.fillByRow(listCategory);
		return listCategory;
	}
	
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
	
	@GetMapping("forecheckLogin")
	public Object checkLogin( HttpSession session) {
		User user = (User)session.getAttribute("user");
		if(user==null) {
			return new Result(Result.FAILURE_CODE,"未登录");
		}
		return new Result(Result.SUCCESS_CODE,null);
	}
	
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
	
	//分类页
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
	
	
		
}
