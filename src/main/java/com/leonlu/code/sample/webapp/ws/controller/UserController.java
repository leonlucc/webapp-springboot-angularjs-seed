package com.leonlu.code.sample.webapp.ws.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.leonlu.code.sample.webapp.ws.domain.User;
import com.leonlu.code.sample.webapp.ws.rest.RestResultResponse;
import com.leonlu.code.sample.webapp.ws.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(method = RequestMethod.GET)
    public Collection<User> getUsers() {
		return userService.getAllUsers();
    }

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody User user) {
		if(user.getName() == null || user.getName().isEmpty()) {
			throw new IllegalArgumentException("Parameter 'name' must not be null or empty");
		}
		if(user.getAge() == null) {
			throw new IllegalArgumentException("Parameter 'age' must not be null or empty");
		}
        return userService.addUser(user);
    }

	@RequestMapping(value="/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

	@RequestMapping(value="/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public User updateUser(@PathVariable("id") Long id, @RequestBody User user) {
		if(user.getName() == null && user.getAge() == null) {
			throw new IllegalArgumentException("Parameter 'name' and 'age' must not both be null");
		}
		user.setId(id);
        return userService.updateUser(user);
    }

	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public RestResultResponse deleteUser(@PathVariable("id") Long id) {
		userService.deleteUser(id);
		return new RestResultResponse(true);
    }

}
