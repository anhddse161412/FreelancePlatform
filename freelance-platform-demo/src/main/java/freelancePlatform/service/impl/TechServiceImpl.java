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

import freelancePlatform.domain.Tech;
import freelancePlatform.repository.TechRepository;
import freelancePlatform.service.TechService;

@Service
public class TechServiceImpl implements TechService {
	@Autowired
	TechRepository repository;

	@Override
	public Tech findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public List<Tech> findByNameContaining(String name) {
		return repository.findByNameContaining(name);
	}

	@Override
	public <S extends Tech> S save(S entity) {
		return repository.save(entity);
	}

	@Override
	public <S extends Tech> Optional<S> findOne(Example<S> example) {
		return repository.findOne(example);
	}

	@Override
	public List<Tech> findAll() {
		return repository.findAll();
	}

	@Override
	public Page<Tech> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public List<Tech> findAll(Sort sort) {
		return repository.findAll(sort);
	}

	@Override
	public List<Tech> findAllById(Iterable<Long> ids) {
		return repository.findAllById(ids);
	}

	@Override
	public Optional<Tech> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public <S extends Tech> List<S> saveAll(Iterable<S> entities) {
		return repository.saveAll(entities);
	}

	@Override
	public void flush() {
		repository.flush();
	}

	@Override
	public boolean existsById(Long id) {
		return repository.existsById(id);
	}

	@Override
	public <S extends Tech> S saveAndFlush(S entity) {
		return repository.saveAndFlush(entity);
	}

	@Override
	public <S extends Tech> List<S> saveAllAndFlush(Iterable<S> entities) {
		return repository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Tech> Page<S> findAll(Example<S> example, Pageable pageable) {
		return repository.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<Tech> entities) {
		repository.deleteInBatch(entities);
	}

	@Override
	public <S extends Tech> long count(Example<S> example) {
		return repository.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Tech> entities) {
		repository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return repository.count();
	}

	@Override
	public <S extends Tech> boolean exists(Example<S> example) {
		return repository.exists(example);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		repository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Tech entity) {
		repository.delete(entity);
	}

	@Override
	public <S extends Tech, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return repository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		repository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		repository.deleteAllInBatch();
	}

	@Override
	public Tech getOne(Long id) {
		return repository.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Tech> entities) {
		repository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		repository.deleteAll();
	}

	@Override
	public Tech getById(Long id) {
		return repository.getById(id);
	}

	@Override
	public Tech getReferenceById(Long id) {
		return repository.getReferenceById(id);
	}

	@Override
	public <S extends Tech> List<S> findAll(Example<S> example) {
		return repository.findAll(example);
	}

	@Override
	public <S extends Tech> List<S> findAll(Example<S> example, Sort sort) {
		return repository.findAll(example, sort);
	}
	
	
}
