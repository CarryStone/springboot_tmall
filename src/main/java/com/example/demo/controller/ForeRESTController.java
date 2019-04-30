package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import com.example.demo.pojo.Category;
import com.example.demo.pojo.User;
import com.example.demo.service.CategoryService;
import com.example.demo.service.UserService;
import com.example.demo.util.Result;

@RestController
public class ForeRESTController {

	@Autowired
	private CategoryService categoryservice;
	@Autowired
	private UserService userservice;
	
	@GetMapping("forehome")
	public List<Category> list() throws Exception {
		List<Category> listCategory = categoryservice.getCategoryList();
		categoryservice.fill(listCategory);
		categoryservice.fillByRow(listCategory);
		return listCategory;
	}
	
	@PostMapping("foreregister")
	public Object register(@RequestBody User user) throws Exception {
		
		String name = HtmlUtils.htmlEscape(user.getName());
		user.setName(name);
		User users = userservice.findUserByName(name);
		if(users!=null) {
			String message = "该昵称已存在，请重新注册！";
			return new Result(Result.FAILURE_CODE,message);
		}
		userservice.addUsers(user);
		return new Result(Result.SUCCESS_CODE,null);
	}
	
	
}
