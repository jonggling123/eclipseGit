package kr.or.ddit.alba.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.alba.service.AlbaServiceImpl;
import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.AlbaVO;

@CommandHandler
public class AlbaListController {
	IAlbaService service = new AlbaServiceImpl();
	
	@URIMapping("/alba/albaList.do")
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<AlbaVO> albaList = service.retrieveAlbaList();
		req.setAttribute("albaList", albaList);
		String view = "alba/albaList";
		return view;
	}
}
