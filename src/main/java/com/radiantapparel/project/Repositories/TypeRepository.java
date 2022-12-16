package com.radiantapparel.project.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.radiantapparel.project.Models.Type;

@Repository
public interface TypeRepository extends CrudRepository<Type, Long> {
    List<Type> findAll();
}
