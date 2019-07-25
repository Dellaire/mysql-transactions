package de.clumsystuff.mysql.transactions;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionTest {

    @Autowired
    private DataObjectDataStore dataObjectDataStore;

    @Autowired
    private OuterLayer outerLayer;

    @Before
    public void cleanUp() {
        this.dataObjectDataStore.clearAll();
    }

    @Test
    public void commitInCaseOfNoError() {

        this.outerLayer.saveSuccess(new DataObject("Tony", "Test"), new DataObject("Tina", "Test"));

        assertThat(this.dataObjectDataStore.readAll().size(), is(2));
    }

    @Test
    public void rollbackInCaseOfError() {

        try {
            this.outerLayer.saveFail(new DataObject("Tony", "Test"), new DataObject("Tina", "Test"));
        } catch (Exception e) {
        }

        assertThat(this.dataObjectDataStore.readAll().size(), is(0));
    }
}
