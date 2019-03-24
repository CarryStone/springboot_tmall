package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import com.example.demo.util.PageNavigator;

@RestController
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping("Users")
	public PageNavigator<User> list(
			@RequestParam(value="page",defaultValue="0") int page,
			@RequestParam(value="size",defaultValue="5") int size) throws Exception {
		return service.getUsers(page,size);	
	}

}
