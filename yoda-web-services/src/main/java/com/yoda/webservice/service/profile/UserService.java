package com.yoda.webservice.service.profile;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.yoda.webservice.dto.profile.UserDto;
import com.yoda.webservice.entity.profile.User;
import com.yoda.webservice.service.JpaBackedService;

@Component
public interface UserService extends JpaBackedService<UserDto, User>{
	
	public Optional<UserDto> findByEmail(String email);
}