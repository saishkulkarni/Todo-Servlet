package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter({"/add-task","/delete","/complete","/update-task"})
public class CheckSessionFilter implements Filter
{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse resp=(HttpServletResponse)response;
		
		
		if(req.getSession().getAttribute("customer")==null)
		{
			resp.getWriter().print("<h1 align='center' style='color:red'>Session Expired</h1>");
			req.getRequestDispatcher("Login.html").include(req, resp);
		}
		else {
			chain.doFilter(request, response);
		}
	}

}
