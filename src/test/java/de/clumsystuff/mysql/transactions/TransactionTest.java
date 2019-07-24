package de.clumsystuff.mysql.transactions;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionTest {

	@Autowired
	private DataObjectDataStore dataObjectDataStore;

	@After
	public void cleanUp() {
		this.dataObjectDataStore.clearAll();
	}

	@Test
	public void commitInCaseOfNoError() {

		this.dataObjectDataStore.saveSuccess(new DataObject("Tony", "Test"));

		assertThat(this.dataObjectDataStore.readAll().size(), is(2));
	}

	@Test
	public void rollbackInCaseOfError() {

		try {
			this.dataObjectDataStore.saveFail(new DataObject("Tony", "Test"));
		} catch (Exception e) {
		}

		assertThat(this.dataObjectDataStore.readAll().size(), is(0));
	}
}
