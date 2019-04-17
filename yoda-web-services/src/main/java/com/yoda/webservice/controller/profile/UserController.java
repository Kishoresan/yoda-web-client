package com.yoda.webservice.controller.profile;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yoda.webservice.dto.profile.UserDto;
import com.yoda.webservice.service.profile.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(path = "/id/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDto> findById(@PathVariable("id") UUID id) {
		
		Optional<UserDto> foundUser = userService.findById(id);
		
		if(foundUser.isPresent()) {
			return ResponseEntity.ok().body(foundUser.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@RequestMapping(path = "/email/{email}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDto> findByEmail(@PathVariable("email") String email) {
		
		Optional<UserDto> foundUser = userService.findByEmail(email);
		
		if(foundUser.isPresent()) {
			return ResponseEntity.ok().body(foundUser.get());
		}
		
		return ResponseEntity.notFound().build();
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDto> save(@RequestBody(required = true) UserDto user) {
		
		UserDto createdUser = userService.save(user);
		
		return ResponseEntity.ok().body(createdUser);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody(required = true) UserDto user) {
		userService.update(user);
	}
	
	@RequestMapping(path = "/id/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") UUID id) {
		userService.delete(id);
	}
}