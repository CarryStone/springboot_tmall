package com.example.demo.service;

import java.util.List;

import com.example.demo.pojo.Order;
import com.example.demo.pojo.OrderItem;
import com.example.demo.pojo.Product;
import com.example.demo.pojo.User;

public interface OrderItemService {
	
	public void findByOrder(List<Order> list) throws Exception;
	
	public int getSaleCount(Product product) throws Exception;
	
	public List<OrderItem> listByUser(User user) throws Exception;
	
	public void update(OrderItem item) throws Exception;
	
	public void add(OrderItem item) throws Exception;
	
	public OrderItem get(int id) throws Exception;
	
	public void delete(int id) throws Exception;
	
	public void fill(Order order) throws Exception;
		
}
