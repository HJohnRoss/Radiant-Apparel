package com.radiantapparel.project.Services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.radiantapparel.project.Models.LoginUser;
import com.radiantapparel.project.Models.ProductDatabase;
import com.radiantapparel.project.Models.User;
import com.radiantapparel.project.Repositories.UserRepository;




@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public User register(User newUser, BindingResult result) {
		Optional<User> optionalUser = userRepository.findByEmail(newUser.getEmail());
		if(!newUser.getPassword().equals(newUser.getConfirm())) {
			result.rejectValue("confirm", "Matches", "passwords do not match");
		}
		if(optionalUser.isPresent()) {
			result.rejectValue("email", "Unique", "email is already in use");
		}
		if(result.hasErrors()) {
			return null;
		}
		String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashed);
		return userRepository.save(newUser);
		
	} 
	
	public User login(LoginUser newLoginObject, BindingResult result) {
		Optional<User> optionalUser = userRepository.findByEmail(newLoginObject.getEmail());
		if(!optionalUser.isPresent()) {
			result.rejectValue("email", "Unique", "The email is not registered");
			return null;
		}
		User user = optionalUser.get();
		if(!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) {
			result.rejectValue("password", "Matches", "Invalid password");
		}
		
		if(result.hasErrors()) {
			return null;
		}
		return user;
	}
	
	public User createUser(User u) {
		return userRepository.save(u);
	}
	
	public User findUser(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if(optionalUser.isPresent()) {
			return optionalUser.get();
		}
		return null;
	}
	
	public User updateUser(User u) {
		return userRepository.save(u);
	}
	
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
	
	public List<User> allUsers() {
		return userRepository.findAll();
	}

	
}