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
import freelancePlatform.repository.ProposalRepository;
import freelancePlatform.service.ProposalService;

@Service
public class ProposalServiceImpl implements ProposalService {
	@Autowired
	ProposalRepository proposalRepository;

	@Override
	public <S extends Proposal> S save(S entity) {
		return proposalRepository.save(entity);
	}

	@Override
	public <S extends Proposal> Optional<S> findOne(Example<S> example) {
		return proposalRepository.findOne(example);
	}

	@Override
	public int findCountAllProposalsByFreelancer(Long userid) {
		return proposalRepository.findCountAllProposalsByFreelancer(userid);
	}

	@Override
	public int findCountAllProposalsByFreelancer(Long userid, int status) {
		return proposalRepository.findCountAllProposalsByFreelancer(userid, status);
	}

	@Override
	public int findCountAllProposalsByJobUser(Long userid) {
		return proposalRepository.findCountAllProposalsByJobUser(userid);
	}

	@Override
	public int findCountAllProposalsByJobUserStatus(Long userid, int status) {
		return proposalRepository.findCountAllProposalsByJobUserStatus(userid, status);
	}

	@Override
	public Proposal findApprovedProposalByJobID(Long jobid) {
		return proposalRepository.findApprovedProposalByJobID(jobid);
	}

	@Override
	public Page<Proposal> findAllProposalsByJobUserAndTitle(Long userid, String title, Pageable pageable) {
		return proposalRepository.findAllProposalsByJobUserAndTitle(userid, title, pageable);
	}

	@Override
	public void rejectAllProposalByJobID(Long jobid) {
		proposalRepository.rejectAllProposalByJobID(jobid);
	}

	@Override
	public Page<Proposal> findAllProposalsByJobUser(Long userid, Pageable pageable) {
		return proposalRepository.findAllProposalsByJobUser(userid, pageable);
	}

	@Override
	public List<Proposal> findAllByJobStatus1(Long jobid) {
		return proposalRepository.findAllByJobStatus1(jobid);
	}

	@Override
	public Page<Proposal> findAllProposalsByUser(Long userid, Pageable pageable) {
		return proposalRepository.findAllProposalsByUser(userid, pageable);
	}

	@Override
	public Page<Proposal> findAllProposalsByUserAndTitle(Long userid, String title, Pageable pageable) {
		return proposalRepository.findAllProposalsByUserAndTitle(userid, title, pageable);
	}

	@Override
	public Page<Proposal> findAllProposalsByUserAndTitleAndStatus(Long userid, String title, int status,
			Pageable pageable) {
		return proposalRepository.findAllProposalsByUserAndTitleAndStatus(userid, title, status, pageable);
	}

	@Override
	public List<Proposal> findAllByUserJobStatus1Or2(Long userid, Long jobid) {
		return proposalRepository.findAllByUserJobStatus1Or2(userid, jobid);
	}

	@Override
	public List<Proposal> findAll() {
		return proposalRepository.findAll();
	}

	@Override
	public Page<Proposal> findAll(Pageable pageable) {
		return proposalRepository.findAll(pageable);
	}

	@Override
	public List<Proposal> findAll(Sort sort) {
		return proposalRepository.findAll(sort);
	}

	@Override
	public List<Proposal> findAllById(Iterable<Long> ids) {
		return proposalRepository.findAllById(ids);
	}

	@Override
	public Optional<Proposal> findById(Long id) {
		return proposalRepository.findById(id);
	}

	@Override
	public <S extends Proposal> List<S> saveAll(Iterable<S> entities) {
		return proposalRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		proposalRepository.flush();
	}

	@Override
	public boolean existsById(Long id) {
		return proposalRepository.existsById(id);
	}

	@Override
	public <S extends Proposal> S saveAndFlush(S entity) {
		return proposalRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Proposal> List<S> saveAllAndFlush(Iterable<S> entities) {
		return proposalRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Proposal> Page<S> findAll(Example<S> example, Pageable pageable) {
		return proposalRepository.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<Proposal> entities) {
		proposalRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Proposal> long count(Example<S> example) {
		return proposalRepository.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Proposal> entities) {
		proposalRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return proposalRepository.count();
	}

	@Override
	public <S extends Proposal> boolean exists(Example<S> example) {
		return proposalRepository.exists(example);
	}

	@Override
	public void deleteById(Long id) {
		proposalRepository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		proposalRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Proposal entity) {
		proposalRepository.delete(entity);
	}

	@Override
	public <S extends Proposal, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return proposalRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		proposalRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		proposalRepository.deleteAllInBatch();
	}

	@Override
	public Proposal getOne(Long id) {
		return proposalRepository.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Proposal> entities) {
		proposalRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		proposalRepository.deleteAll();
	}

	@Override
	public Proposal getById(Long id) {
		return proposalRepository.getById(id);
	}

	@Override
	public Proposal getReferenceById(Long id) {
		return proposalRepository.getReferenceById(id);
	}

	@Override
	public <S extends Proposal> List<S> findAll(Example<S> example) {
		return proposalRepository.findAll(example);
	}

	@Override
	public <S extends Proposal> List<S> findAll(Example<S> example, Sort sort) {
		return proposalRepository.findAll(example, sort);
	}
	
}
