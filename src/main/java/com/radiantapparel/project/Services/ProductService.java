package com.radiantapparel.project.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radiantapparel.project.Models.Product;
import com.radiantapparel.project.Repositories.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
	private ProductRepository productRepository;

    public List<Product> allProducts(){
        return productRepository.findAll();
    }
}
