package freelancePlatform.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import freelancePlatform.interceptor.AdminAuthenticationInterceptor;
import freelancePlatform.interceptor.CustomerAuthenticationInterceptor;
import freelancePlatform.interceptor.FreelancerAuthenticationInterceptor;

@Configuration
public class AuthenticationInterceptorConfig implements WebMvcConfigurer {

	@Autowired
	private CustomerAuthenticationInterceptor customerAuthenticationInterceptor;
	
	@Autowired
	private FreelancerAuthenticationInterceptor freelancerAuthenticationInterceptor;
	
	@Autowired
	private AdminAuthenticationInterceptor adminAuthenticationInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(customerAuthenticationInterceptor)
		.addPathPatterns("/customer/**");
		
		registry.addInterceptor(freelancerAuthenticationInterceptor)
		.addPathPatterns("/freelancer/**");
		
		registry.addInterceptor(adminAuthenticationInterceptor)
		.addPathPatterns("/admin/**");

	}
	
}
