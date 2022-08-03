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

import freelancePlatform.domain.Company;
import freelancePlatform.repository.CompanyRepository;
import freelancePlatform.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	CompanyRepository companyRepository;

	@Override
	public Company findByName(String name) {
		return companyRepository.findByName(name);
	}

	@Override
	public Page<Company> findByNameContaining(String name, Pageable pageable) {
		return companyRepository.findByNameContaining(name, pageable);
	}

	@Override
	public <S extends Company> S save(S entity) {
		return companyRepository.save(entity);
	}

	@Override
	public <S extends Company> Optional<S> findOne(Example<S> example) {
		return companyRepository.findOne(example);
	}

	@Override
	public List<Company> findAll() {
		return companyRepository.findAll();
	}

	@Override
	public Page<Company> findAll(Pageable pageable) {
		return companyRepository.findAll(pageable);
	}

	@Override
	public List<Company> findAll(Sort sort) {
		return companyRepository.findAll(sort);
	}

	@Override
	public List<Company> findAllById(Iterable<Long> ids) {
		return companyRepository.findAllById(ids);
	}

	@Override
	public Optional<Company> findById(Long id) {
		return companyRepository.findById(id);
	}

	@Override
	public <S extends Company> List<S> saveAll(Iterable<S> entities) {
		return companyRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		companyRepository.flush();
	}

	@Override
	public boolean existsById(Long id) {
		return companyRepository.existsById(id);
	}

	@Override
	public <S extends Company> S saveAndFlush(S entity) {
		return companyRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Company> List<S> saveAllAndFlush(Iterable<S> entities) {
		return companyRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Company> Page<S> findAll(Example<S> example, Pageable pageable) {
		return companyRepository.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<Company> entities) {
		companyRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Company> long count(Example<S> example) {
		return companyRepository.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Company> entities) {
		companyRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return companyRepository.count();
	}

	@Override
	public <S extends Company> boolean exists(Example<S> example) {
		return companyRepository.exists(example);
	}

	@Override
	public void deleteById(Long id) {
		companyRepository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		companyRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Company entity) {
		companyRepository.delete(entity);
	}

	@Override
	public <S extends Company, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return companyRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		companyRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		companyRepository.deleteAllInBatch();
	}

	@Override
	public Company getOne(Long id) {
		return companyRepository.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Company> entities) {
		companyRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		companyRepository.deleteAll();
	}

	@Override
	public Company getById(Long id) {
		return companyRepository.getById(id);
	}

	@Override
	public Company getReferenceById(Long id) {
		return companyRepository.getReferenceById(id);
	}

	@Override
	public <S extends Company> List<S> findAll(Example<S> example) {
		return companyRepository.findAll(example);
	}

	@Override
	public <S extends Company> List<S> findAll(Example<S> example, Sort sort) {
		return companyRepository.findAll(example, sort);
	}
	
	
}