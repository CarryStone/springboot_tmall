package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.pojo.Product;
import com.example.demo.pojo.Review;

public interface ReviewDAO extends JpaRepository<Review, Integer>{

	public List<Review> findByProductOrderByIdDesc(Product product);
	
	public int countByProduct(Product product);
}
