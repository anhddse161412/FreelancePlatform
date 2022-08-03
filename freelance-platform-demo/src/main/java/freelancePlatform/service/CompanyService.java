package freelancePlatform.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import freelancePlatform.domain.Company;

public interface CompanyService {

	<S extends Company> List<S> findAll(Example<S> example, Sort sort);

	<S extends Company> List<S> findAll(Example<S> example);

	Company getReferenceById(Long id);

	Company getById(Long id);

	void deleteAll();

	void deleteAll(Iterable<? extends Company> entities);

	Company getOne(Long id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Long> ids);

	<S extends Company, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(Company entity);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteById(Long id);

	<S extends Company> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<Company> entities);

	<S extends Company> long count(Example<S> example);

	void deleteInBatch(Iterable<Company> entities);

	<S extends Company> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Company> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Company> S saveAndFlush(S entity);

	boolean existsById(Long id);

	void flush();

	<S extends Company> List<S> saveAll(Iterable<S> entities);

	Optional<Company> findById(Long id);

	List<Company> findAllById(Iterable<Long> ids);

	List<Company> findAll(Sort sort);

	Page<Company> findAll(Pageable pageable);

	List<Company> findAll();

	<S extends Company> Optional<S> findOne(Example<S> example);

	<S extends Company> S save(S entity);

	Page<Company> findByNameContaining(String name, Pageable pageable);

	Company findByName(String name);

}