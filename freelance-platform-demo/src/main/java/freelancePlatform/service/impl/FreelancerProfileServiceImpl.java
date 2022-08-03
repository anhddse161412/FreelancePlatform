package freelancePlatform.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import freelancePlatform.domain.FreelancerProfile;
import freelancePlatform.repository.FreelancerProfileRepository;
import freelancePlatform.service.FreelancerProfileService;

@Service
public class FreelancerProfileServiceImpl implements FreelancerProfileService {
	@Autowired
	FreelancerProfileRepository freelancerProfileRepository;

	@Override
	public FreelancerProfile findByUserId(Long userid) {
		return freelancerProfileRepository.findByUserId(userid);
	}

	@Override
	public <S extends FreelancerProfile> S save(S entity) {
		return freelancerProfileRepository.save(entity);
	}

	@Override
	public <S extends FreelancerProfile> Optional<S> findOne(Example<S> example) {
		return freelancerProfileRepository.findOne(example);
	}

	@Override
	public List<FreelancerProfile> findAll() {
		return freelancerProfileRepository.findAll();
	}

	@Override
	public Page<FreelancerProfile> findAll(Pageable pageable) {
		return freelancerProfileRepository.findAll(pageable);
	}

	@Override
	public List<FreelancerProfile> findAll(Sort sort) {
		return freelancerProfileRepository.findAll(sort);
	}

	@Override
	public List<FreelancerProfile> findAllById(Iterable<Long> ids) {
		return freelancerProfileRepository.findAllById(ids);
	}

	@Override
	public Optional<FreelancerProfile> findById(Long id) {
		return freelancerProfileRepository.findById(id);
	}

	@Override
	public <S extends FreelancerProfile> List<S> saveAll(Iterable<S> entities) {
		return freelancerProfileRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		freelancerProfileRepository.flush();
	}

	@Override
	public boolean existsById(Long id) {
		return freelancerProfileRepository.existsById(id);
	}

	@Override
	public <S extends FreelancerProfile> S saveAndFlush(S entity) {
		return freelancerProfileRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends FreelancerProfile> List<S> saveAllAndFlush(Iterable<S> entities) {
		return freelancerProfileRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends FreelancerProfile> Page<S> findAll(Example<S> example, Pageable pageable) {
		return freelancerProfileRepository.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<FreelancerProfile> entities) {
		freelancerProfileRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends FreelancerProfile> long count(Example<S> example) {
		return freelancerProfileRepository.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<FreelancerProfile> entities) {
		freelancerProfileRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return freelancerProfileRepository.count();
	}

	@Override
	public <S extends FreelancerProfile> boolean exists(Example<S> example) {
		return freelancerProfileRepository.exists(example);
	}

	@Override
	public void deleteById(Long id) {
		freelancerProfileRepository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		freelancerProfileRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(FreelancerProfile entity) {
		freelancerProfileRepository.delete(entity);
	}

	@Override
	public <S extends FreelancerProfile, R> R findBy(Example<S> example,
			Function<FetchableFluentQuery<S>, R> queryFunction) {
		return freelancerProfileRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		freelancerProfileRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		freelancerProfileRepository.deleteAllInBatch();
	}

	@Override
	public FreelancerProfile getOne(Long id) {
		return freelancerProfileRepository.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends FreelancerProfile> entities) {
		freelancerProfileRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		freelancerProfileRepository.deleteAll();
	}

	@Override
	public FreelancerProfile getById(Long id) {
		return freelancerProfileRepository.getById(id);
	}

	@Override
	public FreelancerProfile getReferenceById(Long id) {
		return freelancerProfileRepository.getReferenceById(id);
	}

	@Override
	public <S extends FreelancerProfile> List<S> findAll(Example<S> example) {
		return freelancerProfileRepository.findAll(example);
	}

	@Override
	public <S extends FreelancerProfile> List<S> findAll(Example<S> example, Sort sort) {
		return freelancerProfileRepository.findAll(example, sort);
	}
	
	
}
