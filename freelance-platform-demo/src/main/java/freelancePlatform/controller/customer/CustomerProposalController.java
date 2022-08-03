package freelancePlatform.controller.customer;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import freelancePlatform.domain.Job;
import freelancePlatform.domain.Proposal;
import freelancePlatform.domain.User;
import freelancePlatform.model.JobDto;
import freelancePlatform.model.ProposalDto;
import freelancePlatform.model.UserDto;
import freelancePlatform.repository.JobRepository;
import freelancePlatform.service.JobService;
import freelancePlatform.service.ProposalService;
import freelancePlatform.service.UserService;

@Controller
@RequestMapping("customer/proposals")
public class CustomerProposalController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	ProposalService proposalService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	JobService jobService;
	
	@RequestMapping("")
	public String search(ModelMap model,
			@RequestParam(name ="titleSearch", required = false) String titleSearch,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		Long userId = Long.valueOf(session.getAttribute("userid").toString());
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);
		
		Pageable pageable = PageRequest.of(currentPage-1, pageSize, Sort.by("proposal_id").descending());
		Page<Proposal> resultPage = null;
		
		if(StringUtils.hasText(titleSearch)) {
			resultPage = proposalService.findAllProposalsByJobUserAndTitle(userId, titleSearch, pageable);
			model.addAttribute("titleSearch", titleSearch);
		} else {
			resultPage = proposalService.findAllProposalsByJobUser(userId, pageable);
		}
		
		
		int totalPages = resultPage.getTotalPages();
		if(totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPages);
			
			if(totalPages > 5) {
				if (end == totalPages) start = end - 5;
				else if (start == 1) end = start + 5;
			}
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end)
					.boxed()
					.collect(Collectors.toList());
			
			model.addAttribute("pageNumbers", pageNumbers);
		}
		
		model.addAttribute("proposalPage", resultPage);
		
		return "customer/myProposals";
	}
	
	@RequestMapping("reject/{id}")
	public ModelAndView reject(ModelMap model, @PathVariable("id") Long proposalId) {
		Optional<Proposal> opt = proposalService.findById(proposalId);

		if(opt.isPresent()) {
			Proposal entity = opt.get();
			
			entity.setStatus(0);
			proposalService.save(entity);
			
			return new ModelAndView("redirect:/jobs/detail/"+entity.getJob().getJobId(), model);
		}
		
		model.addAttribute("message", "This proposal is not exist");
		return new ModelAndView("forward:/jobs", model);
	}
	
	@RequestMapping("approve/{id}")
	public ModelAndView approve(ModelMap model, @PathVariable("id") Long proposalId) {
		Optional<Proposal> opt = proposalService.findById(proposalId);

		if(opt.isPresent()) {
			Proposal proposal = opt.get();
			Job job = proposal.getJob();
			
			proposalService.rejectAllProposalByJobID(job.getJobId());
			proposal.setStatus(2);
			proposalService.save(proposal);
			
			jobService.save(job,2);
			
			return new ModelAndView("redirect:/jobs/detail/"+job.getJobId(), model);
		}
		
		model.addAttribute("message", "This proposal is not exist");
		return new ModelAndView("forward:/jobs", model);
	}
}
