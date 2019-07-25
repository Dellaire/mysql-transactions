package de.clumsystuff.mysql.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class OuterLayer {

    @Autowired
    private DataObjectDataStore dataObjectDataStore;

    public void saveSuccess(DataObject dataObject1, DataObject dataObject2) {
        this.dataObjectDataStore.saveSuccess(dataObject1, dataObject2);
    }

    @Transactional
    public void saveFail(DataObject dataObject1, DataObject dataObject2) {
        this.dataObjectDataStore.saveFail(dataObject1, dataObject2);
    }
}
