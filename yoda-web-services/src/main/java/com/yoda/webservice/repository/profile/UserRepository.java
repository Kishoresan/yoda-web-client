package com.yoda.webservice.repository.profile;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yoda.webservice.entity.profile.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
	
	@Query("select u from User u where u.email = :email")
	public Optional<User> findByEmail(@Param("email") String email);

}