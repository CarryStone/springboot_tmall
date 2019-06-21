package com.example.demo.service;

import java.util.List;

import com.example.demo.pojo.OrderItem;
import com.example.demo.pojo.Product;
import com.example.demo.pojo.ProductImage;

public interface ProductImageService {
	
	public List<ProductImage> findProductImageByProduct(Product product,String type) throws Exception;
	
	public ProductImage addProductImage(ProductImage image) throws Exception;
	
	public void deleteProductImage(int id) throws Exception;
	
	public void setFirstProdutImages(List<Product> products) throws Exception;
	
	public void setFirstProdutImage(Product product) throws Exception;
	
	public void setFirstProdutImagesOnOrderItems(List<OrderItem> orderitems) throws Exception;

}
