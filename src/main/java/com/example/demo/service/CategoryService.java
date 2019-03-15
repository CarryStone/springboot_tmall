package com.example.demo.service;

import com.example.demo.pojo.Category;
import com.example.demo.util.PageNavigator;

public interface CategoryService {

	public PageNavigator<Category> getCategoryList(int page,int size) throws Exception;
	
	public void save(Category bean) throws Exception;
	
	public void delete(int id) throws Exception;
	
	public Category get(int id) throws Exception;
	
	public Category update(Category bean) throws Exception;
}
