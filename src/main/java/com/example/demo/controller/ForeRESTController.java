package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Category;
import com.example.demo.service.CategoryService;

@RestController
public class ForeRESTController {

	@Autowired
	private CategoryService categoryservice;
	
	@GetMapping("forehome")
	public List<Category> list() throws Exception {
		List<Category> listCategory = categoryservice.getCategoryList();
		categoryservice.fill(listCategory);
		categoryservice.fillByRow(listCategory);
		return listCategory;
	}
}
