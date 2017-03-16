package csc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import csc.models.TypeInvoice;
import java.lang.String;
import java.util.List;

@Repository
public interface TypeInvoiceRepository extends CrudRepository<TypeInvoice, Integer> {
	TypeInvoice findByNameInvoice(String nameinvoice);
	
	TypeInvoice findById(Integer id);
}
