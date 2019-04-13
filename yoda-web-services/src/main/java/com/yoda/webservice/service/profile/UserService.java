package com.yoda.webservice.service.profile;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.yoda.webservice.dto.profile.UserDTO;

@Component
public interface UserService {
	
	public Optional<UserDTO> findById(UUID id);
	
	public Optional<UserDTO> findByEmail(String email);
	
	public UserDTO save(UserDTO user);

	public UserDTO update(UserDTO user);
	
	public void delete(UUID id);
}
