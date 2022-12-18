package com.radiantapparel.project.Repositories;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import com.radiantapparel.project.Models.Category;
import com.radiantapparel.project.Models.ProductDatabase;


@Repository
public interface ProductRepository extends CrudRepository<ProductDatabase, Long> {
    List<ProductDatabase> findAll();

    List<ProductDatabase> findAllByCategories(Category category);

    List<ProductDatabase> findByCategoriesNotContains(Category category);
}
