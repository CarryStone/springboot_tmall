package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ProductDao;
import com.example.demo.pojo.Category;
import com.example.demo.pojo.Product;
import com.example.demo.service.ProductService;
import com.example.demo.util.PageNavigator;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductDao dao;
	@Autowired
	private ProductImageServiceImpl impl; 
	
	@Override
	public PageNavigator<Product> findByCategory(Category category,int page,int size) throws Exception{
		 Pageable pageable = new PageRequest(page, size);
		 Page<Product> pages = dao.findByCategory(category, pageable);
		 PageNavigator<Product> pageNavigator =  new PageNavigator<Product>(pages,5);
		 impl.setFirstProdutImages(pageNavigator.getContent());
		 return pageNavigator;
	}

	@Override
	public Product addProduct(Product product) throws Exception {
		return dao.save(product);
		
	}

	@Override
	public void deleteProduct(int id) throws Exception {
		dao.delete(id);	
	}

	@Override
	public Product findProductById(int id) throws Exception {
		return dao.getOne(id);
	}

	@Override
	public Product updateProduct(Product product) throws Exception {
		return dao.save(product);
	}
	
	

}
