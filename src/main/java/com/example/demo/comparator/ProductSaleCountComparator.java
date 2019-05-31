package com.example.demo.comparator;

import java.util.Comparator;

import com.example.demo.pojo.Product;

public class ProductSaleCountComparator implements Comparator<Product> {
	 
    @Override
    public int compare(Product p1, Product p2) {
        return p2.getSaleCount()-p1.getSaleCount();
    }
 
}
