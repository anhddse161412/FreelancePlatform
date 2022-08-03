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

import freelancePlatform.domain.Field;
import freelancePlatform.repository.FieldRepository;
import freelancePlatform.service.FieldService;

@Service
public class FieldServiceImpl implements FieldService {
	@Autowired
	FieldRepository fieldRepository;

	@Override
	public Field findByName(String name) {
		return fieldRepository.findByName(name);
	}

	@Override
	public Page<Field> findByNameContaining(String name, Pageable pageable) {
		return fieldRepository.findByNameContaining(name, pageable);
	}

	@Override
	public <S extends Field> S save(S entity) {
		return fieldRepository.save(entity);
	}

	@Override
	public <S extends Field> Optional<S> findOne(Example<S> example) {
		return fieldRepository.findOne(example);
	}

	@Override
	public List<Field> findAll() {
		return fieldRepository.findAll();
	}

	@Override
	public Page<Field> findAll(Pageable pageable) {
		return fieldRepository.findAll(pageable);
	}

	@Override
	public List<Field> findAll(Sort sort) {
		return fieldRepository.findAll(sort);
	}

	@Override
	public List<Field> findAllById(Iterable<Long> ids) {
		return fieldRepository.findAllById(ids);
	}

	@Override
	public Optional<Field> findById(Long id) {
		return fieldRepository.findById(id);
	}

	@Override
	public <S extends Field> List<S> saveAll(Iterable<S> entities) {
		return fieldRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		fieldRepository.flush();
	}

	@Override
	public boolean existsById(Long id) {
		return fieldRepository.existsById(id);
	}

	@Override
	public <S extends Field> S saveAndFlush(S entity) {
		return fieldRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Field> List<S> saveAllAndFlush(Iterable<S> entities) {
		return fieldRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Field> Page<S> findAll(Example<S> example, Pageable pageable) {
		return fieldRepository.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<Field> entities) {
		fieldRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Field> long count(Example<S> example) {
		return fieldRepository.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Field> entities) {
		fieldRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return fieldRepository.count();
	}

	@Override
	public <S extends Field> boolean exists(Example<S> example) {
		return fieldRepository.exists(example);
	}

	@Override
	public void deleteById(Long id) {
		fieldRepository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		fieldRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Field entity) {
		fieldRepository.delete(entity);
	}

	@Override
	public <S extends Field, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return fieldRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		fieldRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		fieldRepository.deleteAllInBatch();
	}

	@Override
	public Field getOne(Long id) {
		return fieldRepository.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Field> entities) {
		fieldRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		fieldRepository.deleteAll();
	}

	@Override
	public Field getById(Long id) {
		return fieldRepository.getById(id);
	}

	@Override
	public Field getReferenceById(Long id) {
		return fieldRepository.getReferenceById(id);
	}

	@Override
	public <S extends Field> List<S> findAll(Example<S> example) {
		return fieldRepository.findAll(example);
	}

	@Override
	public <S extends Field> List<S> findAll(Example<S> example, Sort sort) {
		return fieldRepository.findAll(example, sort);
	}
	
	
}
