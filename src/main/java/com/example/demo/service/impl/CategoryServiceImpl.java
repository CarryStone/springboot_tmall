package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CategoryDao;
import com.example.demo.pojo.Category;
import com.example.demo.service.CategoryService;
import com.example.demo.util.PageNavigator;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryDao dao;
	
	public PageNavigator<Category> getCategoryList(int page,int size) throws Exception{
		Pageable pageable = new PageRequest(page, size);
		Page<Category> pages =  dao.findAll(pageable);
		return new PageNavigator<Category>(pages,5);
	}
	
	@Override
	public void save(Category bean) throws Exception {
		dao.save(bean);
	}

	@Override
	public void delete(int id) throws Exception {
		dao.delete(id);		
	}

	@Override
	public Category get(int id) throws Exception {
		Category bean =  dao.getOne(id);
		return bean;
	}

	@Override
	public Category update(Category bean) throws Exception {
		dao.save(bean);
		return null;
	}
	

	
	
}
