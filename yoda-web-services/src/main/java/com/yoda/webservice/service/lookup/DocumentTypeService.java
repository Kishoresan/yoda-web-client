package com.yoda.webservice.service.lookup;

import org.springframework.stereotype.Component;

import com.yoda.webservice.dto.lookup.DocumentTypeDto;
import com.yoda.webservice.entity.lookup.DocumentType;
import com.yoda.webservice.service.JpaBackedService;

@Component
public interface DocumentTypeService extends JpaBackedService<DocumentTypeDto, DocumentType, Short> {

}