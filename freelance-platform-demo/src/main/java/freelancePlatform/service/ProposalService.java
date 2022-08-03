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

public interface ProposalService {

	<S extends Proposal> List<S> findAll(Example<S> example, Sort sort);

	<S extends Proposal> List<S> findAll(Example<S> example);

	Proposal getReferenceById(Long id);

	Proposal getById(Long id);

	void deleteAll();

	void deleteAll(Iterable<? extends Proposal> entities);

	Proposal getOne(Long id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Long> ids);

	<S extends Proposal, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(Proposal entity);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteById(Long id);

	<S extends Proposal> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<Proposal> entities);

	<S extends Proposal> long count(Example<S> example);

	void deleteInBatch(Iterable<Proposal> entities);

	<S extends Proposal> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Proposal> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Proposal> S saveAndFlush(S entity);

	boolean existsById(Long id);

	void flush();

	<S extends Proposal> List<S> saveAll(Iterable<S> entities);

	Optional<Proposal> findById(Long id);

	List<Proposal> findAllById(Iterable<Long> ids);

	List<Proposal> findAll(Sort sort);

	Page<Proposal> findAll(Pageable pageable);

	List<Proposal> findAll();

	<S extends Proposal> Optional<S> findOne(Example<S> example);

	<S extends Proposal> S save(S entity);

	List<Proposal> findAllByUserJobStatus1Or2(Long userid, Long jobid);

	Page<Proposal> findAllProposalsByUserAndTitleAndStatus(Long userid, String title, int status, Pageable pageable);

	Page<Proposal> findAllProposalsByUserAndTitle(Long userid, String title, Pageable pageable);

	Page<Proposal> findAllProposalsByUser(Long userid, Pageable pageable);

	List<Proposal> findAllByJobStatus1(Long jobid);

	Page<Proposal> findAllProposalsByJobUser(Long userid, Pageable pageable);

	Page<Proposal> findAllProposalsByJobUserAndTitle(Long userid, String title, Pageable pageable);

	void rejectAllProposalByJobID(Long jobid);

	Proposal findApprovedProposalByJobID(Long jobid);

	int findCountAllProposalsByJobUserStatus(Long userid, int status);

	int findCountAllProposalsByJobUser(Long userid);

	int findCountAllProposalsByFreelancer(Long userid, int status);

	int findCountAllProposalsByFreelancer(Long userid);

}
