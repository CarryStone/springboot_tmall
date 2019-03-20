package com.example.demo.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.pojo.Category;
import com.example.demo.pojo.Product;

public interface ProductDao extends JpaRepository<Product, Integer>{

	public Page<Product> findByCategory(Category category,Pageable page) throws Exception;
}
