package com.yoda.webservice.service.lookup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoda.webservice.dto.lookup.DocumentTypeDto;
import com.yoda.webservice.entity.lookup.DocumentType;
import com.yoda.webservice.repository.lookup.DocumentTypeRepository;
import com.yoda.webservice.service.BaseJpaBackedService;

@Service("documentTypeService")
@Transactional(transactionManager = "lookupTransactionManager")
public class DocumentTypeServiceImpl extends BaseJpaBackedService<DocumentTypeDto, DocumentType, Short>
		implements DocumentTypeService {

	@Autowired
	private DocumentTypeRepository documentTypeRepository;

	@Override
	protected DocumentTypeDto buildDto(DocumentType entity) {
		return DocumentTypeDto.of(entity);
	}

	@Override
	protected JpaRepository<DocumentType, Short> getJpaRepository() {
		return documentTypeRepository;
	}
}