package freelancePlatform.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import freelancePlatform.domain.Job;
import freelancePlatform.domain.User;
import freelancePlatform.repository.JobRepository;
import freelancePlatform.service.JobService;

@Service
public class JobServiceImpl implements JobService {
	@Autowired
	JobRepository jobRepository;
	
	@Autowired
	HttpSession session;
	
	@Override
	public List<Job> findAllByStatus(int status){
		return jobRepository.findAllByStatus(status);
	}

	
	@Override
	public int findCountAllActiveJobByField(Long fieldid) {
		return jobRepository.findCountAllActiveJobByField(fieldid);
	}


	@Override
	public Page<Job> findAllJobByTitle(String title, Pageable pageable) {
		return jobRepository.findAllJobByTitle(title, pageable);
	}


	@Override
	public int findCountAllJobByUserAvailableByField(Long userid, Long fieldid) {
		return jobRepository.findCountAllJobByUserAvailableByField(userid, fieldid);
	}


	@Override
	public Page<Job> findAllJobByTitleAndStatus(String title, int status, Pageable pageable) {
		return jobRepository.findAllJobByTitleAndStatus(title, status, pageable);
	}


	@Override
	public int findCountAllActiveJob() {
		return jobRepository.findCountAllActiveJob();
	}


	@Override
	public int findCountAllActiveJobByStatus(int status) {
		return jobRepository.findCountAllActiveJobByStatus(status);
	}


	@Override
	public int findCountAllJobByUserAvailable(Long userid) {
		return jobRepository.findCountAllJobByUserAvailable(userid);
	}


	@Override
	public int findCountAllJobsByFreelancerAndStatus(Long userid, int status) {
		return jobRepository.findCountAllJobsByFreelancerAndStatus(userid, status);
	}


	@Override
	public int findCountAllJobByUserStatus(Long userid, int status) {
		return jobRepository.findCountAllJobByUserStatus(userid, status);
	}


	@Override
	public List<Job> findAllJobByUserHiring(Long userid) {
		return jobRepository.findAllJobByUserHiring(userid);
	}


	@Override
	public List<Job> findAllJobsByFreelancerAndTitleAndStatusList(Long userid, String title, int status) {
		return jobRepository.findAllJobsByFreelancerAndTitleAndStatusList(userid, title, status);
	}


	@Override
	public Page<Job> findAllJobsByFreelancerAndTitleAndStatus(Long userid, String title, int status,
			Pageable pageable) {
		return jobRepository.findAllJobsByFreelancerAndTitleAndStatus(userid, title, status, pageable);
	}


	@Override
	public Page<Job> findAllJobByTitleAndStatusUserParam(Long userid, String title, int status, Pageable pageable) {
		return jobRepository.findAllJobByTitleAndStatusUserParam(userid, title, status, pageable);
	}


	@Override
	public Page<Job> findAllJobByTitleAndFieldUserParam(Long userid, String title, Long fieldid, Pageable pageable) {
		return jobRepository.findAllJobByTitleAndFieldUserParam(userid, title, fieldid, pageable);
	}


	@Override
	public Page<Job> findAllJobByTitleAndFieldHiring(String title, Long fieldid, Pageable pageable) {
		return jobRepository.findAllJobByTitleAndFieldHiring(title, fieldid, pageable);
	}


	@Override
	public Page<Job> findAllJobHiring(Pageable pageable) {
		return jobRepository.findAllJobHiring(pageable);
	}

	
	@Override
	public Page<Job> findAllJobByTitleUserParam(Long userid, String title, Pageable pageable) {
		return jobRepository.findAllJobByTitleUserParam(userid, title, pageable);
	}

	@Override
	public Page<Job> findAllJobUserParam(Long userid, Pageable pageable) {
		return jobRepository.findAllJobUserParam(userid, pageable);
	}

	@Override
	public Page<Job> findAllJobByTitleHiring(String title, Pageable pageable) {
		return jobRepository.findAllJobByTitleHiring(title, pageable);
	}

	@Override
	public <S extends Job> S save(S entity, int status) {
		entity.setStatus(status);

		return jobRepository.save(entity);
	}
	
	@Override
	public <S extends Job> Optional<S> findOne(Example<S> example) {
		return jobRepository.findOne(example);
	}
	
	@Override
	public List<Job> findAllByUserId(Long userId){
		return jobRepository.findAllJobByUserParam(userId);
	}

	@Override
	public List<Job> findAll() {
		return jobRepository.findAll();
	}

	@Override
	public Page<Job> findAll(Pageable pageable) {
		return jobRepository.findAll(pageable);
	}
	
	@Override
	public List<Job> findAll(Sort sort) {
		return jobRepository.findAll(sort);
	}

	@Override
	public List<Job> findAllById(Iterable<Long> ids) {
		return jobRepository.findAllById(ids);
	}

	@Override
	public Optional<Job> findById(Long id) {
		return jobRepository.findById(id);
	}

	@Override
	public <S extends Job> List<S> saveAll(Iterable<S> entities) {
		return jobRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		jobRepository.flush();
	}

	@Override
	public boolean existsById(Long id) {
		return jobRepository.existsById(id);
	}

	@Override
	public <S extends Job> S saveAndFlush(S entity) {
		return jobRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Job> List<S> saveAllAndFlush(Iterable<S> entities) {
		return jobRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Job> Page<S> findAll(Example<S> example, Pageable pageable) {
		return jobRepository.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<Job> entities) {
		jobRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Job> long count(Example<S> example) {
		return jobRepository.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Job> entities) {
		jobRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return jobRepository.count();
	}

	@Override
	public <S extends Job> boolean exists(Example<S> example) {
		return jobRepository.exists(example);
	}

	@Override
	public void deleteById(Long id) {
		jobRepository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		jobRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Job entity) {
		jobRepository.delete(entity);
	}

	@Override
	public <S extends Job, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return jobRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		jobRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		jobRepository.deleteAllInBatch();
	}

	@Override
	public Job getOne(Long id) {
		return jobRepository.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Job> entities) {
		jobRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		jobRepository.deleteAll();
	}

	@Override
	public Job getById(Long id) {
		return jobRepository.getById(id);
	}

	@Override
	public Job getReferenceById(Long id) {
		return jobRepository.getReferenceById(id);
	}

	@Override
	public <S extends Job> List<S> findAll(Example<S> example) {
		return jobRepository.findAll(example);
	}

	@Override
	public <S extends Job> List<S> findAll(Example<S> example, Sort sort) {
		return jobRepository.findAll(example, sort);
	}
	
	
}
