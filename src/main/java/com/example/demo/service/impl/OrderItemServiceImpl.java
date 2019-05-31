package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.OrderItemDao;
import com.example.demo.pojo.Order;
import com.example.demo.pojo.OrderItem;
import com.example.demo.pojo.Product;
import com.example.demo.service.OrderItemService;
import com.example.demo.service.ProductImageService;


@Service
public class OrderItemServiceImpl implements OrderItemService{
	
	@Autowired
	private OrderItemDao dao;
	@Autowired
	private ProductImageService service;

	@Override
	public void findByOrder(List<Order> list) throws Exception {
		for(Order order:list) {
			List<OrderItem> listItem = dao.findByOrder(order);	
			double total = 0.00;
			int totalNumber = 0;			
			for(OrderItem item:listItem) {
				 total = total+(item.getProduct().getDiscountPrice())*(item.getNumber());
				 totalNumber = totalNumber+item.getNumber();
				 service.setFirstProdutImage(item.getProduct());
			}
			order.setOrderItems(listItem);
			order.setTotal(total);
			order.setTotalNumber(totalNumber);
		}
		
	}

	//获取商品销量，没有订单和支付日期的排除
	@Override
	public int getSaleCount(Product product) throws Exception {
		List<OrderItem> itemlist = dao.findByProduct(product);
		int count = 0;
		for(OrderItem oi:itemlist) {
			if(null!= oi.getOrder() && null!=oi.getOrder().getPayDate()) {
				count += oi.getNumber();
			}		
		}
		return count;
	}

}
