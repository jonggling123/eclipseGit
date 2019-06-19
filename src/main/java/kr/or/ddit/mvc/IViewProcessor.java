package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IViewProcessor {
	public void setPrefix(String prefix);
	public void setSuffix(String suffix);
	
	/**
	 * 논리적인 뷰 네임을 받아서, 앞 뒤에 prefix 와 suffix 를 사용해
	 * 완전한 뷰 레이어의 경로를 찾아낸 후, forward 로 이동하거나,
	 * redirect 로 이동하기 위한 메서드
	 * @param viewName viewName 이 "redirect:"으로 시작되면 redirect, 그 외에는 forward로 이동.
	 * @param req
	 * @param resp
	 */
	public void viewProcess(String viewName
								, HttpServletRequest req, HttpServletResponse resp)
										 throws ServletException, IOException;
}
