package com.leonlu.code.sample.webapp.ws.controller;

import static org.junit.Assert.*;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.leonlu.code.sample.webapp.ws.App;
import com.leonlu.code.sample.webapp.ws.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
    private int port;
	private URL base;
	
	private static Long userId;
	
	@Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/");
        restTemplate = new TestRestTemplate();
    }
	
	@Test
	public void testAddUser() {
		User requestUser = new User();
		requestUser.setName("tom");
		requestUser.setAge(12);
		ResponseEntity<User> responseBody = this.restTemplate.postForEntity(base.toString() + "user", requestUser, User.class);
		System.out.println(responseBody.getBody());
		assertEquals(HttpStatus.CREATED, responseBody.getStatusCode());
		userId = responseBody.getBody().getId();
	}
	
	@Test
	public void testUpdateUser() {
		String url = base.toString() + "user" + "/" + userId;
		User tom = this.restTemplate.getForObject(url, User.class);
		tom.setAge(20);
		this.restTemplate.put(url, tom);		
		assertEquals(20, this.restTemplate.getForObject(url, User.class).getAge().intValue());
	}
	
	@Test
	public void testDeleteUser() {
		String url = base.toString() + "user" + "/" + userId;
		this.restTemplate.delete(url);
		ResponseEntity<String> res = this.restTemplate.getForEntity(url, String.class);
		System.out.println(res.getBody());
		assertEquals(HttpStatus.NOT_FOUND, res.getStatusCode());
		
	}

}
