package freelancePlatform.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import freelancePlatform.domain.Company;
import freelancePlatform.domain.CustomerProfile;
import freelancePlatform.domain.FreelancerProfile;
import freelancePlatform.domain.Job;
import freelancePlatform.domain.Tech;
import freelancePlatform.domain.Field;
import freelancePlatform.domain.User;
import freelancePlatform.model.JobDto;
import freelancePlatform.model.TechDto;
import freelancePlatform.model.UserDto;
import freelancePlatform.service.CompanyService;
import freelancePlatform.service.CustomerProfileService;
import freelancePlatform.service.FieldService;
import freelancePlatform.service.FreelancerProfileService;
import freelancePlatform.service.IImageService;
import freelancePlatform.service.JobService;
import freelancePlatform.service.TechService;
import freelancePlatform.service.UserService;

@Controller
public class ProfileController {
	@Autowired
	HttpSession session;
	
	@Autowired
	UserService  userService;
	
	@Autowired
	CustomerProfileService customerProfileService;
	
	@Autowired
	FreelancerProfileService freelancerProfileService;
	
	@Autowired
	TechService techService;
	
	@Autowired
    IImageService imageService;
	
	@Autowired
	JobService jobService;
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	FieldService fieldService;
	
	@ModelAttribute("fields")
	public List<Field> getFields(){
		return fieldService.findAll();
	} 
	
	@ModelAttribute("companies")
	public List<String> getCompanys() {
		List<String> companyList = new ArrayList<String>();
		List<Company> companyListData = companyService.findAll();
		for (Company company : companyListData) {
			companyList.add(company.getName());
		}
		return companyList;
	}
	
	@RequestMapping("profile/{id}")
	public ModelAndView detail(ModelMap model, @PathVariable("id") Long userId) {
		Optional<User> opt = userService.findById(userId);
		UserDto dto = new UserDto();
		if(opt.isPresent()) {
			User entity = opt.get();
			
			BeanUtils.copyProperties(entity, dto);
			if(entity.getRole()==2) {
				List<Job> jobs = jobService.findAllJobByUserHiring(userId);
				CustomerProfile profile = customerProfileService.findByUserId(entity.getUserId());
				
				model.addAttribute("jobs", jobs);
				model.addAttribute("user", dto);
				model.addAttribute("profile", profile);
			
				return new ModelAndView("site/profile/customerdetail", model);
			} else {
				List<Job> jobs = jobService.findAllJobsByFreelancerAndTitleAndStatusList(userId, "", 3);
				FreelancerProfile profile  = freelancerProfileService.findByUserId(entity.getUserId());
				model.addAttribute("jobs", jobs);
				model.addAttribute("techList", profile.getTechs().toArray());
				model.addAttribute("user", dto);
				model.addAttribute("profile", profile);
			
				return new ModelAndView("site/profile/freelancerdetail", model);
			}
		}
		
		model.addAttribute("message", "Profile is not exist");
		return new ModelAndView("forward:/jobs", model);
	}
	
	@GetMapping("profile/edit/{id}")
	public ModelAndView edit(ModelMap model, @PathVariable("id") Long userId) {
		Long currentUserId = Long.valueOf(session.getAttribute("userid").toString());
		if(!currentUserId.equals(userId)) {
			model.addAttribute("message", "You can only edit your own profile!");		
			return new ModelAndView("forward:/jobs", model);
		}
		
		Optional<User> opt = userService.findById(userId);
		UserDto dto = new UserDto();
		if(opt.isPresent()) {
			User entity = opt.get();
			
			BeanUtils.copyProperties(entity, dto);
			if(entity.getRole()==2) {
				String company = entity.getCustomerProfile().getCompany();
				model.addAttribute("user", dto);
				model.addAttribute("company", company);
				return new ModelAndView("customer/profile/editProfile", model);
			}
			
			else {
				List<String> techs = new ArrayList<String>();
				Set<Tech> techsSet = entity.getFreelancerProfile().getTechs();
				for (Tech tech : techsSet) {
					techs.add(tech.getName());
				}
				model.addAttribute("techsUser", techs);
				model.addAttribute("user", dto);
				
				return new ModelAndView("freelancer/profile/editProfile", model);
			}
		}
		
		model.addAttribute("message", "User is not exist");
		return new ModelAndView("forward:/jobs", model);
	}
	
	@PostMapping("profile/saveAsCustomer")
	public ModelAndView saveAsCustomer(ModelMap model,
			@Valid @ModelAttribute("user") UserDto dto,
			@RequestParam("company") String company,
			BindingResult result) {
		
		User entity = userService.findById(dto.getUserId()).get();
		String avatar = entity.getAvatar();
		dto.setRole(2);
		dto.setStatus(1);
		BeanUtils.copyProperties(dto, entity);
		
		if(!dto.getAvatarFile().isEmpty()) {
			try {

                String fileName = imageService.save(dto.getAvatarFile());

                String imageUrl = imageService.getImageUrl(fileName);

                entity.setAvatar(imageUrl);

            } catch (Exception e) {
            	System.out.println(e);
            }
		}else {
			entity.setAvatar(avatar);
		}
		
		CustomerProfile profile = customerProfileService.findByUserId(entity.getUserId());
		profile.setCompany(company);
		
		userService.saveEdit(entity);
		customerProfileService.save(profile);
		model.addAttribute("message","Edit account successfully!");
		
		session.setAttribute("avatar", entity.getAvatar());
		session.setAttribute("name", entity.getName());
		
		return new ModelAndView("forward:/profile/"+entity.getUserId(),model);
	}
	
	@PostMapping("profile/saveAsFreelancer")
	public ModelAndView saveAsFreelancer(ModelMap model,
			@Valid @ModelAttribute("user") UserDto dto,
			@RequestParam("techChecked") String techChecked,
			BindingResult result) {
		
		String[] stringArray = techChecked.split(",");
		List<String> techCheckedList = Arrays.asList(stringArray);
		
		User entity = userService.findById(dto.getUserId()).get();
		String avatar = entity.getAvatar();
		dto.setRole(3);
		dto.setStatus(1);
		BeanUtils.copyProperties(dto, entity);
		
		if(!dto.getAvatarFile().isEmpty()) {
			try {

                String fileName = imageService.save(dto.getAvatarFile());

                String imageUrl = imageService.getImageUrl(fileName);

                entity.setAvatar(imageUrl);

            } catch (Exception e) {
            	System.out.println(e);
            }
		} else {
			entity.setAvatar(avatar);
		}
		FreelancerProfile profile = freelancerProfileService.findByUserId(entity.getUserId());
		profile.getTechs().clear();
		
		for (String tech : techCheckedList) {		
			profile.getTechs().add(techService.findByName(tech));
		}
		
		userService.saveEdit(entity);
		freelancerProfileService.save(profile);
		model.addAttribute("message","Edit account successfully!");
		
		session.setAttribute("avatar", entity.getAvatar());
		session.setAttribute("name", entity.getName());
		
		return new ModelAndView("forward:/profile/"+entity.getUserId(),model);
	}
}
