package freelancePlatform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import freelancePlatform.domain.FreelancerProfile;

@Repository
public interface FreelancerProfileRepository extends JpaRepository<FreelancerProfile, Long> {
	@Query(value = "SELECT * FROM freelancer_profiles f WHERE f.user_id = :userid", nativeQuery = true)
	FreelancerProfile findByUserId(Long userid);
}
