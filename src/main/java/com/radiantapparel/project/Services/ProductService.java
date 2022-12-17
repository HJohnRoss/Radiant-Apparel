package com.radiantapparel.project.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radiantapparel.project.Models.ProductDatabase;
import com.radiantapparel.project.Repositories.ProductRepository;
import com.stripe.model.Product;

@Service
public class ProductService {
    
    @Autowired
	private ProductRepository productRepository;

    public List<ProductDatabase> allProducts(){
        return productRepository.findAll();
    }

    public void createProduct(ProductDatabase product, Product stripeProduct) {
        // saving the stripe product id in our database
        product.setStripeProductId(stripeProduct.getId());
        // saving product
        productRepository.save(product);
    }
}
