package com.yoda.webservice.repository.profile;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yoda.webservice.entity.profile.IdentityDocument;

@Repository
public interface IdentityDocumentRepository extends JpaRepository<IdentityDocument, UUID>{

}