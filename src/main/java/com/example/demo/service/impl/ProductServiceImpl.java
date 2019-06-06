package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ProductDao;
import com.example.demo.pojo.Category;
import com.example.demo.pojo.Product;
import com.example.demo.service.OrderItemService;
import com.example.demo.service.ProductService;
import com.example.demo.service.ReviewService;
import com.example.demo.util.PageNavigator;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductDao dao;
	@Autowired
	private ProductImageServiceImpl impl; 
	@Autowired
	private ReviewService reviewservice;
	@Autowired
	private OrderItemService orderitemservice;
	
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

	@Override
	public void SetSaleCountAndReviewNumber(Product product) throws Exception {
		int reviewCount = reviewservice.getReviewCount(product);
		product.setReviewCount(reviewCount);
		
		int saleCount = orderitemservice.getSaleCount(product);
		product.setSaleCount(saleCount);	
	}

	@Override
	public void SetSaleCountAndReviewNumber(List<Product> list) throws Exception {
		for(Product product:list) {
			int reviewCount = reviewservice.getReviewCount(product);
			product.setReviewCount(reviewCount);
			
			int saleCount = orderitemservice.getSaleCount(product);
			product.setSaleCount(saleCount);	
		}		
	}

	@Override
	public List<Product> search(String keyword, int start, int size) throws Exception {
		Sort sort = new Sort(Sort.Direction.DESC,"id");
		Pageable pageable = new PageRequest(start, size, sort);
		List<Product> products = dao.findByNameLike("%"+keyword+"%",pageable);	
		return products;
	}
	
}
