package freelancePlatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import freelancePlatform.domain.Company;
import freelancePlatform.domain.Field;
import freelancePlatform.domain.User;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
	Company findByName(String name);
	Page<Company> findByNameContaining(String name, Pageable pageable);
}