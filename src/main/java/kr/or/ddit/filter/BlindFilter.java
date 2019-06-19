package kr.or.ddit.filter;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlindFilter implements Filter {
	Map<String, String> blindMap;
	private static Logger logger = LoggerFactory.getLogger(BlindFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		blindMap = new LinkedHashMap<String, String>();
		blindMap.put("192.168.207.126", "dsaf");
		blindMap.put("127.0.0.1", "dsafasdfasdfasdfasdf");
		blindMap.put("192.168.207.145", "zxcvxcvdsaf");
		logger.info("{} 필터 초기화", getClass().getSimpleName());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String ip = request.getRemoteAddr();
		if(blindMap.containsKey(ip)) {
			String blindMsg = blindMap.get(ip);
			//dispatch
//			request.setAttribute("blindMsg", blindMsg);
//			RequestDispatcher rd = request.getRequestDispatcher("/14/blind.jsp");
//			rd.forward(request, response);
			//redirect
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
			HttpServletResponse resp = (HttpServletResponse) response;
			session.setAttribute("blindMsg", blindMsg);
			logger.info("{} 가 {} 사유로 차단되었음", ip, blindMsg);
			resp.sendRedirect(req.getContextPath()+"/14/blind.jsp");
		}else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
