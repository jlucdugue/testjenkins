package fr.imie;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.resteasy.spi.HttpResponse;

/**
 * Servlet Filter implementation class SecurityFilter
 */
@WebFilter(dispatcherTypes = { DispatcherType.REQUEST }, urlPatterns = { "/*" })
public class SecurityFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public SecurityFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		Boolean resourceAllow = true;
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		String url = httpServletRequest.getRequestURI();
		if (httpServletRequest.getQueryString()!=null){
			url = url.concat("?").concat(httpServletRequest.getQueryString());
		}

		if (!url.contains("css") && !url.contains("js")
				&& !url.contains("login") && !url.contains("Login")) {
			resourceAllow = false;
		}

		UserDTO connectedUser = (UserDTO) httpServletRequest.getSession()
				.getAttribute("connectedUser");

		if (!resourceAllow && connectedUser == null) {
			httpServletRequest.getSession().setAttribute("askedResource", url);
			httpServletResponse.sendRedirect("Login");
		} else {
			chain.doFilter(request, response);
		}

		UserDTO recentConnectdUser = (UserDTO) httpServletRequest.getSession()
				.getAttribute("connectedUser");
		if (connectedUser == null && recentConnectdUser != null) {
			String askedResource = (String) httpServletRequest.getSession()
					.getAttribute("askedResource");
			if(askedResource==null){
				askedResource="UserList";
			}
			httpServletRequest.getSession()
			.removeAttribute("askedResource");
			httpServletResponse.sendRedirect(askedResource);
		} else if (connectedUser != null && recentConnectdUser == null) {
			httpServletResponse.sendRedirect("Login");
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
