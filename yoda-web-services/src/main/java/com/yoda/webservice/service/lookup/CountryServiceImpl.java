package com.yoda.webservice.service.lookup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoda.webservice.dto.lookup.CountryDto;
import com.yoda.webservice.entity.lookup.Country;
import com.yoda.webservice.repository.lookup.CountryRepository;
import com.yoda.webservice.service.BaseJpaBackedService;

@Service("countryService")
@Transactional(transactionManager = "lookupTransactionManager")
public class CountryServiceImpl extends BaseJpaBackedService<CountryDto, Country, Short> implements CountryService {

	@Autowired
	private CountryRepository countryRepository;

	@Override
	protected CountryDto buildDto(Country entity) {
		return CountryDto.of(entity);
	}

	@Override
	protected JpaRepository<Country, Short> getJpaRepository() {
		return countryRepository;
	}
}