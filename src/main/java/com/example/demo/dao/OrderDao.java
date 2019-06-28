package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.pojo.Order;
import com.example.demo.pojo.User;

public interface OrderDao extends JpaRepository<Order, Integer>{
	
	public List<Order> findByUserAndStatusNotOrderByIdDesc(User user,String status) throws Exception;

}
