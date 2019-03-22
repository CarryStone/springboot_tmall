package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	
	//分类管理跳转
	@RequestMapping("admin_category_list")
	public String admin() {
		return "redirect:index";
	}
		
    //初始化跳转
	@RequestMapping("/index")
	public String listCategory() {
		return "admin/listCategory";
	}
	
	@RequestMapping("admin_category_edit")
	public String editCategory() {
		return "admin/editCategory";
	}
	
	@RequestMapping("admin_property_list")
	public String listProperty() {
		return "admin/listProperty";
	}
	
	@RequestMapping("admin_property_edit")
	public String editProperty() {
		return "admin/editProperty";
	}
	
	@RequestMapping("admin_product_list")
	public String listProduct() {
		return "admin/listProduct";
	}
	
	@RequestMapping("admin_product_edit")
	public String editProduct() {
		return "admin/editProduct";
	}	
	
	@RequestMapping("admin_productImage_list")
	public String listProductImage() {
		return "admin/listProductImage";
	}
	
	@RequestMapping("admin_propertyValue_edit")
	public String editPropertyValue() {
		return "admin/editPropertyValue";
	}
	
}
