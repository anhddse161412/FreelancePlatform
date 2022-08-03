package freelancePlatform.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import freelancePlatform.domain.Job;
import freelancePlatform.domain.Proposal;
import freelancePlatform.domain.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
	@Query(value = "SELECT * FROM reports r WHERE r.job_id = :jobid", nativeQuery = true)
	Report findByJobId(@Param("jobid") Long jobId);
}
