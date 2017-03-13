package csc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import csc.models.Report;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long> {
	Page<Report> findAll(Pageable pageable);
}
