package kr.or.ddit.mvc.annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IHandlerInvoker {
	/**
	 * 프론트 컨트롤러를 대신해서 커맨드 핸들러를 호출함.
	 * @param mappingInfo
	 * @param req
	 * @param resp
	 * @return 논리적인 뷰 네임(뷰 레이어에 대한 위치 정보)
	 */
	public String invokeHandler(URIMappingInfo mappingInfo
									, HttpServletRequest req, HttpServletResponse resp);
}
