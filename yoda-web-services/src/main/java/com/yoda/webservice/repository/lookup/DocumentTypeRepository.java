package com.yoda.webservice.repository.lookup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yoda.webservice.entity.lookup.DocumentType;

@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentType, Short>{

}
