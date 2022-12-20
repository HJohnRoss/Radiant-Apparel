package com.radiantapparel.project.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radiantapparel.project.Models.Category;
import com.radiantapparel.project.Models.PriceDatabase;
import com.radiantapparel.project.Models.ProductDatabase;
import com.radiantapparel.project.Models.Review;
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

    public List<ProductDatabase> findAllByCategory(Category category) {
        return productRepository.findAllByCategories(category);
    }

    public List<ProductDatabase> findByCategoriesNotContains(Category category) {
        return productRepository.findByCategoriesNotContains(category);
    }

    public ProductDatabase findProductById(Long id) {
        Optional<ProductDatabase> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()) {
            return optionalProduct.get();
        }
        else {
            return null;
        }
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById((Long) productId);
    }

    public PriceDatabase productPrices(Long id) {
        Optional<ProductDatabase> optionalProduct = productRepository.findById((Long) id);
        if(optionalProduct.isPresent()){
            ProductDatabase product = optionalProduct.get();
            return product.getPrice();
        }
        return null;
    }

    public List<Category> productCategories(Long id) {
        Optional<ProductDatabase> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            ProductDatabase product = optionalProduct.get();
            return product.getCategories();
        }
        return null;
    }

    public ProductDatabase oneProduct(Long id) {
        Optional<ProductDatabase> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            ProductDatabase product = optionalProduct.get();
            return product;
        }
        return null;
    }
    
    public List<ProductDatabase> findByName(String name) {
        return productRepository.findByNameContaining(name);
    }

    public void addReview(Long productId, Review review) {
        Optional<ProductDatabase> optionalProduct = productRepository.findById((Long) productId);
        if(optionalProduct.isPresent()) {
            ProductDatabase product = optionalProduct.get();
            List<Review> productReviews = product.getReviews();
            productReviews.add(review);
        }
    }
}