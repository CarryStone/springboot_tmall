package com.example.demo.service;

import java.util.List;

import com.example.demo.pojo.Product;
import com.example.demo.pojo.PropertyValue;

public interface PropertyValueService {

	public void init(Product product) throws Exception;
	
	public List<PropertyValue> getByProduct(Product product) throws Exception;
	
	public void updatePropertyValue(PropertyValue bean) throws Exception;
}
