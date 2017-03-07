package csc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import csc.models.Company;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {

}
