package com.radiantapparel.project.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radiantapparel.project.Models.Category;
import com.radiantapparel.project.Models.Type;
import com.radiantapparel.project.Repositories.TypeRepository;

@Service
public class TypeService {
    
    @Autowired
	private TypeRepository typeRepository;

    public List<Type> allTypes(){
        return typeRepository.findAll();
    }

    public Type createType(Type type){
        return typeRepository.save(type);
    }

    public Type oneType(Long id){
        Optional<Type> type = typeRepository.findById(id);
        if(type.isPresent()){
            return type.get();
        }
        return null;
    }

    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }

    public List<Category> typeCategories(Long typeId) {
        Optional<Type> optionalType = typeRepository.findById(typeId);
        if(optionalType.isPresent()){
            Type type = optionalType.get();
            return type.getCategories();
        }
        return null;
    }
}
