package freelancePlatform.controller.freelancer;

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
import freelancePlatform.domain.User;
import freelancePlatform.model.UserDto;
import freelancePlatform.service.JobService;
import freelancePlatform.service.ProposalService;
import freelancePlatform.service.UserService;

@Controller
public class FreelancerController {
	
	@Autowired
	JobService jobService;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	ProposalService proposalService;
	
	@RequestMapping("freelancer")
	public String login(ModelMap model) {
		model.addAttribute("user", new UserDto());
		return "/freelancer/index";
	}
	
	@RequestMapping("freelancer/jobs/ongoing")
	public String ongoing(ModelMap model,
			@RequestParam(name ="titleSearch", required = false) String titleSearch,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		Long userId = Long.valueOf(session.getAttribute("userid").toString());
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);
		
		Pageable pageable = PageRequest.of(currentPage-1, pageSize, Sort.by("job_id").descending());
		Page<Job> resultPage = null;
		
		if(StringUtils.hasText(titleSearch)) {
			resultPage = jobService.findAllJobsByFreelancerAndTitleAndStatus(userId, titleSearch, 2, pageable);
			model.addAttribute("titleSearch", titleSearch);
		} else {
			resultPage = jobService.findAllJobsByFreelancerAndTitleAndStatus(userId, "", 2, pageable);
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
		
		model.addAttribute("jobPage", resultPage);
		
		return "freelancer/ongoingJob";
	}
	
	@RequestMapping("freelancer/jobs/completed")
	public String completed(ModelMap model,
			@RequestParam(name ="titleSearch", required = false) String titleSearch,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		Long userId = Long.valueOf(session.getAttribute("userid").toString());
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);
		
		Pageable pageable = PageRequest.of(currentPage-1, pageSize, Sort.by("job_id").descending());
		Page<Job> resultPage = null;
		
		if(StringUtils.hasText(titleSearch)) {
			resultPage = jobService.findAllJobsByFreelancerAndTitleAndStatus(userId, titleSearch, 3, pageable);
			model.addAttribute("titleSearch", titleSearch);
		} else {
			resultPage = jobService.findAllJobsByFreelancerAndTitleAndStatus(userId, "", 3, pageable);
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
		
		model.addAttribute("jobPage", resultPage);
		
		return "freelancer/completedJob";
	}
}
