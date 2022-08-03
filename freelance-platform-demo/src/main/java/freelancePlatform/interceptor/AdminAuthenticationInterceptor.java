package freelancePlatform.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AdminAuthenticationInterceptor implements HandlerInterceptor {

	@Autowired
	private HttpSession session;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("Pre handle of request" + request.getRequestURI());
		if(session.getAttribute("email") != null && session.getAttribute("role").equals(1)) {
			return true;
		}
		
		//session.setAttribute("redirect-uri", request.getRequestURI());
		response.sendRedirect("/");
		
		return false;
	}
	
}
