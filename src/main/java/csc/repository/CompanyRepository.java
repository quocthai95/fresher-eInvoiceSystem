package csc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import csc.models.Company;
import csc.models.TypeInvoice;
import java.util.List;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {
	Company findByNameCpn(String namecpn);

	Company findById(Integer id);
	
	List<Company> findByIdType(TypeInvoice idtype);
}
