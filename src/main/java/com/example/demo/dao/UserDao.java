package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.pojo.User;

public interface UserDao extends JpaRepository<User, Integer>{
	
	public User findUserByName(String name) throws Exception;
	
	public User getByNameAndPassword(String name, String password) throws Exception;

}
