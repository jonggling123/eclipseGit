package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.MemberVO;

@CommandHandler
public class MemberViewController{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	IMemberService service = new MemberServiceImpl();
	
	@URIMapping("/member/memberView.do")
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mem_id = req.getParameter("who");
		String viewName = null;
		if(StringUtils.isBlank(mem_id)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "필수 파라미터 누락");
			return null;
		}
		if(logger.isDebugEnabled()) {
			logger.debug("{} 에 대한 상세조회", mem_id);
		}else {
			logger.info("{} 에 대한 상세조회", mem_id);
		}
		MemberVO member = service.retrieveMember(mem_id);
		req.setAttribute("member", member);
		
		viewName = "member/memberView";
		return viewName;
	}
}






