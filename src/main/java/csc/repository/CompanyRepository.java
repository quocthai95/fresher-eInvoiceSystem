package csc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import csc.models.Company;
import java.lang.String;
import java.util.List;
import java.lang.Integer;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {
	Company findByNameCpn(String namecpn);

	Company findById(Integer id);
}
