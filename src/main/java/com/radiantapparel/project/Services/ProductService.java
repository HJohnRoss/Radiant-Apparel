package com.radiantapparel.project.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radiantapparel.project.Repositories.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
	private ProductRepository productRepository;
}
