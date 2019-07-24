package de.clumsystuff.mysql.transactions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataObjectDataStore {

	@Autowired
	private DataObjectRepository dataObjectRepository;

	public void saveSuccess(DataObject dataObject) {

		this.dataObjectRepository.save(dataObject);
		this.dataObjectRepository.save(dataObject);
	}

	@Transactional
	public void saveFail(DataObject dataObject) throws Exception {

		this.dataObjectRepository.save(dataObject);
		if (true) {
			throw new Exception("ERROR");
		}
		this.dataObjectRepository.save(dataObject);
	}

	public List<DataObject> readAll() {

		return (List<DataObject>) this.dataObjectRepository.findAll();
	}

	public void clearAll() {

		this.dataObjectRepository.deleteAll();
	}
}
