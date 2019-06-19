package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.wrapper.FirstSimpleWrapper;

/**
 * 모든 요청을 중간에 가로채서,
 * 해당 요청에 who 파라미터가 포함되었다면,
 * 해당 파라미터의 값을 "c001" 로 변경하자.
 *
 */
public class WrapperTestFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String who = request.getParameter("who");
		if(who==null) {
			chain.doFilter(request, response);
		}else {
			FirstSimpleWrapper wrapper = new FirstSimpleWrapper((HttpServletRequest)request);
			chain.doFilter(wrapper, response); //변질된 요청(wrapper)을 원본 request 대신 넘긴다
		}
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
