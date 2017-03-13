package csc.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import csc.models.Invoice;
import csc.models.Report;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long> {
	@Query(value = "select * from Report u where u.id_customer = ?#{[0]} and u.date between ?#{[1]} and ?#{[2]} ORDER BY ?#{#pageable}", nativeQuery=true)
	Page<Report> findReport(String idCus, String dateStart, String dateEnd, Pageable pageable);
}
