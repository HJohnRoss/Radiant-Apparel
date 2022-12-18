package com.radiantapparel.project.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radiantapparel.project.Models.Category;
import com.radiantapparel.project.Models.ProductDatabase;
import com.radiantapparel.project.Repositories.CategoryRepository;
import com.radiantapparel.project.Repositories.ProductRepository;

@Service
public class CategoryService {
    @Autowired
	private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Category> allCategories(){
        return categoryRepository.findAll();
    }

    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }

    public Category oneCategory(Long id){
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            return category.get();
        }
        return null;
    }

    public void addProduct(Long categoryId, Long productId) {
        ProductDatabase product = productRepository.findById(productId).get();
        Category category = categoryRepository.findById(categoryId).get();
        category.getProducts().add(product);
        categoryRepository.save(category);
    }

    public void deleteCategory(Category category) {
        categoryRepository.delete(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
