package com.example.demo.service;

import java.util.List;

import com.example.demo.pojo.Category;
import com.example.demo.pojo.Product;
import com.example.demo.util.PageNavigator;

public interface ProductService {

	public PageNavigator<Product> findByCategory(Category category,int page,int size) throws Exception;
	
	public List<Product> findByCategory(Category category) throws Exception;
	
	public Product addProduct(Product product) throws Exception;
	
	public void deleteProduct(int id) throws Exception;
	
	public Product findProductById(int id) throws Exception;
	
	public Product updateProduct(Product product) throws Exception;
	
	public void SetSaleCountAndReviewNumber(List<Product> list) throws Exception;
	
	public void SetSaleCountAndReviewNumber(Product product) throws Exception;
	
	public List<Product> search(String keyword, int start, int size) throws Exception;
}
