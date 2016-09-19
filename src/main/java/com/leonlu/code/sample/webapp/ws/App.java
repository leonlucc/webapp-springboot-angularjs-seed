package com.leonlu.code.sample.webapp.ws;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.leonlu.code.sample.webapp.ws.domain.User;
import com.leonlu.code.sample.webapp.ws.service.UserService;

@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
public class App {
	@Bean
    CommandLineRunner init(UserService userService) {
		// add 5 new users after app is started
        return (evt) ->
                Arrays.asList("john,alex,mike,mary,jenny".split(","))
                        .forEach(item -> {
                            User user = new User(item, (int)(20 + Math.random() * 10));
                            userService.addUser(user);
                        });
    }

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
