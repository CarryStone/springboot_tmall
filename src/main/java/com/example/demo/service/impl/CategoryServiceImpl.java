package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CategoryDao;
import com.example.demo.dao.ProductDao;
import com.example.demo.pojo.Category;
import com.example.demo.pojo.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductImageService;
import com.example.demo.util.PageNavigator;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryDao dao;
	@Autowired
	private ProductDao productdao;
	@Autowired
	private ProductImageService imageservice;
	
	public PageNavigator<Category> getCategoryList(int page,int size) throws Exception{
		Pageable pageable = new PageRequest(page, size);
		Page<Category> pages =  dao.findAll(pageable);
		return new PageNavigator<Category>(pages,5);
	}
	
	@Override
	public void save(Category bean) throws Exception {
		dao.save(bean);
	}

	@Override
	public void delete(int id) throws Exception {
		dao.delete(id);		
	}

	@Override
	public Category get(int id) throws Exception {
		Category bean =  dao.getOne(id);
		return bean;
	}

	@Override
	public Category update(Category bean) throws Exception {
		dao.save(bean);
		return null;
	}

	@Override
	public List<Category> getCategoryList() throws Exception {
		return dao.findAll();		
	}

	@Override
	public void fill(List<Category> list) throws Exception {
		for(Category category:list) {
			List<Product> listProduct = productdao.findByCategory(category);
			imageservice.setFirstProdutImages(listProduct);
			//防止无限递归
			for(Product p: listProduct) {
				p.setCategory(null);
			}
			category.setProducts(listProduct);
			
		}	
	}

	@Override
	public void fillByRow(List<Category> list) throws Exception {
		for(Category category:list) {
			List<Product> listProduct = category.getProducts();
			List<List<Product>> listProductByRow = new ArrayList<>();
			int productByRow = 8;
			for(int i=0;i<listProduct.size();i+=productByRow) {
				productByRow = (i+productByRow)>listProduct.size()?listProduct.size():(i+productByRow);
				List<Product> product = listProduct.subList(i, productByRow);
				listProductByRow.add(product);
			}
			for(List<Product> listProducts:listProductByRow) {
				for(Product product:listProducts) {
					product.setCategory(null);
				}
			}
			category.setProductsByRow(listProductByRow);		
		}
		
	}
		
}
