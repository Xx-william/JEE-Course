package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest servletRequest = (HttpServletRequest) req;
		HttpServletResponse servletResponse = (HttpServletResponse) resp;
		HttpSession session = servletRequest.getSession();

		// get request URI
		String path = servletRequest.getRequestURI();
		System.out.println(path);

		// get email from session
		String email = (String) session.getAttribute("email");

		// the pages no need filter
		if (path.indexOf("/index.jsp") > -1) {
			chain.doFilter(servletRequest, servletResponse);
			return;
		}
		if (path.indexOf("/signUp.jsp") > -1) {
			chain.doFilter(servletRequest, servletResponse);
			return;
		}

		// if we don't get the information of the login we redirect to the
		// index.jsp
		if (email == null || "".equals(email)) {

			servletResponse.sendRedirect("../index.jsp");
		} else {
			// user already logded in
			chain.doFilter(req, resp);
		}

	}

	@Override
	public void destroy() {

	}

}