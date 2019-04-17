package com.yoda.webservice.service.lookup;

import org.springframework.stereotype.Component;

import com.yoda.webservice.dto.lookup.CountryDto;
import com.yoda.webservice.entity.lookup.Country;
import com.yoda.webservice.service.JpaBackedService;

@Component
public interface CountryService extends JpaBackedService<CountryDto, Country, Short> {

}