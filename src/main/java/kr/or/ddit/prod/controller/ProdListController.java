package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.prod.dao.IOthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

@CommandHandler
public class ProdListController {
	IProdService service = new ProdServiceImpl();
	IOthersDAO othersDAO = new OthersDAOImpl();
	
	private void withOthersData(HttpServletRequest req) {
		// prodForm 에서 사용할 분류정보/거래처정보를 전달.
		req.setAttribute("lprodList", othersDAO.selectLprodList());
		req.setAttribute("buyerList", othersDAO.selectBuyerList(null));
	}
	
	@URIMapping("/prod/prodList.do")
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = null;
		withOthersData(req);
		
		long currentPage = 1;
		String pageParam = req.getParameter("page");
		ProdVO searchProd = new ProdVO();
		try {
			BeanUtils.populate(searchProd, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
		if(StringUtils.isNotBlank(pageParam) && StringUtils.isNumeric(pageParam)) {
			currentPage = Long.parseLong(pageParam);
		}	
		
		PagingVO<ProdVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		//검색 조건은 totalRecord세팅 전에 넣는다.
		pagingVO.setSearchData(searchProd);
		long totalRecord = service.retrieveProdCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<ProdVO> prodList = service.retrieveProdList(pagingVO);
		pagingVO.setDataList(prodList);
		
		String accept = req.getHeader("accept");
		
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-control", "no-cache");
		resp.addHeader("Cache-Control", "no-store");
		resp.setDateHeader("Expires", 0);
		
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			resp.setContentType("application/json;charset=UTF-8");
			ObjectMapper mapper = new ObjectMapper();
			try(
				PrintWriter out = resp.getWriter();
			){
				mapper.writeValue(out, pagingVO);
			}
			
		}else {
			req.setAttribute("pagingVO", pagingVO);
			
			view = "prod/prodList";
		}
		return view;
	}
}

















