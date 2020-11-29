package de.clumsystuff.mysql.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import de.clumsystuff.mysql.transactions.model.DataObject;
import de.clumsystuff.mysql.transactions.repositories1.DataObject1Repository;
import de.clumsystuff.mysql.transactions.repositories2.DataObject2Repository;

@Component
public class TestRunner implements CommandLineRunner {

	@Autowired
	private DataObject1Repository data1ObjectRepository;

	@Autowired
	private DataObject2Repository data2ObjectRepository;

	@Override
	public void run(String... args) throws Exception {

		DataObject dataObject1 = new DataObject();
		dataObject1.setFirstName("Toni");
		dataObject1.setLastName("Test");

		DataObject dataObject2 = new DataObject();
		dataObject2.setFirstName("Tina");
		dataObject2.setLastName("Test");

		this.data1ObjectRepository.save(dataObject1);
		this.data2ObjectRepository.save(dataObject2);

		this.data1ObjectRepository.findAll()
				.forEach(do1 -> System.out.println("data1ObjectRepository: " + do1.getFirstName()));
		this.data2ObjectRepository.findAll()
				.forEach(do2 -> System.out.println("data2ObjectRepository: " + do2.getFirstName()));
	}
}
