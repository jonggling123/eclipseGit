package kr.or.ddit.alba.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.alba.service.AlbaServiceImpl;
import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.AlbaVO;

@CommandHandler
public class AlbaViewController {
	IAlbaService service = new AlbaServiceImpl();

	@URIMapping("/alba/albaView.do")
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String who = req.getParameter("who");
		int sc = 0;
		if(StringUtils.isBlank(who)) {
			sc = HttpServletResponse.SC_BAD_REQUEST;
		}else {
			AlbaVO albaVO = service.retrieveAlba(who);
			req.setAttribute("albaVO", albaVO);
			if(albaVO==null) {
				sc = HttpServletResponse.SC_NOT_FOUND;
			}
		}
		String view = null;
		if(sc!=0) {
			resp.sendError(sc);
		}else {
			view = "alba/albaView";
		}
		return view;
	}
}
