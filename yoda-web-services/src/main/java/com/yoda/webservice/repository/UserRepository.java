package com.yoda.webservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yoda.webservice.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

}
