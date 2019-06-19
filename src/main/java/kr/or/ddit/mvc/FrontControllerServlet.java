package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.annotation.HandlerInvoker;
import kr.or.ddit.mvc.annotation.HandlerMapper;
import kr.or.ddit.mvc.annotation.IHandlerInvoker;
import kr.or.ddit.mvc.annotation.IHandlerMapper;
import kr.or.ddit.mvc.annotation.URIMappingInfo;

public class FrontControllerServlet extends HttpServlet {
	private IHandlerMapper handlerMapper;
	private IHandlerInvoker handlerInvoker;
	private IViewProcessor viewProcessor;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		//멤버 변수 객체 생성
		//1.handlerMapper
		//basePackages는 서블릿의 초기화 파라미터로 가져온다
		String basePackagesParam = config.getInitParameter("basePackages");
		String[] basePackages = basePackagesParam.split("\\s+"); //공백이 하나 이상
		handlerMapper = new HandlerMapper(basePackages);
		//2. handlerInvoker, viewProcessor
		handlerInvoker = new HandlerInvoker();
		viewProcessor = new ViewProcessor();
		//3.view의 prefix, suffix 설정
		String prefix = config.getInitParameter("prefix");
		String suffix = config.getInitParameter("suffix");
		viewProcessor.setPrefix(prefix);
		viewProcessor.setSuffix(suffix);
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			URIMappingInfo mappingInfo = handlerMapper.findCommandHandler(req);
			if(mappingInfo==null) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND
						, "해당 요청에 대한 커맨드 핸들러가 없음");
				return;
			}
			
			String viewName = handlerInvoker.invokeHandler(mappingInfo, req, resp);
			//viewName이 필요하지 않거나 코딩 실수
			if(viewName==null) {
				if(!resp.isCommitted()) { //코딩 실수
					resp.sendError(500);
				}
				return;
			}
			
			viewProcessor.viewProcess(viewName, req, resp);
		} catch(IllegalArgumentException e) {
			resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		}
		
	}
}
