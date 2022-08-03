package freelancePlatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import freelancePlatform.domain.Field;
import freelancePlatform.domain.User;

@Repository
public interface FieldRepository extends JpaRepository<Field, Long> {
	Field findByName(String name);
	Page<Field> findByNameContaining(String name, Pageable pageable);
}
