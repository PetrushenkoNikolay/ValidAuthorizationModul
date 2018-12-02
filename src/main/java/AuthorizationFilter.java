

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthorizationFilter
 */
public class AuthorizationFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthorizationFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpSession session = httpRequest.getSession();
		String name = (String) session.getAttribute("name");
		String nameInReq = httpRequest.getParameter("userName");
		System.out.println("Session attrbute \"name\" is "+" \""+name+"\"");
		if (name==null) {
			if (nameInReq!=null&&!nameInReq.equals("")) {
				session.setAttribute("name", nameInReq);
				httpRequest.getRequestDispatcher("AuthorizationServlet").forward(request, response);
			} else {
				httpRequest.getRequestDispatcher("/index.jsp").include(request, response);
				response.getWriter().println("Entered empty name");
			}
		} else {
			if (name.equals("user")||name.equals("admin")) {
				httpRequest.getRequestDispatcher("AuthorizationServlet").forward(request, response);
			} else if (nameInReq!=null&&!nameInReq.equals("")) {
				session.setAttribute("name", nameInReq);
				httpRequest.getRequestDispatcher("AuthorizationServlet").forward(request, response);
			} else {
				httpRequest.getRequestDispatcher("/index.jsp").include(request, response);
				response.getWriter().println("Enterd empty name");
			}
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
