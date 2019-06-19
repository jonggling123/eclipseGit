package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 필터 사용단계
 * 1. Filter 구현체 작성
 * 	1) lifecycle 콜백 : init, destroy
 * 	2) 요청 콜백 : doFilter
 * 				*** 어느 시점이든 chain, doFilter를 호출하여,
 * 					다음 필터나 최종 자원쪽으로 요청을 전달
 * 					doFilter 메서드 호출점을 중심으로 요청/응답 필터링 코드 분리
 * 				annotation으로도 가능하지만 잘 사용하지 않음
 * 				(순서가 없기 때문에 )
 * 2. web.xml 에 필터 등록
 * 	-> FilterChain이 형성됨(필터를 등록한 순서에 따라 필터링 순서가 결정됨)
 * 		servlet 3.0부터 @WebFilter 로 등록할 수 있음(필터링 순서가 무작위)
 * 3. 필터 매핑 설정
 * 	url-pattern : 확장자 매핑(*.do), 경로 매핑(/member/*)
 *
 */
public class FirstFilter implements Filter{
	private static Logger logger = LoggerFactory.getLogger(FirstFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("{} 필터 생성 및 초기화", this.getClass().getSimpleName());
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//요청 필터링
		HttpServletRequest req = (HttpServletRequest) request;
		logger.info("{} 요청이 필터링 되었음. ", req.getRequestURI());
		chain.doFilter(request, response); //응답과 요청에 대한 기준
		
		//응답 필터링
		logger.info("{} 요청에 대한 응답 필터링. ", req.getRequestURI());
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
}
