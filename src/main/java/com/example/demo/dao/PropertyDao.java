package com.example.demo.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.pojo.Category;
import com.example.demo.pojo.Property;

public interface PropertyDao extends JpaRepository<Property, Integer>{

	public Page<Property> findByCategory(Category category,Pageable page) throws Exception;
	
	public List<Property> findByCategory(Category category) throws Exception;
	
}
