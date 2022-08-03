package freelancePlatform.controller.freelancer;

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
import freelancePlatform.service.JobService;
import freelancePlatform.service.ProposalService;
import freelancePlatform.service.UserService;

@Controller
@RequestMapping("freelancer/proposals")
public class FreelancerProposalController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	ProposalService proposalService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	JobService jobService;
	
	@GetMapping("add/{jobId}")
	public String addProposal(ModelMap model, @PathVariable("jobId") Long jobId) {
		ProposalDto dto = new ProposalDto();
		Long userId = Long.valueOf(session.getAttribute("userid").toString());
		dto.setJobId(jobId);
		dto.setUserId(userId);
		
		model.addAttribute("proposal", dto);
		return "/freelancer/sendProposal";
	}
	
	@PostMapping("save")
	public ModelAndView saveJob(ModelMap model,
			@Valid @ModelAttribute("proposal") ProposalDto dto,
			BindingResult result) {
		
		Proposal entity = new Proposal();
		BeanUtils.copyProperties(dto, entity);
		
		entity.setStatus(1);
		entity.setUser(userService.findById(dto.getUserId()).get());
		entity.setJob(jobService.findById(dto.getJobId()).get());
		
		proposalService.save(entity);
		
		model.addAttribute("message", "Proposal is sent!");
		
		return new ModelAndView("forward:/jobs/detail/"+dto.getJobId(),model);
	}
	
	@RequestMapping("")
	public String search(ModelMap model,
			@RequestParam(name ="titleSearch", required = false) String titleSearch,
			@RequestParam(name ="statusSearch", required = false) String statusSearch,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		Long userId = Long.valueOf(session.getAttribute("userid").toString());
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);
		
		Pageable pageable = PageRequest.of(currentPage-1, pageSize, Sort.by("proposal_id").descending());
		Page<Proposal> resultPage = null;
		
		if(StringUtils.hasText(titleSearch)) {
			resultPage = proposalService.findAllProposalsByUserAndTitle(userId, titleSearch, pageable);
			model.addAttribute("titleSearch", titleSearch);
		} else {
			resultPage = proposalService.findAllProposalsByUser(userId, pageable);
		}
		
		if(StringUtils.hasText(statusSearch)) {
			int status = Integer.valueOf(statusSearch);
			resultPage = proposalService.findAllProposalsByUserAndTitleAndStatus(userId, titleSearch, status, pageable);
			model.addAttribute("statusSearch", statusSearch);
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
		
		return "freelancer/myProposals";
	}
}
