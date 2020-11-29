package de.clumsystuff.mysql.transactions;

import static org.hamcrest.CoreMatchers.is;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import de.clumsystuff.mysql.transactions.model.DataObject;

@SpringBootTest
public class TransactionTest {

    @Autowired
    private DataObjectDataStore dataObjectDataStore;

    @Autowired
    private OuterLayer outerLayer;

    @BeforeEach
    public void cleanUp() {
        this.dataObjectDataStore.clearAll();
    }

    @Test
    public void commitInCaseOfNoError() {

        this.outerLayer.saveSuccess(new DataObject("Tony", "Test"), new DataObject("Tina", "Test"));

        
        MatcherAssert.assertThat(this.dataObjectDataStore.readAll().size(), is(2));
    }

    @Test
    public void rollbackInCaseOfError() {

        try {
            this.outerLayer.saveFail(new DataObject("Tony", "Test"), new DataObject("Tina", "Test"));
        } catch (Exception e) {
        }

        MatcherAssert.assertThat(this.dataObjectDataStore.readAll().size(), is(0));
    }
}
