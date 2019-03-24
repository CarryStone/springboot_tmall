package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.pojo.Product;
import com.example.demo.pojo.Property;
import com.example.demo.pojo.PropertyValue;

public interface PropertyValueDao extends JpaRepository<PropertyValue, Integer>{

	public PropertyValue getByPropertyAndProduct(Property property,Product product) throws Exception;
	
	public List<PropertyValue> getByProduct(Product product) throws Exception;
}
