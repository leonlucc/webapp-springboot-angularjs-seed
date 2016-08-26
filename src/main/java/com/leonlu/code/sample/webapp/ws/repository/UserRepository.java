package com.leonlu.code.sample.webapp.ws.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

import com.leonlu.code.sample.webapp.ws.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	Optional<User> findByName(String name);
}
