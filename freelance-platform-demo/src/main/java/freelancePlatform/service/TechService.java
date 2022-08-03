package freelancePlatform.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import freelancePlatform.domain.Tech;

public interface TechService {

	<S extends Tech> List<S> findAll(Example<S> example, Sort sort);

	<S extends Tech> List<S> findAll(Example<S> example);

	Tech getReferenceById(Long id);

	Tech getById(Long id);

	void deleteAll();

	void deleteAll(Iterable<? extends Tech> entities);

	Tech getOne(Long id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Long> ids);

	<S extends Tech, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(Tech entity);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteById(Long id);

	<S extends Tech> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<Tech> entities);

	<S extends Tech> long count(Example<S> example);

	void deleteInBatch(Iterable<Tech> entities);

	<S extends Tech> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Tech> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Tech> S saveAndFlush(S entity);

	boolean existsById(Long id);

	void flush();

	<S extends Tech> List<S> saveAll(Iterable<S> entities);

	Optional<Tech> findById(Long id);

	List<Tech> findAllById(Iterable<Long> ids);

	List<Tech> findAll(Sort sort);

	Page<Tech> findAll(Pageable pageable);

	List<Tech> findAll();

	<S extends Tech> Optional<S> findOne(Example<S> example);

	<S extends Tech> S save(S entity);

	List<Tech> findByNameContaining(String name);

	Tech findByName(String name);

}
