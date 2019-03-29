package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ProductImageDao;
import com.example.demo.pojo.Product;
import com.example.demo.pojo.ProductImage;
import com.example.demo.service.ProductImageService;

@Service
public class ProductImageServiceImpl implements ProductImageService{
	
	@Autowired
	private ProductImageDao dao;

	@Override
	public List<ProductImage> findProductImageByProduct(Product product,String type) throws Exception {
		return dao.findByProductAndTypeOrderById(product, type);
	}

	@Override
	public ProductImage addProductImage(ProductImage image) throws Exception {	
		return dao.save(image);		
	}

	@Override
	public void deleteProductImage(int id) throws Exception {
		dao.delete(id);	
	}
	
	@Override
	public void setFirstProdutImages(List<Product> products) throws Exception {
		for(Product product:products) {
			setFirstProdutImage(product);
		}
	}
	
	@Override
	public void setFirstProdutImage(Product product) throws Exception {
		List<ProductImage> list = findProductImageByProduct(product,"single");
		if(!list.isEmpty()) {
			product.setImage(list.get(0));
		}else {
			product.setImage(new ProductImage());
		}
		
	}

}
