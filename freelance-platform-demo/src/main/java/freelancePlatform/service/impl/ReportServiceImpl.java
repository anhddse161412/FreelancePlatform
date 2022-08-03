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

import freelancePlatform.domain.Proposal;
import freelancePlatform.domain.Report;
import freelancePlatform.repository.ProposalRepository;
import freelancePlatform.repository.ReportRepository;
import freelancePlatform.service.ProposalService;
import freelancePlatform.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	ReportRepository reportRepository;

	
	@Override
	public Report findByJobId(Long jobId) {
		return reportRepository.findByJobId(jobId);
	}

	@Override
	public <S extends Report> S save(S entity) {
		return reportRepository.save(entity);
	}

	@Override
	public <S extends Report> Optional<S> findOne(Example<S> example) {
		return reportRepository.findOne(example);
	}

	@Override
	public List<Report> findAll() {
		return reportRepository.findAll();
	}

	@Override
	public Page<Report> findAll(Pageable pageable) {
		return reportRepository.findAll(pageable);
	}

	@Override
	public List<Report> findAll(Sort sort) {
		return reportRepository.findAll(sort);
	}

	@Override
	public List<Report> findAllById(Iterable<Long> ids) {
		return reportRepository.findAllById(ids);
	}

	@Override
	public Optional<Report> findById(Long id) {
		return reportRepository.findById(id);
	}

	@Override
	public <S extends Report> List<S> saveAll(Iterable<S> entities) {
		return reportRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		reportRepository.flush();
	}

	@Override
	public boolean existsById(Long id) {
		return reportRepository.existsById(id);
	}

	@Override
	public <S extends Report> S saveAndFlush(S entity) {
		return reportRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Report> List<S> saveAllAndFlush(Iterable<S> entities) {
		return reportRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Report> Page<S> findAll(Example<S> example, Pageable pageable) {
		return reportRepository.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<Report> entities) {
		reportRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Report> long count(Example<S> example) {
		return reportRepository.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Report> entities) {
		reportRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return reportRepository.count();
	}

	@Override
	public <S extends Report> boolean exists(Example<S> example) {
		return reportRepository.exists(example);
	}

	@Override
	public void deleteById(Long id) {
		reportRepository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		reportRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Report entity) {
		reportRepository.delete(entity);
	}

	@Override
	public <S extends Report, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return reportRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		reportRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		reportRepository.deleteAllInBatch();
	}

	@Override
	public Report getOne(Long id) {
		return reportRepository.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Report> entities) {
		reportRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		reportRepository.deleteAll();
	}

	@Override
	public Report getById(Long id) {
		return reportRepository.getById(id);
	}

	@Override
	public Report getReferenceById(Long id) {
		return reportRepository.getReferenceById(id);
	}

	@Override
	public <S extends Report> List<S> findAll(Example<S> example) {
		return reportRepository.findAll(example);
	}

	@Override
	public <S extends Report> List<S> findAll(Example<S> example, Sort sort) {
		return reportRepository.findAll(example, sort);
	}
	
	
}
