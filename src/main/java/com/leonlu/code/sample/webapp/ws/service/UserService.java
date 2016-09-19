package com.leonlu.code.sample.webapp.ws.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonlu.code.sample.webapp.ws.domain.User;
import com.leonlu.code.sample.webapp.ws.exception.UserNotFoundException;
import com.leonlu.code.sample.webapp.ws.repository.UserRepository;

@Service
public class UserService {
	private UserRepository userRepository;
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public Collection<User> getAllUsers() {
		Iterable<User> userIter = userRepository.findAll();
		ArrayList<User> userList = new ArrayList<User>();
		userIter.forEach(item -> {
			userList.add(item);
		});
		return userList;
	}
	
	public User getUserById(Long id) {
		User user =  userRepository.findOne(id);
		if(user == null) {
			throw new UserNotFoundException(id);
		}
		return user;
	}
	
	public User getUserByName(String name) {
		return userRepository.findByName(name)
				.orElseThrow(() -> new UserNotFoundException(name));
	}
	
	public User addUser(User user) {
		return userRepository.save(user);
		
	}
	
	public User updateUser(User user) {
		getUserById(user.getId());
		return userRepository.save(user);	
	}
	
	public void deleteUser(Long id) {
		getUserById(id);
		userRepository.delete(id);
	}
	
}
