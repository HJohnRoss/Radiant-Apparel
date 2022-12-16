package com.radiantapparel.project.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radiantapparel.project.Repositories.TypeRepository;

@Service
public class TypeService {
    
    @Autowired
	private TypeRepository typeRepository;

    
}
