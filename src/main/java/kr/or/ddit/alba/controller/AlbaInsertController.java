package kr.or.ddit.alba.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.alba.dao.CodeDAOImpl;
import kr.or.ddit.alba.dao.ICodeDAO;
import kr.or.ddit.alba.service.AlbaServiceImpl;
import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.AlbaVO;

@CommandHandler
public class AlbaInsertController {
	ICodeDAO codeDAO = new CodeDAOImpl();
	IAlbaService service = new AlbaServiceImpl();
	
	private void setCodeInScope(HttpServletRequest req){
		req.setAttribute("licenses", codeDAO.selectLicense());
		req.setAttribute("grades", codeDAO.selectGrades());
	}
	
	@URIMapping("/alba/albaInsert.do")
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setCodeInScope(req);
		String view = "alba/albaForm";
		return view;
	}
	
	
	@URIMapping(value="/alba/albaInsert.do", method=HttpMethod.POST)
	public String doPost(HttpServletRequest req, 
							HttpServletResponse resp) throws ServletException, IOException {
		setCodeInScope(req);
		
		Map<String, String[]> parameterMap = req.getParameterMap();
		AlbaVO alba = new AlbaVO();
		req.setAttribute("albaVO", alba);
		
		try {
			BeanUtils.populate(alba, parameterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
		// 검증
		Map<String, String> errors = new HashMap<String, String>();
		req.setAttribute("errors", errors);
		boolean valid = validate(alba, errors);
		
		String view = null;
		
		if(valid) {
			ServiceResult result = service.createAlba(alba);
			if (ServiceResult.OK.equals(result)) {
				view = "redirect:/alba/albaList.do";
			} else {
				req.setAttribute("message", "등록 실패, 다시 시도");
				view = "alba/albaForm";
			}
		}else {
			view = "alba/albaForm";
		}
		
		return view;
	}
	
	private boolean validate(AlbaVO alba, Map<String, String> errors) {
		boolean valid = true;
		// 검증
				if (StringUtils.isBlank(alba.getAl_id())) {
					valid = false;
					errors.put("al_id", "아이디 누락");
				}
				if (StringUtils.isBlank(alba.getAl_name())) {
					valid = false;
					errors.put("al_name", "이름 누락");
				}
				if (alba.getAl_age()==null) {
					valid = false;
					errors.put("al_age", "나이 누락");
				}
				if (StringUtils.isBlank(alba.getAl_address())) {
					valid = false;
					errors.put("al_address", "주소 누락");
				}
				if (StringUtils.isBlank(alba.getAl_hp())) {
					valid = false;
					errors.put("al_hp", "휴대폰 누락");
				}
				if (StringUtils.isBlank(alba.getGr_code())) {
					valid = false;
					errors.put("gr_code", "최종학력 누락");
				}
				if (StringUtils.isBlank(alba.getAl_gen())) {
					valid = false;
					errors.put("al_gen", "성별 누락");
				}
				if (StringUtils.isBlank(alba.getAl_btype())) {
					valid = false;
					errors.put("al_btype", "혈액형 누락");
				}
				if (StringUtils.isBlank(alba.getAl_mail())) {
					valid = false;
					errors.put("al_mail", "이메일 누락");
				}
		return valid;
	}
}











