package com.radiantapparel.project.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radiantapparel.project.Models.Category;
import com.radiantapparel.project.Repositories.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
	private CategoryRepository categoryRepository;

    public List<Category> allCategories(){
        return categoryRepository.findAll();
    }

    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }
}
