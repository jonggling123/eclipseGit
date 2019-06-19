package kr.or.ddit.servlet03;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceType;

@WebServlet("/index.do")
public class IndexControllerServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageParam = request.getParameter("includePage");
		String includePage = null;
		int statusCode = 0;
		if(StringUtils.isNotBlank(pageParam)){
			try{
				ServiceType serviceType = ServiceType.valueOf(pageParam.toUpperCase());
				includePage = serviceType.getServicePage();
			}catch(IllegalArgumentException e){
				statusCode = HttpServletResponse.SC_NOT_FOUND;
			}
		}
		if(statusCode!=0){
			response.sendError(statusCode);
		}else {
			request.setAttribute("includePage", includePage);
			
			String view = "/WEB-INF/views/index.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		}
		
	}
}
