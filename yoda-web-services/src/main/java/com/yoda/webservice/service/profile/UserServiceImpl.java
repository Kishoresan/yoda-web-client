package com.yoda.webservice.service.profile;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoda.webservice.dto.profile.UserDto;
import com.yoda.webservice.entity.profile.User;
import com.yoda.webservice.repository.profile.UserRepository;
import com.yoda.webservice.service.BaseJpaBackedService;

@Service("userService")
@Transactional(transactionManager = "profileTransactionManager")
public class UserServiceImpl extends BaseJpaBackedService<UserDto, User> implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Optional<UserDto> findByEmail(String email) {
		return extractDtoFromOptional(userRepository.findByEmail(email));
	}

	@Override
	protected UserDto buildDto(User entity) {
		return UserDto.of(entity);
	}

	@Override
	protected JpaRepository<User, UUID> getJpaRepository() {
		return userRepository;
	}
}