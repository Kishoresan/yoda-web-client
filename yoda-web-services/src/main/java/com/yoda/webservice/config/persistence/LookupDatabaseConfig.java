package com.yoda.webservice.config.persistence;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "lookupEntityManagerFactory", 
	transactionManagerRef = "lookupTransactionManager", basePackages = "com.yoda.webservice.repository.lookup")
public class LookupDatabaseConfig {

	@Bean(name = "lookupDataSource")
	@ConfigurationProperties(prefix = "lookup.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "lookupEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean lookupEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("lookupDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.yoda.webservice.entity.lookup").persistenceUnit("lookup")
				.build();
	}

	@Bean(name = "lookupTransactionManager")
	public PlatformTransactionManager lookupTransactionManager(
			@Qualifier("lookupEntityManagerFactory") EntityManagerFactory lookupEntityManagerFactory) {
		return new JpaTransactionManager(lookupEntityManagerFactory);
	}
}