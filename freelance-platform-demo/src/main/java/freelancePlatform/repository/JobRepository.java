package freelancePlatform.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import freelancePlatform.domain.Field;
import freelancePlatform.domain.Job;
import freelancePlatform.domain.Proposal;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
	
	//Tim job khong phai status 0 cua 1 userid
	@Query(value = "SELECT * FROM jobs j WHERE j.user_id = :userid AND j.status!=0", nativeQuery = true)
	List<Job> findAllJobByUserParam(@Param("userid") Long userid);
	
	//Tim job status 1 cua 1 userid
	@Query(value = "SELECT * FROM jobs j WHERE j.user_id = :userid AND j.status=1", nativeQuery = true)
	List<Job> findAllJobByUserHiring(@Param("userid") Long userid);
	
	//Tim tat ca job theo status
	List<Job> findAllByStatus(int status);
	
	//Tim tat ca job co status 1 va containing title: alljobs
	@Query(value = "SELECT * FROM jobs j WHERE j.title LIKE %:title% AND j.status=1",
			countQuery = "SELECT count(*) FROM jobs j WHERE j.title LIKE %:title% AND j.status=1",
			nativeQuery = true)
	Page<Job> findAllJobByTitleHiring(@Param("title") String title, Pageable pageable);
	
	//Tim tat ca job co status 1 theo field va containing title: alljobs
	@Query(value = "SELECT * FROM jobs j WHERE j.title LIKE %:title% AND j.status=1 AND j.field_id=:fieldid",
			countQuery = "SELECT count(*) FROM jobs j WHERE j.title LIKE %:title% AND j.status=1 AND j.field_id=:fieldid",
			nativeQuery = true)
	Page<Job> findAllJobByTitleAndFieldHiring(@Param("title") String title,@Param("fieldid") Long fieldid, Pageable pageable);
	
	//Tim tat ca job co status 1: alljobs
	@Query(value = "SELECT * FROM jobs j WHERE j.status=1",
			countQuery = "SELECT count(*) FROM jobs j WHERE j.status = 1",
			nativeQuery = true)
	Page<Job> findAllJobHiring(Pageable pageable);
	
	//Tim tat ca job status khac 0 cua 1 userid containing title:myjobs
	@Query(value = "SELECT * FROM jobs j WHERE j.title LIKE %:title% AND j.status!=0 AND j.user_id = :userid",
			countQuery = "SELECT count(*) FROM jobs j WHERE j.title LIKE %:title% AND j.status!=0 AND j.user_id = :userid",
			nativeQuery = true)
	Page<Job> findAllJobByTitleUserParam(@Param("userid") Long userid, @Param("title") String title, Pageable pageable);
	
	//Tim tat ca job status khac 0 cua 1 userid containing title va field : myjobs
	@Query(value = "SELECT * FROM jobs j WHERE j.title LIKE %:title% AND j.status!=0 AND j.user_id = :userid AND j.field_id=:fieldid",
			countQuery = "SELECT count(*) FROM jobs j WHERE j.title LIKE %:title% AND j.status!=0 AND j.user_id = :userid AND j.field_id=:fieldid",
			nativeQuery = true)
	Page<Job> findAllJobByTitleAndFieldUserParam(@Param("userid") Long userid, @Param("title") String title,@Param("fieldid") Long fieldid, Pageable pageable);
	
	//Tim tat ca job status khac 0 cua 1 userid
	@Query(value = "SELECT * FROM jobs j WHERE j.user_id = :userid AND j.status!=0",
			countQuery = "SELECT count(*) FROM jobs j WHERE j.user_id = :userid AND j.status!=0",
			nativeQuery = true)
	Page<Job> findAllJobUserParam(@Param("userid") Long userid,Pageable pageable);
	
	//Tim tat ca job containing title va status : myjobs
	@Query(value = "SELECT * FROM jobs j WHERE j.title LIKE %:title% AND j.user_id = :userid AND j.status=:status",
			countQuery = "SELECT count(*) FROM jobs j WHERE j.title LIKE %:title% AND j.user_id = :userid AND j.status=:status",
			nativeQuery = true)
	Page<Job> findAllJobByTitleAndStatusUserParam(@Param("userid") Long userid, @Param("title") String title,@Param("status") int status, Pageable pageable);
	
	//Tim tat ca proposal cua 1 user id co job title search pagination
	@Query(value = "SELECT * FROM jobs j JOIN proposals p ON j.job_id=p.job_id WHERE p.user_id= :userid AND j.title LIKE %:title% AND j.status=:status AND p.status=2",
			countQuery = "SELECT COUNT(*) FROM jobs j JOIN proposals p ON j.job_id=p.job_id WHERE p.user_id= :userid AND j.title LIKE %:title% AND j.status=:status AND p.status=2",
			nativeQuery = true)
	Page<Job> findAllJobsByFreelancerAndTitleAndStatus(@Param("userid") Long userid,@Param("title") String title,@Param("status") int status,Pageable pageable);
	
	//Tim tat ca proposal cua 1 user id co job title search list
	@Query(value = "SELECT * FROM jobs j JOIN proposals p ON j.job_id=p.job_id WHERE p.user_id= :userid AND j.title LIKE %:title% AND j.status=:status AND p.status=2",
			nativeQuery = true)
	List<Job> findAllJobsByFreelancerAndTitleAndStatusList(@Param("userid") Long userid,@Param("title") String title,@Param("status") int status);

	@Query(value = "SELECT count(*) FROM jobs j WHERE j.user_id = :userid AND j.status!=0", nativeQuery = true)
	int findCountAllJobByUserAvailable(@Param("userid") Long userid);
	
	@Query(value = "SELECT count(*) FROM jobs j WHERE j.user_id = :userid AND j.status=:status", nativeQuery = true)
	int findCountAllJobByUserStatus(@Param("userid") Long userid,@Param("status") int status);
	
	@Query(value = "SELECT Count(*) FROM jobs j JOIN proposals p ON j.job_id=p.job_id WHERE p.user_id= :userid AND j.status=:status AND p.status=2",
			nativeQuery = true)
	int findCountAllJobsByFreelancerAndStatus(@Param("userid") Long userid,@Param("status") int status);
	
	@Query(value = "SELECT count(*) FROM jobs j WHERE j.status!=0", nativeQuery = true)
	int findCountAllActiveJob();
	
	@Query(value = "SELECT count(*) FROM jobs j WHERE j.status=:status", nativeQuery = true)
	int findCountAllActiveJobByStatus(@Param("status") int status);
	
	@Query(value = "SELECT count(*) FROM jobs j WHERE j.user_id = :userid AND j.status!=0 AND j.field_id=:fieldid", nativeQuery = true)
	int findCountAllJobByUserAvailableByField(@Param("userid") Long userid, @Param("fieldid") Long fieldid);
	
	@Query(value = "SELECT count(*) FROM jobs j WHERE j.status!=0 AND j.field_id=:fieldid", nativeQuery = true)
	int findCountAllActiveJobByField(@Param("fieldid") Long fieldid);
	
	//Tim tat ca job co status !=0 va containing title: alljobs
	@Query(value = "SELECT * FROM jobs j WHERE j.title LIKE %:title% AND j.status!=0",
			countQuery = "SELECT count(*) FROM jobs j WHERE j.title LIKE %:title% AND j.status!=0",
			nativeQuery = true)
	Page<Job> findAllJobByTitle(@Param("title") String title, Pageable pageable);
	
	//Tim tat ca job co status !=0 theo field va containing title: alljobs
	@Query(value = "SELECT * FROM jobs j WHERE j.title LIKE %:title% AND j.status=:status",
			countQuery = "SELECT count(*) FROM jobs j WHERE j.title LIKE %:title% AND j.status=:status",
			nativeQuery = true)
	Page<Job> findAllJobByTitleAndStatus(@Param("title") String title,@Param("status") int status, Pageable pageable);
}
