package freelancePlatform.controller;

import java.util.UUID;

import javax.servlet.http.HttpSession;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import freelancePlatform.domain.User;
import freelancePlatform.model.UserDto;
import freelancePlatform.service.UserService;

@Controller
public class SignInController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("signin")
	public String login(ModelMap model) {
		model.addAttribute("user", new UserDto());
		return "/site/signin";
	}
	
	@PostMapping("login")
	public ModelAndView login(ModelMap model,
			@Valid @ModelAttribute("user") UserDto dto,
			BindingResult result) {
		if(result.hasErrors()) {
			return new ModelAndView("/site/signin", model);
		}
		

		User user = userService.login(dto.getEmail(), dto.getPassword());

		if(user==null) {
			model.addAttribute("message","Invalid username or password");
			return new ModelAndView("/site/signin", model);
		}
		
		session.setAttribute("userid", user.getUserId());
		session.setAttribute("email", user.getEmail());
		session.setAttribute("role", user.getRole());
		session.setAttribute("avatar", user.getAvatar());
		session.setAttribute("name", user.getName());
		
//		Object ruri = session.getAttribute("redirect-url");
//		
//		if(ruri != null) {
//			session.removeAttribute("redirect-url");
//			return new ModelAndView("forward:/customer/", model);
//		}
		
		return new ModelAndView("forward:/", model);
	}
	
	@GetMapping("logout")
	public String logout(ModelMap model) {
		model.addAttribute("user", new UserDto());
		if(session!=null) {
			session.invalidate();
		}
		return "/site/signin";
	}
}
