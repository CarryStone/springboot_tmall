package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import com.example.demo.util.PageNavigator;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao dao;

	@Override
	public PageNavigator<User> getUsers(int page, int size) throws Exception {
		Pageable pageable = new PageRequest(page,size);
		Page<User> pageFromJPA = dao.findAll(pageable);
		return new PageNavigator<>(pageFromJPA, 5);		
	}

}
