package kr.or.ddit.mvc.annotation;

import javax.servlet.http.HttpServletRequest;

public interface IHandlerMapper {
	/**
	 * 요청에 대한 처리자(command handler)를 검색
	 * @param req
	 * @return 커맨드 핸들러를 검색에 실패한 경우, null 반환.
	 */
	URIMappingInfo findCommandHandler(HttpServletRequest req);
}
