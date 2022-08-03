package freelancePlatform.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import freelancePlatform.domain.Proposal;
import freelancePlatform.domain.Report;

public interface ReportService {

	<S extends Report> List<S> findAll(Example<S> example, Sort sort);

	<S extends Report> List<S> findAll(Example<S> example);

	Report getReferenceById(Long id);

	Report getById(Long id);

	void deleteAll();

	void deleteAll(Iterable<? extends Report> entities);

	Report getOne(Long id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Long> ids);

	<S extends Report, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(Report entity);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteById(Long id);

	<S extends Report> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<Report> entities);

	<S extends Report> long count(Example<S> example);

	void deleteInBatch(Iterable<Report> entities);

	<S extends Report> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Report> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Report> S saveAndFlush(S entity);

	boolean existsById(Long id);

	void flush();

	<S extends Report> List<S> saveAll(Iterable<S> entities);

	Optional<Report> findById(Long id);

	List<Report> findAllById(Iterable<Long> ids);

	List<Report> findAll(Sort sort);

	Page<Report> findAll(Pageable pageable);

	List<Report> findAll();

	<S extends Report> Optional<S> findOne(Example<S> example);

	<S extends Report> S save(S entity);

	Report findByJobId(Long jobId);

}
