package de.clumsystuff.mysql.transactions.repositories1;

import org.springframework.data.repository.CrudRepository;

import de.clumsystuff.mysql.transactions.model.DataObject;

public interface DataObject1Repository extends CrudRepository<DataObject, Long> {
}
