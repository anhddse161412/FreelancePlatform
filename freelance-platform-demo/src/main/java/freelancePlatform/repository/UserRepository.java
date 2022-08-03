package freelancePlatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import freelancePlatform.domain.Job;
import freelancePlatform.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
	boolean existsByEmail(String email);
	//Tim tat ca user containing email
	@Query(value = "SELECT * FROM users u WHERE u.email LIKE %:email%",
			countQuery = "SELECT count(*) FROM users u WHERE u.email LIKE %:email%",
			nativeQuery = true)
	Page<User> findAllUserByEmail(@Param("email") String title, Pageable pageable);
		
	//Tim tat ca user containing email va role
	@Query(value = "SELECT * FROM users u WHERE u.email LIKE %:email% AND u.role=:role",
			countQuery = "SELECT count(*) FROM users u WHERE u.email LIKE %:email% AND u.role=:role",
			nativeQuery = true)
	Page<User> findAllUserByEmailRole(@Param("email") String title,@Param("role") int role, Pageable pageable);
	
	@Query(value = "SELECT count(*) FROM users u WHERE u.role!=1", nativeQuery = true)
	int findCountAllUser();
	
	@Query(value = "SELECT count(*) FROM users u WHERE u.role=:role", nativeQuery = true)
	int findCountAllUserByRole(@Param("role") int role);
}
