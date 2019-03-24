package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PropertyValueDao;
import com.example.demo.pojo.Product;
import com.example.demo.pojo.Property;
import com.example.demo.pojo.PropertyValue;
import com.example.demo.service.PropertyService;
import com.example.demo.service.PropertyValueService;

@Service
public class PropertyValueServiceImpl implements PropertyValueService{
	

	@Autowired
	private PropertyService propertyservice;
	@Autowired
	private PropertyValueDao dao;
	

	@Override
	public void init(Product product) throws Exception {			
		List<Property> listProperty = propertyservice.findByCategory(product.getCategory());
		for(Property property:listProperty) {
			PropertyValue value = getByPropertyAndProduct(property,product);
			if(value==null) {
				PropertyValue bean = new PropertyValue();
				bean.setProduct(product);
				bean.setProperty(property);
				dao.save(bean);			
			}
		}	
	}
	
	public PropertyValue getByPropertyAndProduct(Property property,Product product) throws Exception {
		return dao.getByPropertyAndProduct(property,product);
	}

	@Override
	public List<PropertyValue> getByProduct(Product product) throws Exception {
		return dao.getByProduct(product);	
	}

	@Override
	public void updatePropertyValue(PropertyValue bean) throws Exception {	
		dao.save(bean);
	}

}
