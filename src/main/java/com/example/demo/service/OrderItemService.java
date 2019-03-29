package com.example.demo.service;

import java.util.List;

import com.example.demo.pojo.Order;

public interface OrderItemService {
	
	public void findByOrder(List<Order> list) throws Exception;

}
