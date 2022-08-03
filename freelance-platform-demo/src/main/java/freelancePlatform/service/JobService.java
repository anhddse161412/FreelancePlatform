package freelancePlatform.service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.web.multipart.MultipartFile;

import freelancePlatform.domain.Job;

public interface JobService {

	<S extends Job> List<S> findAll(Example<S> example, Sort sort);

	<S extends Job> List<S> findAll(Example<S> example);

	Job getReferenceById(Long id);

	Job getById(Long id);

	void deleteAll();

	void deleteAll(Iterable<? extends Job> entities);

	Job getOne(Long id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Long> ids);

	<S extends Job, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(Job entity);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteById(Long id);

	<S extends Job> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<Job> entities);

	<S extends Job> long count(Example<S> example);

	void deleteInBatch(Iterable<Job> entities);

	<S extends Job> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Job> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Job> S saveAndFlush(S entity);

	boolean existsById(Long id);

	void flush();

	<S extends Job> List<S> saveAll(Iterable<S> entities);

	Optional<Job> findById(Long id);

	List<Job> findAllById(Iterable<Long> ids);

	List<Job> findAll(Sort sort);

	Page<Job> findAll(Pageable pageable);

	List<Job> findAll();

	<S extends Job> Optional<S> findOne(Example<S> example);

	<S extends Job> S save(S entity, int status);

	List<Job> findAllByUserId(Long userId);

	List<Job> findAllByStatus(int status);


	Page<Job> findAllJobByTitleHiring(String title, Pageable pageable);

	Page<Job> findAllJobHiring(Pageable pageable);

	Page<Job> findAllJobUserParam(Long userid, Pageable pageable);

	Page<Job> findAllJobByTitleUserParam(Long userid, String title, Pageable pageable);

	Page<Job> findAllJobByTitleAndFieldHiring(String title, Long fieldid, Pageable pageable);

	Page<Job> findAllJobByTitleAndFieldUserParam(Long userid, String title, Long fieldid, Pageable pageable);

	List<Job> findAllJobByUserHiring(Long userid);

	Page<Job> findAllJobByTitleAndStatusUserParam(Long userid, String title, int status, Pageable pageable);

	Page<Job> findAllJobsByFreelancerAndTitleAndStatus(Long userid, String title, int status, Pageable pageable);

	List<Job> findAllJobsByFreelancerAndTitleAndStatusList(Long userid, String title, int status);

	int findCountAllJobByUserStatus(Long userid, int status);

	int findCountAllJobByUserAvailable(Long userid);

	int findCountAllJobsByFreelancerAndStatus(Long userid, int status);

	int findCountAllActiveJobByStatus(int status);

	int findCountAllActiveJob();

	Page<Job> findAllJobByTitleAndStatus(String title, int status, Pageable pageable);

	Page<Job> findAllJobByTitle(String title, Pageable pageable);

	int findCountAllJobByUserAvailableByField(Long userid, Long fieldid);

	int findCountAllActiveJobByField(Long fieldid);

}
