package com.radiantapparel.project.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.radiantapparel.project.Models.User;


public interface UserRepository extends CrudRepository<User, Long>{
    List<User> findAll();
	
	Optional<User> findById(Long id);
	
	Optional<User> findByEmail(String email);

}
