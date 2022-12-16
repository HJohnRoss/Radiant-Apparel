package com.radiantapparel.project.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.radiantapparel.project.Models.Price;

@Repository
public interface PriceRepository extends CrudRepository<Price, Long> {
    // List<Price> findAll();
}
