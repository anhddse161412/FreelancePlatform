package freelancePlatform.controller.admin;

import java.io.IOException;
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
import freelancePlatform.model.FieldDto;
import freelancePlatform.model.UserDto;
import freelancePlatform.service.FieldService;
import freelancePlatform.service.JobService;
import freelancePlatform.service.ProposalService;
import freelancePlatform.service.UserService;
import freelancePlatform.domain.Field;

@Controller
public class AdminController {
	
	@Autowired
	JobService jobService;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	ProposalService proposalService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	FieldService fieldService;
	
	@ModelAttribute("fields")
	public List<Field> getFields(){
		return fieldService.findAll();
	}
	
	@RequestMapping("admin")
	public String login(ModelMap model) {
		model.addAttribute("user", new UserDto());
		return "/admin/index";
	}
	
	@RequestMapping("admin/accounts")
	public String searchAcc(ModelMap model,
			@RequestParam(name ="emailSearch", required = false) String emailSearch,
			@RequestParam(name ="roleSearch", required = false) String roleSearch,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		Long userId = Long.valueOf(session.getAttribute("userid").toString());
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);
		
		Pageable pageable = PageRequest.of(currentPage-1, pageSize, Sort.by("user_id").descending());
		Page<User> resultPage = null;
		
		if(StringUtils.hasText(emailSearch)) {
			resultPage = userService.findAllUserByEmail(emailSearch, pageable);
			model.addAttribute("emailSearch", emailSearch);
		} else {
			resultPage = userService.findAllUserByEmail("", pageable);
		}
		
		if(StringUtils.hasText(roleSearch)) {
			int role = Integer.valueOf(roleSearch);
			resultPage = userService.findAllUserByEmailRole(emailSearch, role, pageable);
			model.addAttribute("roleSearch", roleSearch);
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
		
		model.addAttribute("accountPage", resultPage);
		
		return "admin/accounts";
	}
	
	@RequestMapping("admin/deactivate/{id}")
	public ModelAndView deactivate(ModelMap model, @PathVariable("id") Long userId) {
		Optional<User> opt = userService.findById(userId);

		if(opt.isPresent()) {
			User entity = opt.get();
			
			entity.setStatus(0);
			userService.update(entity);
			
			return new ModelAndView("redirect:/admin/accounts", model);
		}
		
		model.addAttribute("message", "This account is not exist");
		return new ModelAndView("forward:/admin/accounts", model);
	}
	
	@RequestMapping("admin/activate/{id}")
	public ModelAndView activate(ModelMap model, @PathVariable("id") Long userId) {
		Optional<User> opt = userService.findById(userId);

		if(opt.isPresent()) {
			User entity = opt.get();
			
			entity.setStatus(1);
			userService.update(entity);
			
			return new ModelAndView("redirect:/admin/accounts", model);
		}
		
		model.addAttribute("message", "This account is not exist");
		return new ModelAndView("forward:/admin/accounts", model);
	}
	
	@GetMapping("admin/delete/job/{jobId}")
	public ModelAndView delete(ModelMap model,@PathVariable("jobId") Long jobId) throws IOException {
		
		Optional<Job> opt = jobService.findById(jobId);
		
		if(opt.isPresent()) {
			proposalService.rejectAllProposalByJobID(jobId);
			jobService.save(opt.get(),0);
			
			model.addAttribute("message", "Job is deleted!");
		} else {
			model.addAttribute("message", "Job is not found!");
		}
		
		return new ModelAndView("forward:/jobs",model);
	}
	
	@RequestMapping("admin/allJob")
	public String searchJob(ModelMap model,
			@RequestParam(name ="titleSearch", required = false) String titleSearch,
			@RequestParam(name ="statusSearch", required = false) String statusSearch,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);
		
		Pageable pageable = PageRequest.of(currentPage-1, pageSize, Sort.by("job_id").descending());
		Page<Job> resultPage = null;
		
		if(StringUtils.hasText(titleSearch)) {
			resultPage = jobService.findAllJobByTitle(titleSearch, pageable);
			model.addAttribute("titleSearch", titleSearch);
		} else {
			resultPage = jobService.findAllJobByTitle("",pageable);
		}
		
		if(StringUtils.hasText(statusSearch)) {
			int status = Integer.parseInt(statusSearch);
			resultPage = jobService.findAllJobByTitleAndStatus(titleSearch, status, pageable);
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
		
		model.addAttribute("jobPage", resultPage);
		
		return "admin/allJobs";
	}
	
}
