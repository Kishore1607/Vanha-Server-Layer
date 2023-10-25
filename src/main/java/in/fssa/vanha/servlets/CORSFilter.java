package in.fssa.vanha.servlets;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class CORSFilter
 */
@WebFilter("/*")
public class CORSFilter extends HttpFilter implements Filter {

	/**
	 * Initializes a new instance of the CORSFilter class.
	 * 
	 * This constructor creates an instance of the CORSFilter class, which can be
	 * used to manage Cross-Origin Resource Sharing (CORS) settings for a web
	 * application. CORSFilter is typically used to configure the allowed origins,
	 * HTTP methods, and headers for handling cross-origin requests in a web
	 * application.
	 * 
	 * Example usage:
	 * 
	 * <pre>
	 * CORSFilter corsFilter = new CORSFilter();
	 * corsFilter.setAllowedOrigins("https://example.com", "https://api.example.com");
	 * corsFilter.setAllowedMethods("GET", "POST", "PUT");
	 * corsFilter.setAllowedHeaders("Content-Type", "Authorization");
	 * </pre>
	 * 
	 * It is important to configure CORS settings to enhance security and control
	 * access to resources on your web server.
	 */
	public CORSFilter() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * This method is an implementation of the Servlet Filter interface and is used
	 * to handle HTTP requests and responses, allowing for Cross-Origin Resource
	 * Sharing (CORS) in a web application. It adds appropriate headers to the HTTP
	 * response to enable cross-origin requests from any origin and define which
	 * HTTP methods and headers are allowed.
	 *
	 * @param request  The ServletRequest object representing the HTTP request.
	 * @param response The ServletResponse object representing the HTTP response.
	 * @param chain    A FilterChain object that provides a mechanism to invoke the
	 *                 next filter in the chain, if any, or the resource at the end
	 *                 of the chain.
	 *
	 * @throws IOException      If an I/O error occurs during the execution of this
	 *                          filter.
	 * @throws ServletException If a servlet-specific error occurs during the
	 *                          execution of this filter.
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub

		HttpServletResponse res = (HttpServletResponse) response;
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		res.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
		res.setContentType("application/json");

		chain.doFilter(request, response);
	}

}
