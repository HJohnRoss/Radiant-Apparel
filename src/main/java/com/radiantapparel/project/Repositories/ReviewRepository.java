package com.radiantapparel.project.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.radiantapparel.project.Models.Review;

public interface ReviewRepository extends CrudRepository<Review, Long>{

    List<Review> findAll();
    
    List<Review> findByProductIdIs(Long id);
}
