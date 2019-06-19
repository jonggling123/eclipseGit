package kr.or.ddit.common.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.FormSubmitEvent.MethodType;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.common.service.AuthenticateServiceImpl;
import kr.or.ddit.common.service.IAuthenticateService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.utils.CookieUtil;
import kr.or.ddit.utils.CookieUtil.TextType;
import kr.or.ddit.vo.MemberVO;
import oracle.jdbc.proxy.annotation.Methods;

@CommandHandler
public class LoginCheckController {
	IAuthenticateService service = new AuthenticateServiceImpl();
	
	@URIMapping(value="/login/loginCheck.do", method=HttpMethod.POST)
	public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 파라미터 확보(mem_id, mem_pass)
		String mem_id = request.getParameter("mem_id");
		String mem_pass = request.getParameter("mem_pass");
		String saveId = request.getParameter("saveId");
		
		HttpSession session = request.getSession(false);
		if(session==null || session.isNew()) {
			response.sendError(400, "정상절차에 의한 로그인 시도가 아님.");
			return null;
		}
		String message = null;
		String viewName = null;
	// 2. 검증(필수파라미터 전송)
		if(StringUtils.isBlank(mem_id) || StringUtils.isBlank(mem_pass)){
	// 3. 불통(누락) loginForm.jsp 로 이동(원본 요청의 파라미터가 생존한채 전달)
			message = "아이디나 비번 누락";
			viewName = "redirect:/login/loginForm.jsp";
		}else{
			MemberVO member = new MemberVO(mem_id, mem_pass);
			Object result = service.authenticate(member);
	// 4. 통과(아이디와 비번이 같으면 인증 성공)
			if(result instanceof MemberVO) {
				int maxAge = 0;
				if(StringUtils.equals(saveId, "saved")) {
					maxAge = 60*60*24*7;
				}
				Cookie idCookie = CookieUtil.createCookie("idCookie", mem_id, 
										request.getContextPath(), TextType.PATH, maxAge);
				response.addCookie(idCookie);
//	 	4-1. 인증 성공 : 웰컴 페이지로 이동(원본 요청을 전달하지 않고 이동)
				if(mem_id.equals("c001")) {
					((MemberVO) result).setMem_auth("ROLE_ADMIN");
				}else {
					((MemberVO) result).setMem_auth("ROLE_USER");
				}
				session.setAttribute("authMember", result);
				viewName = "redirect:/";
			}else{
//	 	4-2. 인증 실패 : loginForm.jsp 로 이동(원본 요청의 파라미터가 생존한채 전달)
				if(ServiceResult.INVALIDPASSWORD.equals(result)) {
					message = "비밀번호 오류";
				}else {
					message = "존재하지 않는 아이디";
				}
				
				session.setAttribute("failedId", mem_id);
				viewName = "redirect:/login/loginForm.jsp";
			}
		}
		session.setAttribute("message", message);
		return viewName;
	}
}
