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
    public void doFilter(ServletRequest req, ServletResponse resp,
            FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest servletRequest = (HttpServletRequest) req;
        HttpServletResponse servletResponse = (HttpServletResponse) resp;
        HttpSession session = servletRequest.getSession();

        // get request URI
        String path = servletRequest.getRequestURI();
        System.out.println(path);
        
        // get email from session
        String email = (String) session.getAttribute("email");

        /*创建类Constants.java，里面写的是无需过滤的页面
        for (int i = 0; i < Constants.NoFilter_Pages.length; i++) {

            if (path.indexOf(Constants.NoFilter_Pages[i]) > -1) {
                chain.doFilter(servletRequest, servletResponse);
                return;
            }
        }*/
        
        // 登陆页面无需过滤
        if(path.indexOf("/index.jsp") > -1) {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }
        if(path.indexOf("/signUp.jsp") > -1) {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }
        
        // 判断如果没有取到员工信息,就跳转到登陆页面
        if (email == null || "".equals(email)) {
            // 跳转到登陆页面
            servletResponse.sendRedirect("../index.jsp");
        } else {
            // 已经登陆,继续此次请求
            chain.doFilter(req, resp);
        }

    }

    @Override
    public void destroy() {

    }

}