package freelancePlatform.controller;

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

import freelancePlatform.domain.CustomerProfile;
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
@RequestMapping("proposals")
public class ProposalController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	ProposalService proposalService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	JobService jobService;
	
	@RequestMapping("detail/{id}")
	public ModelAndView detail(ModelMap model, @PathVariable("id") Long proposalId) {
		Optional<Proposal> opt = proposalService.findById(proposalId);
		ProposalDto dto = new ProposalDto();

		
		if(opt.isPresent()) {
			Proposal entity = opt.get();
			
			BeanUtils.copyProperties(entity, dto);
			dto.setUserId(entity.getUser().getUserId());
			
			Job job = entity.getJob();
			User freelancer = entity.getUser();
			
			model.addAttribute("proposal", dto);
			model.addAttribute("job", job);
			model.addAttribute("freelancer", freelancer);
			
			return new ModelAndView("site/proposals/detail", model);
		}
		
		model.addAttribute("message", "This proposal is not exist");
		return new ModelAndView("forward:/jobs", model);
	}
}
