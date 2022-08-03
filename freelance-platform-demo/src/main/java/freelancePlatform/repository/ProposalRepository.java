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

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long> {
	//Tim proposal 1 job co status 1
	@Query(value = "SELECT * FROM proposals p WHERE p.job_id= :jobid AND p.status=1",
			nativeQuery = true)
	List<Proposal> findAllByJobStatus1(@Param("jobid") Long jobid);
	
	//Tim proposal giua 1 freelancer va 1 job co status khac 1(processing) va 2(approved)
	@Query(value = "SELECT * FROM proposals p WHERE p.user_id= :userid AND p.job_id= :jobid AND (p.status=1 OR p.status=2)",
			nativeQuery = true)
	List<Proposal> findAllByUserJobStatus1Or2(@Param("userid") Long userid, @Param("jobid") Long jobid);
	
	//Tim tat ca proposal cua 1 user id
	@Query(value = "SELECT * FROM proposals p WHERE p.user_id=:userid",
			countQuery = "SELECT count(*) FROM proposals p WHERE p.user_id=:userid",
			nativeQuery = true)
	Page<Proposal> findAllProposalsByUser(@Param("userid") Long userid,Pageable pageable);
	
	//Tim tat ca proposal cua 1 user id co job title search
	@Query(value = "SELECT * FROM proposals p JOIN jobs j ON p.job_id=j.job_id WHERE p.user_id= :userid AND j.title LIKE %:title%",
			countQuery = "SELECT COUNT(*) FROM proposals p JOIN jobs j ON p.job_id=j.job_id WHERE p.user_id= :userid AND j.title LIKE %:title%",
			nativeQuery = true)
	Page<Proposal> findAllProposalsByUserAndTitle(@Param("userid") Long userid,@Param("title") String title,Pageable pageable);
	
	//Tim tat ca proposal cua 1 user id co job title search va status
	@Query(value = "SELECT * FROM proposals p JOIN jobs j ON p.job_id=j.job_id WHERE p.user_id=:userid AND j.title LIKE %:title% AND p.status=:status",
			countQuery = "SELECT COUNT(*) FROM proposals p JOIN jobs j ON p.job_id=j.job_id WHERE p.user_id=:userid AND j.title LIKE %:title% AND p.status=:status",
			nativeQuery = true)
	Page<Proposal> findAllProposalsByUserAndTitleAndStatus(@Param("userid") Long userid,@Param("title") String title,@Param("status") int status,Pageable pageable);

	//Tim tat ca proposal cua 1 job co user id co job title search
	@Query(value = "SELECT * FROM proposals p JOIN jobs j ON p.job_id=j.job_id WHERE j.user_id=:userid AND j.title LIKE %:title% AND p.status=1",
			countQuery = "SELECT COUNT(*) FROM proposals p JOIN jobs j ON p.job_id=j.job_id WHERE j.user_id=:userid AND j.title LIKE %:title% AND p.status=1",
			nativeQuery = true)
	Page<Proposal> findAllProposalsByJobUserAndTitle(@Param("userid") Long userid,@Param("title") String title,Pageable pageable);

	//Tim tat ca proposal cua 1 user id
	@Query(value = "SELECT * FROM proposals p JOIN jobs j ON p.job_id=j.job_id WHERE j.user_id=:userid AND p.status=1",
			countQuery = "SELECT count(*) FROM proposals p JOIN jobs j ON p.job_id=j.job_id WHERE j.user_id=:userid AND p.status=1",
			nativeQuery = true)
	Page<Proposal> findAllProposalsByJobUser(@Param("userid") Long userid,Pageable pageable);
	
	//Reject tat ca proposal cua 1 job
	@Transactional
	@Modifying
	@Query(value ="UPDATE proposals SET status=0 WHERE job_id=:jobid",nativeQuery = true)
	void rejectAllProposalByJobID(@Param("jobid") Long jobid);
	
	//Tim proposal co status la 2 cua 1 job voi jobid
	@Query(value = "SELECT * FROM proposals p join jobs j ON p.job_id=j.job_id\r\n"
			+ "WHERE p.status=2 AND j.job_id=:jobid",
			nativeQuery = true)
	Proposal findApprovedProposalByJobID(@Param("jobid") Long jobid);
	
	//Tim tat ca proposal cua 1 client
	@Query(value = "SELECT count(*) FROM proposals p JOIN jobs j ON p.job_id=j.job_id WHERE j.user_id=:userid",
			nativeQuery = true)
	int findCountAllProposalsByJobUser(@Param("userid") Long userid);
	
	//Tim tat ca proposal cua 1 client va status
	@Query(value = "SELECT count(*) FROM proposals p JOIN jobs j ON p.job_id=j.job_id WHERE j.user_id=:userid AND p.status=:status",
			nativeQuery = true)
	int findCountAllProposalsByJobUserStatus(@Param("userid") Long userid,@Param("status") int status);
	
	//Tim tat ca proposal cua 1 freelancer
	@Query(value = "SELECT count(*) FROM proposals p WHERE p.user_id=:userid",
			nativeQuery = true)
	int findCountAllProposalsByFreelancer(@Param("userid") Long userid);
	
	//Tim tat ca proposal cua 1 freelancer va status
	@Query(value = "SELECT count(*) FROM proposals p WHERE p.user_id=:userid AND p.status=:status",
			nativeQuery = true)
	int findCountAllProposalsByFreelancer(@Param("userid") Long userid,@Param("status") int status);
}
