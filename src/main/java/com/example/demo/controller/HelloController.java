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
	public String hello() {
		return "admin/listCategory";
	}
	
	@RequestMapping("admin_category_edit")
	public String edit() {
		return "admin/editCategory";
	}
	
	@RequestMapping("admin_property_list")
	public String property() {
		return "admin/listProperty";
	}
}
