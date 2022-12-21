package com.radiantapparel.project.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.radiantapparel.project.Models.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    List<Category> findAll();
    
    Optional<Category> findByNameContaining(String name);
}
