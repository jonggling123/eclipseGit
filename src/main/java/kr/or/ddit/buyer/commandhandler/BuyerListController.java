package kr.or.ddit.buyer.commandhandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

@CommandHandler
public class BuyerListController {
	IBuyerService service = new BuyerServiceImpl();
	
	@URIMapping("/buyer/buyerList.do")
	public String buyerListHandler(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String logicalViewName = null;
		String param =  req.getParameter("page");
		
		long currentPage = 1;
		if(StringUtils.isNoneBlank(param) && StringUtils.isNumeric(param)) {
			currentPage = Long.parseLong(param);
		}
		
		PagingVO<BuyerVO> pagingVO = new PagingVO<BuyerVO>(3, 5);
		pagingVO.setCurrentPage(currentPage);
		long totalRecord = service.retrieveBuyerCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<BuyerVO> buyerList = service.retrieveBuyerList(pagingVO);
		pagingVO.setDataList(buyerList);
		
		boolean ajaxFlag = StringUtils.equalsIgnoreCase(req.getHeader("X-Requested-With"), "xmlhttprequest");
		String accept = req.getHeader("Accept");
		if(ajaxFlag && StringUtils.containsIgnoreCase(accept, "json")) {
			resp.setContentType("application/json;charset=UTF-8");
			ObjectMapper mapper = new ObjectMapper();
			try(
				PrintWriter out = resp.getWriter();
			){
				mapper.writeValue(out, pagingVO);
			}
		}else {
			req.setAttribute("pagingVO", pagingVO);
			logicalViewName = "buyer/buyerList";
		}
		
		return logicalViewName;
	}
}
