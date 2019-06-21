package com.example.demo.service;

import java.util.List;

import com.example.demo.pojo.Order;
import com.example.demo.pojo.OrderItem;
import com.example.demo.util.PageNavigator;

public interface OrderService {
	
	public PageNavigator<Order> findOrder(int page,int size) throws Exception;
	
	public Order getOrderById(int id) throws Exception;
	
	public void updateOrder(Order order) throws Exception;
	
	public void add(Order order, List<OrderItem> ois) throws Exception;

}
