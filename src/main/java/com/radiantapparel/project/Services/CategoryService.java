package com.radiantapparel.project.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radiantapparel.project.Repositories.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
	private CategoryRepository categoryRepository;
}
