package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Category;
import com.example.demo.pojo.Property;
import com.example.demo.service.CategoryService;
import com.example.demo.service.PropertyService;
import com.example.demo.util.PageNavigator;

@RestController
public class PropertyController {
	
	@Autowired
	private PropertyService service;
	@Autowired
	private CategoryService categoryservice;
	
	@GetMapping("Properties")
	public PageNavigator<Property> list(@RequestParam(value="cid",defaultValue="0") int cid,
			@RequestParam(value="page",defaultValue="0")int page,
			@RequestParam(value="size",defaultValue="5")int size) throws Exception{
		//根据id获取当前category对象
		Category category = categoryservice.get(cid);
		return service.getPropertyByCategory(category, page, size);
	}
	
	@PostMapping("Properties")
	public Property add(@RequestBody Property property) throws Exception{	
		service.addProperty(property);
		return property;
	}
	
	@DeleteMapping("Properties/{id}")
	public Object delete(@PathVariable(value="id") int id) throws Exception{
		service.deleteProperty(id);
		return null;
	}

}
