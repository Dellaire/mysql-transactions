package de.clumsystuff.mysql.transactions;

import static org.hamcrest.CoreMatchers.is;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import de.clumsystuff.mysql.transactions.model.DataObject;
import de.clumsystuff.mysql.transactions.repositories1.DataObject1Repository;
import de.clumsystuff.mysql.transactions.repositories2.DataObject2Repository;

@SpringBootTest
public class MultipleRepositoriesTest {

	@Autowired
	private DataObject1Repository dataObject1Repository;

	@Autowired
	private DataObject2Repository dataObject2Repository;

	@BeforeEach
	public void cleanUp() {
		this.dataObject1Repository.deleteAll();
		this.dataObject2Repository.deleteAll();
	}

	@Test
	public void commitInCaseOfNoError() {

		DataObject dataObject1 = new DataObject();
		dataObject1.setFirstName("Toni");
		dataObject1.setLastName("Test");

		DataObject dataObject2 = new DataObject();
		dataObject2.setFirstName("Tina");
		dataObject2.setLastName("Test");

		this.dataObject1Repository.save(dataObject1);
		this.dataObject2Repository.save(dataObject2);

		List<DataObject> dataObjects1 = StreamSupport.stream(this.dataObject1Repository.findAll().spliterator(), false)
				.collect(Collectors.toList());
		List<DataObject> dataObjects2 = StreamSupport.stream(this.dataObject2Repository.findAll().spliterator(), false)
				.collect(Collectors.toList());

		MatcherAssert.assertThat(dataObjects1.size(), is(1));
		MatcherAssert.assertThat(dataObjects1.get(0).getFirstName(), is("Toni"));
		MatcherAssert.assertThat(dataObjects2.size(), is(1));
		MatcherAssert.assertThat(dataObjects2.get(0).getFirstName(), is("Tina"));
	}
}
