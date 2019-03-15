package com.example.demo.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.pojo.Category;
import com.example.demo.service.CategoryService;
import com.example.demo.util.ImageUtil;
import com.example.demo.util.PageNavigator;





@RestController
public class CategoryController {

	@Autowired
	private CategoryService service;
	
	@GetMapping("categories")
	public PageNavigator<Category> getCategoryList(
			@RequestParam(value="page",defaultValue="0") int page,
			@RequestParam(value="size",defaultValue="5") int size) throws Exception{	
		return service.getCategoryList(page,size);
	}
	
	@GetMapping("categories/{id}")
	public Category get(@PathVariable("id") int id) throws Exception{
		return service.get(id);
	}
	
	@PostMapping("categories")
	public Category add(Category bean,MultipartFile image,HttpServletRequest request) throws Exception{	
		service.save(bean);
		ImageUtil.saveImg(bean,image,request);
		return bean;
	}
	
	@DeleteMapping("categories/{id}")
	public String delete(@PathVariable("id") int id,HttpServletRequest request) throws Exception{
		service.delete(id);
		File imgfolder = new File(request.getServletContext().getRealPath("img/category"));
		File file = new File(imgfolder,id+".jpg");
		file.delete();
		return null;
	}
	
	@PutMapping("categories/{id}")
	public Category update(Category bean,MultipartFile image,HttpServletRequest request) throws Exception{
		String name = request.getParameter("name");				
		bean.setName(name);
		service.update(bean);
		if(image!=null) {
			ImageUtil.saveImg(bean,image,request);
		}
		return bean;
	}
}
