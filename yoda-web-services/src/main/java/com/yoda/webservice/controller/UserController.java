package com.yoda.webservice.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yoda.webservice.dto.profile.UserDTO;
import com.yoda.webservice.service.profile.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(path = "/id/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> findById(@PathVariable("id") UUID id) {
		
		Optional<UserDTO> foundUser = userService.findById(id);
		
		if(foundUser.isPresent()) {
			return new ResponseEntity<UserDTO>(foundUser.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(path = "/email/{email}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> findByEmail(@PathVariable("email") String email) {
		
		Optional<UserDTO> foundUser = userService.findByEmail(email);
		
		if(foundUser.isPresent()) {
			return new ResponseEntity<UserDTO>(foundUser.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> save(@RequestBody(required = true) UserDTO user) {
		
		UserDTO createdUser = userService.save(user);
		
		return new ResponseEntity<UserDTO>(createdUser, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody(required = true) UserDTO user) {
		userService.update(user);
	}
	
	@RequestMapping(path = "/id/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") UUID id) {
		userService.delete(id);
	}
}