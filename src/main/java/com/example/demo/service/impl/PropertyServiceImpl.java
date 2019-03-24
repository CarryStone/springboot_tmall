package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PropertyDao;
import com.example.demo.pojo.Category;
import com.example.demo.pojo.Property;
import com.example.demo.service.PropertyService;
import com.example.demo.util.PageNavigator;

@Service
public class PropertyServiceImpl implements PropertyService{
	
	@Autowired
	private PropertyDao dao;

	@Override
	public PageNavigator<Property> getPropertyByCategory(Category category,int page,int size) throws Exception {
		Pageable pageable = new PageRequest(page, size);
		Page<Property> pages = dao.findByCategory(category, pageable);
		return new PageNavigator<Property>(pages, 5);
	}

	@Override
	public void addProperty(Property property) throws Exception {
		dao.save(property);		
	}

	@Override
	public void deleteProperty(int id) throws Exception {
		dao.delete(id);		
	}

	@Override
	public Property getPropertyById(int id) throws Exception {
		return dao.findOne(id);	
	}

	@Override
	public Property updateProperty(Property property) throws Exception {
		return dao.save(property);
	}

	@Override
	public List<Property> findByCategory(Category category) throws Exception {
		return dao.findByCategory(category);	
	}

}
