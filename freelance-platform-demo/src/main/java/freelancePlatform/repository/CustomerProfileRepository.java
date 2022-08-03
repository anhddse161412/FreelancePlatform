package freelancePlatform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import freelancePlatform.domain.CustomerProfile;

@Repository
public interface CustomerProfileRepository extends JpaRepository<CustomerProfile, Long> {
	@Query(value = "SELECT * FROM customer_profiles c WHERE c.user_id = :userid", nativeQuery = true)
	CustomerProfile findByUserId(Long userid);
}
