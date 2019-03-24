package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Product;
import com.example.demo.pojo.PropertyValue;
import com.example.demo.service.ProductService;
import com.example.demo.service.PropertyValueService;

@RestController
public class PropertyValueController {
	
	@Autowired
	private PropertyValueService service;
	@Autowired
	private ProductService productservice;

	@GetMapping("PropertyValue/{pid}")
	public List<PropertyValue> list(@PathVariable("pid") int pid) throws Exception {
		Product product = productservice.findProductById(pid);
		//初始化	
		service.init(product);	
		//根据产品获取产品属性值
		return service.getByProduct(product);	
	}
	
	@PutMapping("PropertyValue")
    public Object update(@RequestBody PropertyValue bean) throws Exception {
		service.updatePropertyValue(bean);
        return bean;
    }
}
