package csc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import csc.models.Company;
import java.lang.String;
import java.util.List;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {
	Company findByNameCpn(String namecpn);
}
