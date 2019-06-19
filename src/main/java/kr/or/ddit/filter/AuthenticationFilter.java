package kr.or.ddit.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.Constants;

/**
 * 정보 보호를 위한 접근 제어
 * 1. 보호된 자원에 대해 접근시 인증 여부 확인(인증 체크)
 * 2. 인증 여부 확인후 해당 자원에 대한 권한 부여 여부 확인(인가 체크)
 *
 */
public class AuthenticationFilter implements Filter {
	Map<String, List<String>> securedResources;
	private static Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		securedResources = new LinkedHashMap<String, List<String>>();
		//공유
		ServletContext application = filterConfig.getServletContext();
		application.setAttribute(Constants.SECUREDRESOURCENAME, securedResources);
		//properties 읽기
		String securePath = filterConfig.getInitParameter("securePath");
		ResourceBundle bundle = ResourceBundle.getBundle(securePath);
		Enumeration<String> keys = bundle.getKeys();
		while (keys.hasMoreElements()) {
			String securedURI = (String) keys.nextElement();
			String roles = bundle.getString(securedURI);
			List<String> roleList = Arrays.asList(roles.split("\\s*,\\s*"));
			securedResources.put(securedURI.trim(), roleList);
			logger.info("보호 자원 - {} : {}", securedURI, roles);
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		//sessionParameter 제거
		String[] tmp = uri.split(";");
		uri = tmp[0];
		//contextPath 제거
		uri = uri.substring(req.getContextPath().length());
		//판단
		boolean pass = true;
		if(securedResources.containsKey(uri)) {
			HttpSession session = req.getSession();
			Object authMember = session.getAttribute("authMember");
			if(authMember==null) { //통과 시키면 안됨(보안 목록에 있는데 로그인 안한상태(권한 없음))
				pass = false;
			}
		}
		//이동
		if(pass) {
			chain.doFilter(request, response);
		}else {
			//잘못된 요청이므로 요청 자체를 유지할 필요가 없다
			//redirect
			resp.sendRedirect(req.getContextPath()+"/login/loginForm.jsp");
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
