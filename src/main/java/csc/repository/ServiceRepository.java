package csc.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import csc.models.Service;
import csc.models.TypeInvoice;

import java.lang.String;
import java.util.List;
@Repository
public interface ServiceRepository extends CrudRepository<Service, Long> {
	
	List<Service> findByNameService(String nameservice);
	
	Service findByNameServiceAndIdType(String nameservice, TypeInvoice idType);
	
	List<Service> findByIdType(TypeInvoice idtype);
	
}
