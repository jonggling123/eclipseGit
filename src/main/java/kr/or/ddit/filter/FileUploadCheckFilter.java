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

import kr.or.ddit.wrapper.FileUploadRequestWrapper;

/**
 * Multipart request 를 wrapper 로 변경하는 역할
 *
 */
public class FileUploadCheckFilter implements Filter {
	private static Logger logger = LoggerFactory.getLogger(FileUploadCheckFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	/*
	1. 파일이 업로드 되는지 확인(multipart 인지 확인)
	 -> encType -> header : content-type
	2. 비어있는 parameterMap을 채워줘야 함
	 -> wrapper 생성
	multipart라면 request를 wrapper로 변경
	아니라면 그대로
	*/
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//multipart request 여부 판단
		String contentType = request.getContentType();
		if(contentType!=null && contentType.startsWith("multipart/")) {
			HttpServletRequest req = (HttpServletRequest)request;
			logger.info("{} 요청 필터링 후 wrapper로 변경", req.getRequestURI());
			FileUploadRequestWrapper wrapper = 
					new FileUploadRequestWrapper(req);
			chain.doFilter(wrapper, response);
		}else {
			chain.doFilter(request, response);
		}

	}

	@Override
	public void destroy() {
		logger.info("{} 필터 소멸", getClass().getSimpleName());

	}

}
