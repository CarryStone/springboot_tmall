package com.example.demo.service.impl;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.OrderDao;
import com.example.demo.pojo.Order;
import com.example.demo.pojo.OrderItem;
import com.example.demo.service.OrderItemService;
import com.example.demo.service.OrderService;
import com.example.demo.util.PageNavigator;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderDao dao;
	@Autowired
	private OrderItemService service;

	@Override
	public PageNavigator<Order> findOrder(int page, int size) throws Exception {
		Pageable pageable = new PageRequest(page, size);
		Page<Order> pages = dao.findAll(pageable);
		List<Order> listOrder = pages.getContent();
		service.findByOrder(listOrder);
		return new PageNavigator<>(pages,5);		
	}

	@Override
	public Order getOrderById(int id) throws Exception {
		return dao.getOne(id);
	}

	@Override
	public void updateOrder(Order order) throws Exception {
		dao.save(order);
		
	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
	public void add(Order order, List<OrderItem> ois) throws Exception {
		dao.save(order);
		for(OrderItem oi:ois) {
			oi.setOrder(order);
			service.update(oi);
		}		
	}
	
	

}
