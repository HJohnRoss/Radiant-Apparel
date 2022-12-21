package com.radiantapparel.project.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.radiantapparel.project.Models.Category;
import com.radiantapparel.project.Models.ProductDatabase;
import com.radiantapparel.project.Models.User;



@Repository
public interface ProductRepository extends CrudRepository<ProductDatabase, Long> {
    List<ProductDatabase> findAll();

    List<ProductDatabase> findAllByCategories(Category category);

    List<ProductDatabase> findByCategoriesNotContains(Category category);

    List<ProductDatabase> findByNameContaining(String name);

    List<ProductDatabase> findAllByUsers(User user);
    
}
