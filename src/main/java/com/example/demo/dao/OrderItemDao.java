package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.pojo.Order;
import com.example.demo.pojo.OrderItem;
import com.example.demo.pojo.Product;

public interface OrderItemDao extends JpaRepository<OrderItem, Integer>{
	
	public List<OrderItem> findByOrder(Order order) throws Exception;
	
	public List<OrderItem> findByProduct(Product product) throws Exception;

}
