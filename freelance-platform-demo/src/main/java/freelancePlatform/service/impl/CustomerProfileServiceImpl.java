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

import freelancePlatform.domain.CustomerProfile;
import freelancePlatform.repository.CustomerProfileRepository;
import freelancePlatform.service.CustomerProfileService;

@Service
public class CustomerProfileServiceImpl implements CustomerProfileService {
	@Autowired
	CustomerProfileRepository customerProfileRepository;

	@Override
	public CustomerProfile findByUserId(Long userId) {
		return customerProfileRepository.findByUserId(userId);
	}

	@Override
	public <S extends CustomerProfile> S save(S entity) {
		return customerProfileRepository.save(entity);
	}

	@Override
	public <S extends CustomerProfile> Optional<S> findOne(Example<S> example) {
		return customerProfileRepository.findOne(example);
	}

	@Override
	public List<CustomerProfile> findAll() {
		return customerProfileRepository.findAll();
	}

	@Override
	public Page<CustomerProfile> findAll(Pageable pageable) {
		return customerProfileRepository.findAll(pageable);
	}

	@Override
	public List<CustomerProfile> findAll(Sort sort) {
		return customerProfileRepository.findAll(sort);
	}

	@Override
	public List<CustomerProfile> findAllById(Iterable<Long> ids) {
		return customerProfileRepository.findAllById(ids);
	}

	@Override
	public Optional<CustomerProfile> findById(Long id) {
		return customerProfileRepository.findById(id);
	}

	@Override
	public <S extends CustomerProfile> List<S> saveAll(Iterable<S> entities) {
		return customerProfileRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		customerProfileRepository.flush();
	}

	@Override
	public boolean existsById(Long id) {
		return customerProfileRepository.existsById(id);
	}

	@Override
	public <S extends CustomerProfile> S saveAndFlush(S entity) {
		return customerProfileRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends CustomerProfile> List<S> saveAllAndFlush(Iterable<S> entities) {
		return customerProfileRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends CustomerProfile> Page<S> findAll(Example<S> example, Pageable pageable) {
		return customerProfileRepository.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<CustomerProfile> entities) {
		customerProfileRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends CustomerProfile> long count(Example<S> example) {
		return customerProfileRepository.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<CustomerProfile> entities) {
		customerProfileRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return customerProfileRepository.count();
	}

	@Override
	public <S extends CustomerProfile> boolean exists(Example<S> example) {
		return customerProfileRepository.exists(example);
	}

	@Override
	public void deleteById(Long id) {
		customerProfileRepository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		customerProfileRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(CustomerProfile entity) {
		customerProfileRepository.delete(entity);
	}

	@Override
	public <S extends CustomerProfile, R> R findBy(Example<S> example,
			Function<FetchableFluentQuery<S>, R> queryFunction) {
		return customerProfileRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		customerProfileRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		customerProfileRepository.deleteAllInBatch();
	}

	@Override
	public CustomerProfile getOne(Long id) {
		return customerProfileRepository.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends CustomerProfile> entities) {
		customerProfileRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		customerProfileRepository.deleteAll();
	}

	@Override
	public CustomerProfile getById(Long id) {
		return customerProfileRepository.getById(id);
	}

	@Override
	public CustomerProfile getReferenceById(Long id) {
		return customerProfileRepository.getReferenceById(id);
	}

	@Override
	public <S extends CustomerProfile> List<S> findAll(Example<S> example) {
		return customerProfileRepository.findAll(example);
	}

	@Override
	public <S extends CustomerProfile> List<S> findAll(Example<S> example, Sort sort) {
		return customerProfileRepository.findAll(example, sort);
	}
	
	
}
