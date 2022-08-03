package freelancePlatform.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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

import freelancePlatform.domain.CustomerProfile;
import freelancePlatform.domain.Field;
import freelancePlatform.domain.Job;
import freelancePlatform.domain.Proposal;
import freelancePlatform.domain.Report;
import freelancePlatform.domain.Tech;
import freelancePlatform.domain.User;
import freelancePlatform.model.FieldDto;
import freelancePlatform.model.JobDto;
import freelancePlatform.model.ReportDto;
import freelancePlatform.model.TechDto;
import freelancePlatform.model.UserDto;
import freelancePlatform.repository.FieldRepository;
import freelancePlatform.repository.TechRepository;
import freelancePlatform.service.CustomerProfileService;
import freelancePlatform.service.FieldService;
import freelancePlatform.service.JobService;
import freelancePlatform.service.ProposalService;
import freelancePlatform.service.ReportService;
import freelancePlatform.service.TechService;
import freelancePlatform.service.UserService;

@Controller
@RequestMapping("jobs")
public class JobController {
	@Autowired
	JobService jobService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CustomerProfileService customerProfileService;
	
	@Autowired
	FieldService fieldService;
	
	@Autowired
	TechService techService;
	
	@Autowired
	ReportService reportService;
	
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
//		List<Job> list = jobService.findAllByStatus(1);
//		
//		model.addAttribute("alljobs", list);
//		
//		return "site/jobs/allJobs";
//	}
	
	@RequestMapping("")
	public String search(ModelMap model,
			@RequestParam(name ="titleSearch", required = false) String titleSearch,
			@RequestParam(name ="fieldSearch", required = false) Long fieldSearch,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);
		
		Pageable pageable = PageRequest.of(currentPage-1, pageSize, Sort.by("job_id").descending());
		Page<Job> resultPage = null;
		
		if(StringUtils.hasText(titleSearch)) {
			resultPage = jobService.findAllJobByTitleHiring(titleSearch, pageable);
			model.addAttribute("titleSearch", titleSearch);
		} else {
			resultPage = jobService.findAllJobHiring(pageable);
		}
		
		if(fieldSearch!=null) {
			resultPage = jobService.findAllJobByTitleAndFieldHiring(titleSearch, fieldSearch, pageable);
			model.addAttribute("fieldSearch", fieldSearch);
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
		
		return "site/jobs/allJobs";
	}
	
	@RequestMapping("detail/{id}")
	public ModelAndView detail(ModelMap model, @PathVariable("id") Long jobId) {
		Optional<Job> opt = jobService.findById(jobId);
		JobDto dto = new JobDto();
		boolean isApplied = false;
		
		if(session.getAttribute("userid")!=null) {
			Long currentUserId = Long.valueOf(session.getAttribute("userid").toString());
			List<Proposal> currentProposal = proposalService.findAllByUserJobStatus1Or2(currentUserId, jobId);
			if(!currentProposal.isEmpty()) {
				model.addAttribute("proposalId", currentProposal.get(0).getProposalId());
				isApplied = true;
			};			
			model.addAttribute("isApplied", isApplied);
		}
		
		if(opt.isPresent()) {
			Job entity = opt.get();
			LocalDateTime createDate = entity.getCreatedAt();
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
	        String formattedDateTime = createDate.format(dateTimeFormatter);
			BeanUtils.copyProperties(entity, dto);
			dto.setUserId(entity.getUser().getUserId());
			
			User jobOwner = entity.getUser();
			CustomerProfile ownerProfile = customerProfileService.findByUserId(jobOwner.getUserId());
			
			List<Proposal> proposalList = proposalService.findAllByJobStatus1(jobId);
			Report report = reportService.findByJobId(jobId);
			ReportDto reportDto = new ReportDto();
			if(report!=null) {
				BeanUtils.copyProperties(report, reportDto);
				model.addAttribute("fileName", report.getFileName());
				model.addAttribute("fileUrl", report.getFileUrl());
			}
			if(entity.getStatus()==2 || entity.getStatus()==3) {
				Proposal approvedProposal = proposalService.findApprovedProposalByJobID(jobId);
				User freelancer = approvedProposal.getUser();
				model.addAttribute("freelancer", freelancer);
			}
			
			model.addAttribute("report", reportDto);
			model.addAttribute("createDate", formattedDateTime);			
			model.addAttribute("techList", entity.getTechs().toArray());
			model.addAttribute("jobField", entity.getField().getName());
			model.addAttribute("job", dto);
			model.addAttribute("jobOwner", jobOwner);
			model.addAttribute("ownerProfile", ownerProfile);
			model.addAttribute("proposalList", proposalList);
			return new ModelAndView("site/jobs/detail", model);
		}
		
		model.addAttribute("message", "Job is not exist");
		return new ModelAndView("forward:/jobs", model);
	}
	
	@RequestMapping("pay/{id}")
	public ModelAndView completeJob(ModelMap model, @PathVariable("id") Long jobId) {
		Optional<Job> opt = jobService.findById(jobId);

		if(opt.isPresent()) {
			Job entity = opt.get();

			jobService.save(entity,3);
			
			return new ModelAndView("forward:/jobs/detail/" + jobId,model);
		}
		
		model.addAttribute("message", "This job is not exist");
		return new ModelAndView("forward:/jobs", model);
	}
}
