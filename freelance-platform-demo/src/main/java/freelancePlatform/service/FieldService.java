package freelancePlatform.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import freelancePlatform.domain.Field;

public interface FieldService {

	<S extends Field> List<S> findAll(Example<S> example, Sort sort);

	<S extends Field> List<S> findAll(Example<S> example);

	Field getReferenceById(Long id);

	Field getById(Long id);

	void deleteAll();

	void deleteAll(Iterable<? extends Field> entities);

	Field getOne(Long id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Long> ids);

	<S extends Field, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(Field entity);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteById(Long id);

	<S extends Field> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<Field> entities);

	<S extends Field> long count(Example<S> example);

	void deleteInBatch(Iterable<Field> entities);

	<S extends Field> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Field> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Field> S saveAndFlush(S entity);

	boolean existsById(Long id);

	void flush();

	<S extends Field> List<S> saveAll(Iterable<S> entities);

	Optional<Field> findById(Long id);

	List<Field> findAllById(Iterable<Long> ids);

	List<Field> findAll(Sort sort);

	Page<Field> findAll(Pageable pageable);

	List<Field> findAll();

	<S extends Field> Optional<S> findOne(Example<S> example);

	<S extends Field> S save(S entity);

	Page<Field> findByNameContaining(String name, Pageable pageable);

	Field findByName(String name);

}
