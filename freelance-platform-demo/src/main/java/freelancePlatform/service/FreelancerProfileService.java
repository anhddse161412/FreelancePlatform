package freelancePlatform.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import freelancePlatform.domain.FreelancerProfile;

public interface FreelancerProfileService {

	<S extends FreelancerProfile> List<S> findAll(Example<S> example, Sort sort);

	<S extends FreelancerProfile> List<S> findAll(Example<S> example);

	FreelancerProfile getReferenceById(Long id);

	FreelancerProfile getById(Long id);

	void deleteAll();

	void deleteAll(Iterable<? extends FreelancerProfile> entities);

	FreelancerProfile getOne(Long id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Long> ids);

	<S extends FreelancerProfile, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(FreelancerProfile entity);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteById(Long id);

	<S extends FreelancerProfile> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<FreelancerProfile> entities);

	<S extends FreelancerProfile> long count(Example<S> example);

	void deleteInBatch(Iterable<FreelancerProfile> entities);

	<S extends FreelancerProfile> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends FreelancerProfile> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends FreelancerProfile> S saveAndFlush(S entity);

	boolean existsById(Long id);

	void flush();

	<S extends FreelancerProfile> List<S> saveAll(Iterable<S> entities);

	Optional<FreelancerProfile> findById(Long id);

	List<FreelancerProfile> findAllById(Iterable<Long> ids);

	List<FreelancerProfile> findAll(Sort sort);

	Page<FreelancerProfile> findAll(Pageable pageable);

	List<FreelancerProfile> findAll();

	<S extends FreelancerProfile> Optional<S> findOne(Example<S> example);

	<S extends FreelancerProfile> S save(S entity);

	FreelancerProfile findByUserId(Long userid);

}
