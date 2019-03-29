package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.OrderItemDao;
import com.example.demo.pojo.Order;
import com.example.demo.pojo.OrderItem;
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

}
