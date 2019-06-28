package com.example.demo.service;

import java.util.List;

import com.example.demo.pojo.Product;
import com.example.demo.pojo.Review;

public interface ReviewService {

	public List<Review> list(Product product) throws Exception;
	
	public int getReviewCount(Product product) throws Exception;
	
	public void add(Review review) throws Exception;
}
