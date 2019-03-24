package com.example.demo.service;

import com.example.demo.pojo.User;
import com.example.demo.util.PageNavigator;

public interface UserService {
	
	public PageNavigator<User> getUsers(int page,int size) throws Exception; 

}
