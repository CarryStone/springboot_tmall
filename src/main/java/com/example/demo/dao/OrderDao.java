package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.pojo.Order;

public interface OrderDao extends JpaRepository<Order, Integer>{

}