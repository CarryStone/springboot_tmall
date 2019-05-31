package com.example.demo.comparator;

import java.util.Comparator;

import com.example.demo.pojo.Product;

public class ProductPriceComparator implements Comparator<Product> {
	 
    @Override
    public int compare(Product p1, Product p2) {
        return (int) (p2.getDiscountPrice()-p1.getDiscountPrice());
    }
 
}
