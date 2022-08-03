package freelancePlatform.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import freelancePlatform.domain.CustomerProfile;

public interface CustomerProfileService {

	<S extends CustomerProfile> List<S> findAll(Example<S> example, Sort sort);

	<S extends CustomerProfile> List<S> findAll(Example<S> example);

	CustomerProfile getReferenceById(Long id);

	CustomerProfile getById(Long id);

	void deleteAll();

	void deleteAll(Iterable<? extends CustomerProfile> entities);

	CustomerProfile getOne(Long id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Long> ids);

	<S extends CustomerProfile, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(CustomerProfile entity);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteById(Long id);

	<S extends CustomerProfile> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<CustomerProfile> entities);

	<S extends CustomerProfile> long count(Example<S> example);

	void deleteInBatch(Iterable<CustomerProfile> entities);

	<S extends CustomerProfile> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends CustomerProfile> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends CustomerProfile> S saveAndFlush(S entity);

	boolean existsById(Long id);

	void flush();

	<S extends CustomerProfile> List<S> saveAll(Iterable<S> entities);

	Optional<CustomerProfile> findById(Long id);

	List<CustomerProfile> findAllById(Iterable<Long> ids);

	List<CustomerProfile> findAll(Sort sort);

	Page<CustomerProfile> findAll(Pageable pageable);

	List<CustomerProfile> findAll();

	<S extends CustomerProfile> Optional<S> findOne(Example<S> example);

	<S extends CustomerProfile> S save(S entity);

	CustomerProfile findByUserId(Long userId);

}
