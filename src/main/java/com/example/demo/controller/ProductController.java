package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Category;
import com.example.demo.pojo.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import com.example.demo.util.PageNavigator;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService service;
	@Autowired
	private CategoryService categoryservice;

	@GetMapping("Products")
	public PageNavigator<Product> list(@RequestParam(value="cid",defaultValue="0") int cid,
			@RequestParam(value="page",defaultValue="0")int page,
			@RequestParam(value="size",defaultValue="5")int size) throws Exception{
		Category category = categoryservice.get(cid);
		return service.findByCategory(category, page, size);
	}
	
	@PostMapping("Products")
	public Product add(@RequestBody Product product) throws Exception{
		return service.addProduct(product);
	}
	
	@DeleteMapping("Products/{id}")
	public Object delete(@PathVariable("id") int id) throws Exception{
		service.deleteProduct(id);
		return null;
	}
	
	@GetMapping("Products/{pid}")
	public Product get(@PathVariable("pid") int id) throws Exception{		
	    return service.findProductById(id);	
	}
	
	@PutMapping("Products")
	public Product put(@RequestBody Product product) throws Exception{
		return service.updateProduct(product);
	}
	
	
}
