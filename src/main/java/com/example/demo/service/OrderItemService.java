package com.example.demo.service;

import java.util.List;

import com.example.demo.pojo.Order;
import com.example.demo.pojo.Product;

public interface OrderItemService {
	
	public void findByOrder(List<Order> list) throws Exception;
	
	public int getSaleCount(Product product) throws Exception;
	
	

}
