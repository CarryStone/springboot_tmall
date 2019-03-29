package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.pojo.Order;
import com.example.demo.pojo.OrderItem;

public interface OrderItemDao extends JpaRepository<OrderItem, Integer>{
	
	public List<OrderItem> findByOrder(Order order) throws Exception;

}
