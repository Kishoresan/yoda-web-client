package com.yoda.webservice.repository.profile;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yoda.webservice.entity.profile.IdentityDocument;

@Repository
public interface IdentityDocumentRepository extends JpaRepository<IdentityDocument, UUID>{
	
	@Query("select doc from IdentityDocument doc where doc.userId = :userId")
	public Optional<IdentityDocument> findByUserId(@Param("userId") UUID userId);

}