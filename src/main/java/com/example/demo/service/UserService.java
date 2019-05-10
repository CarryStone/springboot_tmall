package com.example.demo.service;

import com.example.demo.pojo.User;
import com.example.demo.util.PageNavigator;

public interface UserService {
	
	public PageNavigator<User> getUsers(int page,int size) throws Exception; 
	
	public User findUserByName(String name) throws Exception;
	
	public void addUsers(User user) throws Exception;
	
	public User get(String name,String password) throws Exception;

}
