package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.ProductValue;

@RestController
public class ProductValueController {

	@GetMapping("ProductValue/{pid}")
	public List<ProductValue> list(@PathVariable("pid") int pid) throws Exception {
		//初始化
        
		return null;
	}
}
