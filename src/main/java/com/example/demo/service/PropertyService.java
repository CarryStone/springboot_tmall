package com.example.demo.service;


import java.util.List;

import com.example.demo.pojo.Category;
import com.example.demo.pojo.Property;
import com.example.demo.util.PageNavigator;



public interface PropertyService {

	public PageNavigator<Property> getPropertyByCategory(Category category,int page,int size) throws Exception;
	
	public void addProperty(Property property) throws Exception;
	
	public void deleteProperty(int id) throws Exception;
	
	public Property getPropertyById(int id) throws Exception;
	
	public Property updateProperty(Property property) throws Exception;
	
	public List<Property> findByCategory(Category category) throws Exception;
		
	
}
