package de.clumsystuff.mysql.transactions.repositories1;

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
		DataObject1Repository.class }, entityManagerFactoryRef = "entityManagerFactory1", transactionManagerRef = "transactionManager1")
public class Database1Configuration {

	@Bean
	public DataSource dataSource1() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/db?createDatabaseIfNotExist=true");
		dataSource.setUsername("root");
		dataSource.setPassword("password");

		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory1(DataSource dataSource1) {

		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource1);
		entityManagerFactory.setPackagesToScan(new String[] { "de.clumsystuff.mysql.transactions.model" });

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		entityManagerFactory.setJpaVendorAdapter(vendorAdapter);

		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "create");
		entityManagerFactory.setJpaPropertyMap(properties);

		return entityManagerFactory;
	}

	@Bean
	public PlatformTransactionManager transactionManager1(
			@Qualifier("entityManagerFactory1") LocalContainerEntityManagerFactoryBean entityManagerFactory1) {

		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory1.getObject());

		return transactionManager;
	}
}
