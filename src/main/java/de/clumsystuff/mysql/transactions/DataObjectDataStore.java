package de.clumsystuff.mysql.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class DataObjectDataStore {

    @Autowired
    private DataObjectRepository dataObjectRepository;

    public void saveSuccess(DataObject dataObject1, DataObject dataObject2) {

        this.dataObjectRepository.save(dataObject1);
        this.dataObjectRepository.save(dataObject2);
    }

    @Transactional
    public void saveFail(DataObject dataObject1, DataObject dataObject2) {

        this.dataObjectRepository.save(dataObject1);
        if (true) {
            throw new RuntimeException("ERROR");
        }
        this.dataObjectRepository.save(dataObject2);
    }

    public List<DataObject> readAll() {

        return (List<DataObject>) this.dataObjectRepository.findAll();
    }

    public void clearAll() {

        this.dataObjectRepository.deleteAll();
    }
}
