package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import dao.UserRepository;
import model.User;
import service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
class JunitMockitoApplicationTests {
	
	@Autowired
	private UserService service;
	
	@MockBean
	private UserRepository repository;

	@Test
	public void getUsersTest() {
		when(repository.findAll()).thenReturn(Stream
				.of(new User(376, "Den", 20, "USA"), new User(232,"Yuz",23,"CANADA"))
				.collect(Collectors.toList()));
	}
	@Test
	public void getUserbyAddressTest() {
		String address = "Washington";
		when(repository.findByAddress(address))
		.thenReturn(Stream
				.of(new User(376, "Den", 20, "USA"), new User(232,"Yuz",23,"CANADA"))
				.collect(Collectors.toList()));
		assertEquals(1, service.getUserbyAddress(address).size());
	}
	@Test
	public void saveUserTest() {
		User user = new User(123, "Jim ", 22, "Toronto");
		when(repository.save(user)).thenReturn(user);
		assertEquals(user, service.addUser(user));
	}
	@Test
	public void deleteUserTest() {
		User user = new User(123, "Jim", 22, "Toronto");
		service.deleteUser(user);
		verify(repository, times(1)).delete(user);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
