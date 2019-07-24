package de.clumsystuff.mysql.transactions;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface DataObjectRepository extends CrudRepository<DataObject, String> {
}
