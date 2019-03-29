package com.example.demo.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Order;
import com.example.demo.service.OrderService;
import com.example.demo.util.Constants;
import com.example.demo.util.PageNavigator;

@RestController
public class OrderController {
	
	@Autowired
	private OrderService service;	

	@GetMapping("Orders")
	public PageNavigator<Order> list(
			@RequestParam(value="page",defaultValue="0") int page,
			@RequestParam(value="size",defaultValue="5") int size) throws Exception {	
		return service.findOrder(page, size);
	}
	
	@PutMapping("Orders/{id}")
	public Order put(@PathVariable("id") int id) throws Exception {
		Order order = service.getOrderById(id);
		order.setDeliveryDate(new Date());
		order.setStatus(Constants.waitConfirm);
		service.updateOrder(order);
		return order;
	}
}
