package com.radiantapparel.project.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.radiantapparel.project.Models.PriceDatabase;

@Repository
public interface PriceRepository extends CrudRepository<PriceDatabase, Long> {
    List<PriceDatabase> findAll();
}
