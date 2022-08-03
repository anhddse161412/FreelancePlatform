package freelancePlatform.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import freelancePlatform.domain.Company;
import freelancePlatform.domain.CustomerProfile;
import freelancePlatform.domain.FreelancerProfile;
import freelancePlatform.domain.Tech;
import freelancePlatform.domain.User;
import freelancePlatform.model.TechDto;
import freelancePlatform.model.UserDto;
import freelancePlatform.service.CompanyService;
import freelancePlatform.service.CustomerProfileService;
import freelancePlatform.service.FreelancerProfileService;
import freelancePlatform.service.IImageService;
import freelancePlatform.service.TechService;
import freelancePlatform.service.UserService;

@Controller
public class SignUpController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	CustomerProfileService customerProfileService;
	
	@Autowired
	FreelancerProfileService freelancerProfileService;
	
	@Autowired
	TechService techService;
	
	@Autowired
    IImageService imageService;
	
	@Autowired
	CompanyService companyService;
	
	@ModelAttribute("techs")
	public List<Tech> getTechs() {
		return techService.findAll();
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
	
	@RequestMapping("signup")
	public String signup(ModelMap model) {
		return "/site/signup";
	}
	
	@RequestMapping("signup/customer")
	public String signupAsCustomer(ModelMap model) {
		model.addAttribute("user", new UserDto());
		return "/site/signupAsCustomer";
	}
	
	@RequestMapping("signup/freelancer")
	public String signupAsFreelancer(ModelMap model) {
		model.addAttribute("user", new UserDto());
		return "/site/signupAsFreelancer";
	}
	
	@PostMapping("signup/saveAsCustomer")
	public ModelAndView saveAsCustomer(ModelMap model,
			@Valid @ModelAttribute("user") UserDto dto,
			@RequestParam("company") String company,
			BindingResult result) {
		
		if(userService.existsByEmail(dto.getEmail())) {
			model.addAttribute("messageErr","This email has been used!");
			
			return new ModelAndView("forward:/signup/customer",model);
		}
		
		if(result.hasErrors()) {
			return new ModelAndView("site/signupAsCustomer");
		}
		
		dto.setRole(2);
		dto.setStatus(1);
		User entity = new User();
		BeanUtils.copyProperties(dto, entity);

		if(!dto.getAvatarFile().isEmpty()) {
			try {

                String fileName = imageService.save(dto.getAvatarFile());

                String imageUrl = imageService.getImageUrl(fileName);

                entity.setAvatar(imageUrl);

            } catch (Exception e) {
            	System.out.println(e);
            }
		}
		
		CustomerProfile profile = new CustomerProfile();
		profile.setUser(entity);
		profile.setCompany(company);
		
		userService.save(entity);
		customerProfileService.save(profile);
		model.addAttribute("message","Registered an account successfully!");
		
		return new ModelAndView("forward:/signup/customer",model);
	}
	
	@PostMapping("signup/saveAsFreelancer")
	public ModelAndView saveAsFreelancer(ModelMap model,
			@Valid @ModelAttribute("user") UserDto dto,
			@RequestParam("techChecked") String techChecked,
			BindingResult result) {
		
		if(userService.existsByEmail(dto.getEmail())) {
			model.addAttribute("messageErr","This email has been used!");
			
			return new ModelAndView("forward:/signup/freelancer",model);
		}
		
		if(result.hasErrors()) {
			return new ModelAndView("site/signupAsFreelancer");
		}
		
		dto.setRole(3);
		dto.setStatus(1);
		User entity = new User();
		BeanUtils.copyProperties(dto, entity);

		if(!dto.getAvatarFile().isEmpty()) {
			try {

                String fileName = imageService.save(dto.getAvatarFile());

                String imageUrl = imageService.getImageUrl(fileName);

                entity.setAvatar(imageUrl);

            } catch (Exception e) {
            	System.out.println(e);
            }
		}
		
		String[] stringArray = techChecked.split(",");
		List<String> techCheckedList = Arrays.asList(stringArray);

		FreelancerProfile profile = new FreelancerProfile();
		profile.setUser(entity);
		
		for (String tech : techCheckedList) {		
			profile.getTechs().add(techService.findByName(tech));
		}
		
		userService.save(entity);
		freelancerProfileService.save(profile);
		model.addAttribute("message","Registered an account successfully!");
		
		return new ModelAndView("forward:/signup/freelancer",model);
	}
}
