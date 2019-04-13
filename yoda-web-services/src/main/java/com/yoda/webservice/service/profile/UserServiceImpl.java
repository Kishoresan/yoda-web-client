package com.yoda.webservice.service.profile;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoda.webservice.dto.profile.UserDTO;
import com.yoda.webservice.entity.profile.User;
import com.yoda.webservice.repository.profile.UserRepository;

@Service("userService")
@Transactional(transactionManager = "profileTransactionManager")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Optional<UserDTO> findById(UUID id) {

		Optional<User> foundEntity = userRepository.findById(id);

		if (foundEntity.isPresent()) {

			return Optional.<UserDTO>of(UserDTO.of(foundEntity.get()));
		}

		return Optional.<UserDTO>empty();
	}

	@Override
	public Optional<UserDTO> findByEmail(String email) {

		Optional<User> foundEntity = userRepository.findByEmail(email);

		if (foundEntity.isPresent()) {

			return Optional.<UserDTO>of(UserDTO.of(foundEntity.get()));
		}

		return Optional.<UserDTO>empty();
	}

	@Override
	public UserDTO save(UserDTO user) {

		User createdUser = userRepository.saveAndFlush(user.createJPAEntity());
		user.synchronizeWithEntity(createdUser);

		return user;
	}

	@Override
	public UserDTO update(UserDTO user) {
		return save(user);
	}

	@Override
	public void delete(UUID id) {
		userRepository.deleteById(id);
	}
}