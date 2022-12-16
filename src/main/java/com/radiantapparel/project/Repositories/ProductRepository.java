package com.radiantapparel.project.Repositories;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import com.radiantapparel.project.Models.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    // List<Product> findAll();
}
