package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.pojo.Product;
import com.example.demo.pojo.ProductImage;

public interface ProductImageDao extends JpaRepository<ProductImage, Integer>{
	
	public List<ProductImage> findByProductAndTypeOrderById(Product product,String type);

}
