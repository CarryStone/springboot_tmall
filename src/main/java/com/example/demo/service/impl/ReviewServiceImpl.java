package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ReviewDAO;
import com.example.demo.pojo.Product;
import com.example.demo.pojo.Review;
import com.example.demo.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService{
	
	@Autowired
	private ReviewDAO dao;

	@Override
	public List<Review> list(Product product) throws Exception {
		return dao.findByProductOrderByIdDesc(product);		
	}

	@Override
	public int getReviewCount(Product product) throws Exception {
		return dao.countByProduct(product);
	}

}
