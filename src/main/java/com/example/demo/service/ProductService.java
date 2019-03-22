package com.example.demo.service;

import com.example.demo.pojo.Category;
import com.example.demo.pojo.Product;
import com.example.demo.util.PageNavigator;

public interface ProductService {

	public PageNavigator<Product> findByCategory(Category category,int page,int size) throws Exception;
	
	public Product addProduct(Product product) throws Exception;
	
	public void deleteProduct(int id) throws Exception;
	
	public Product findProductById(int id) throws Exception;
	
	public Product updateProduct(Product product) throws Exception;
}