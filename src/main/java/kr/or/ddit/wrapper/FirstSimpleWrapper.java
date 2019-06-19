package kr.or.ddit.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class FirstSimpleWrapper extends HttpServletRequestWrapper {

	public FirstSimpleWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String name) {
		if("who".equals(name)) { //parameter 가 who
			return "c001";
		}else { //그 외
			return super.getParameter(name); //부모가 가진 메서드 호출(그대로 사용)
		}
	}
	
	

}
