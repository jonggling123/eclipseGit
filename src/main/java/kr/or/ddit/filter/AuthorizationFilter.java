package kr.or.ddit.filter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.Constants;
import kr.or.ddit.vo.MemberVO;

/**
 * 보호된 자원에 대한 요청에서 인증 여부를 확인한 후
 * 해당 자원에 대한 허가 여부를 확인하기 위한 필터
 *
 */
public class AuthorizationFilter implements Filter {
	private ServletContext application;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		application = filterConfig.getServletContext();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Map<String, List<String>> securedResources = (Map) application.getAttribute(Constants.SECUREDRESOURCENAME);
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String uri = req.getRequestURI();
		//sessionParameter 제거
		String[] tmp = uri.split(";");
		uri = tmp[0];
		//contextPath 제거
		uri = uri.substring(req.getContextPath().length());
		
		List<String> roles = securedResources.get(uri);
		boolean pass = true;
		if(roles!=null) {
			HttpSession session = req.getSession();
			MemberVO authMember = (MemberVO) session.getAttribute("authMember");
			String role = authMember.getMem_auth();
			if(!roles.contains(role)) {
				pass = false;
			}
		}
		
		if(pass) {
			chain.doFilter(request, response);
		}else {
			resp.sendError(HttpServletResponse.SC_FORBIDDEN);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
