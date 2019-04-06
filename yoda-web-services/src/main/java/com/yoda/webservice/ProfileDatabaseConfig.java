package com.yoda.webservice;

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
@EnableJpaRepositories(entityManagerFactoryRef = "profileEntityManagerFactory", 
	transactionManagerRef = "profileTransactionManager", basePackages = "com.yoda.webservice.repository.profile")
public class ProfileDatabaseConfig {

	@Bean(name = "profileDataSource")
	@ConfigurationProperties(prefix = "profile.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "profileEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean profileEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("profileDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.yoda.webservice.entity.profile").persistenceUnit("profile")
				.build();
	}

	@Bean(name = "profileTransactionManager")
	public PlatformTransactionManager profileTransactionManager(
			@Qualifier("profileEntityManagerFactory") EntityManagerFactory profileEntityManagerFactory) {
		return new JpaTransactionManager(profileEntityManagerFactory);
	}
}