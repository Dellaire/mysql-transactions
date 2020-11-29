package de.clumsystuff.mysql.transactions.repositories2;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackageClasses = {
		DataObject2Repository.class }, entityManagerFactoryRef = "entityManagerFactory2", transactionManagerRef = "transactionManager2")
public class Database2Configuration {

	@Bean
	public DataSource dataSource2() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3307/db?createDatabaseIfNotExist=true");
		dataSource.setUsername("root");
		dataSource.setPassword("password");

		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory2(DataSource dataSource2) {

		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource2);
		entityManagerFactory.setPackagesToScan(new String[] { "de.clumsystuff.mysql.transactions.model" });

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		entityManagerFactory.setJpaVendorAdapter(vendorAdapter);

		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "create");
		entityManagerFactory.setJpaPropertyMap(properties);

		return entityManagerFactory;
	}

	@Bean
	public PlatformTransactionManager transactionManager2(
			@Qualifier("entityManagerFactory2") LocalContainerEntityManagerFactoryBean entityManagerFactory2) {

		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory2.getObject());

		return transactionManager;
	}
}
