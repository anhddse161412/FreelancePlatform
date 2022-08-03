package freelancePlatform.controller.customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
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
import org.springframework.ui.Model;
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

import freelancePlatform.domain.Field;
import freelancePlatform.domain.Job;
import freelancePlatform.domain.Tech;
import freelancePlatform.domain.User;
import freelancePlatform.model.FieldDto;
import freelancePlatform.model.JobDto;
import freelancePlatform.model.TechDto;
import freelancePlatform.model.UserDto;
import freelancePlatform.repository.FieldRepository;
import freelancePlatform.repository.JobRepository;
import freelancePlatform.repository.TechRepository;
import freelancePlatform.service.FieldService;
import freelancePlatform.service.JobService;
import freelancePlatform.service.ProposalService;
import freelancePlatform.service.TechService;
import freelancePlatform.service.UserService;

@Controller
@RequestMapping("customer/jobs")
public class CustomerJobController {
	@Autowired
	JobService jobService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	FieldService fieldService;
	
	@Autowired
	TechService techService;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	ProposalService proposalService;
	
	@ModelAttribute("fields")
	public List<Field> getFields(){
		return fieldService.findAll();
	}
	
	@ModelAttribute("techs")
	public List<Tech> getTechs() {
		return techService.findAll();
	}
	
	@ModelAttribute("scopes")
	public List<String> getScopes(){
		return Arrays.asList("Large","Medium","Small");
	}
	
	@ModelAttribute("durations")
	public List<String> getDurations(){
		return Arrays.asList("Less than 1 month","1 to 3 months","3 to 6 months","More than 6 months");
	}
	
//	@RequestMapping("")
//	public String list(ModelMap model) {
//		Long userId = Long.valueOf(session.getAttribute("userid").toString());
//		List<Job> list = jobService.findAllByUserId(userId);
//		
//		model.addAttribute("myjobs", list);
//		
//		return "customer/jobs/mylist";
//	}
	
	@RequestMapping("")
	public String search(ModelMap model,
			@RequestParam(name ="titleSearch", required = false) String titleSearch,
			@RequestParam(name ="statusSearch", required = false) String statusSearch,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		Long userId = Long.valueOf(session.getAttribute("userid").toString());
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);
		
		Pageable pageable = PageRequest.of(currentPage-1, pageSize, Sort.by("job_id").descending());
		Page<Job> resultPage = null;
		
		if(StringUtils.hasText(titleSearch)) {
			resultPage = jobService.findAllJobByTitleUserParam(userId, titleSearch, pageable);
			model.addAttribute("titleSearch", titleSearch);
		} else {
			resultPage = jobService.findAllJobUserParam(userId, pageable);
		}
		
		if(StringUtils.hasText(statusSearch)) {
			int status = Integer.parseInt(statusSearch);
			resultPage = jobService.findAllJobByTitleAndStatusUserParam(userId, titleSearch, status, pageable);
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
		
		return "customer/jobs/mylist";
	}
	
	@GetMapping("add")
	public String add(Model model) {
		JobDto dto = new JobDto();
		model.addAttribute("job", dto);
		
		return "customer/jobs/addNew";
	}
	
	@PostMapping("save")
	public ModelAndView saveJob(ModelMap model,
			@Valid @ModelAttribute("job") JobDto dto,
			@RequestParam("techChecked") String techChecked,
			BindingResult result) {
		
		String[] stringArray = techChecked.split(",");
		List<String> techCheckedList = Arrays.asList(stringArray);
		
		Job entity = new Job();
		BeanUtils.copyProperties(dto, entity);
		
		for (String tech : techCheckedList) {		
			entity.getTechs().add(techService.findByName(tech));
		}
		
		entity.setField(fieldService.findById(dto.getFieldId()).get());
		
		Long userId = Long.valueOf(session.getAttribute("userid").toString());
		User currentUser = userService.getById(userId);
		entity.setUser(currentUser);
		if(entity.getJobId() != null) {
			entity.setCreatedAt(jobService.getById(entity.getJobId()).getCreatedAt());
		}
		jobService.save(entity, 1);
		
		model.addAttribute("message", "Job is saved!");
		if(entity.getJobId() == null) {
			return new ModelAndView("forward:/customer/jobs",model);
		}
		return new ModelAndView("forward:/jobs/detail/"+entity.getJobId(),model);
	}
	
	@GetMapping("detail/{id}")
	public ModelAndView detail(ModelMap model, @PathVariable("id") Long jobId) {
		Long userId = Long.valueOf(session.getAttribute("userid").toString());
		Optional<Job> opt = jobService.findById(jobId);
		JobDto dto = new JobDto();
		if(opt.isPresent()) {
			Job entity = opt.get();
			
			if(entity.getUser().getUserId()!=userId) {
				model.addAttribute("message", "You can only edit your own job!");
				return new ModelAndView("forward:/customer/jobs", model);
			}
			
			BeanUtils.copyProperties(entity, dto);
			
			model.addAttribute("techList", entity.getTechs().toArray());
			model.addAttribute("jobField", entity.getField().getName());
			model.addAttribute("job", dto);
			
			return new ModelAndView("customer/jobs/detail", model);
		}
		
		model.addAttribute("message", "Job is not exist");
		return new ModelAndView("forward:/customer/jobs", model);
	}
	
	@GetMapping("edit/{jobId}")
	public ModelAndView edit(ModelMap model, @PathVariable("jobId") Long jobId) {
		Long userId = Long.valueOf(session.getAttribute("userid").toString());
		Optional<Job> opt = jobService.findById(jobId);
		JobDto dto = new JobDto();
		if(opt.isPresent()) {
			Job entity = opt.get();
			
			if(entity.getUser().getUserId()!=userId) {
				model.addAttribute("message", "You can only edit your own job!");
				return new ModelAndView("forward:/customer/jobs", model);
			}
			
			BeanUtils.copyProperties(entity, dto);
			dto.setFieldId(entity.getField().getFieldId());
			
			List<String> techs = new ArrayList<String>();
			Set<Tech> techsSet = entity.getTechs();
			for (Tech tech : techsSet) {
				techs.add(tech.getName());
			}
			dto.setTechs(techs);
			model.addAttribute("job", dto);
			
			return new ModelAndView("customer/jobs/editJob", model);
		}
		
		model.addAttribute("message", "Job is not exist");
		return new ModelAndView("forward:/customer/jobs", model);
	}
	
	@GetMapping("delete/{jobId}")
	public ModelAndView delete(ModelMap model,@PathVariable("jobId") Long jobId) throws IOException {
		
		Optional<Job> opt = jobService.findById(jobId);
		
		if(opt.isPresent()) {
			proposalService.rejectAllProposalByJobID(jobId);
			jobService.save(opt.get(),0);
			
			model.addAttribute("message", "Job is deleted!");
		} else {
			model.addAttribute("message", "Job is not found!");
		}
		
		return new ModelAndView("forward:/customer/jobs",model);
	}
	
	@RequestMapping("ongoing")
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
			resultPage = jobService.findAllJobByTitleAndStatusUserParam(userId, titleSearch, 2, pageable);
			model.addAttribute("titleSearch", titleSearch);
		} else {
			resultPage = jobService.findAllJobByTitleAndStatusUserParam(userId, "", 2, pageable);
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
		
		return "customer/jobs/ongoingJob";
	}
}
