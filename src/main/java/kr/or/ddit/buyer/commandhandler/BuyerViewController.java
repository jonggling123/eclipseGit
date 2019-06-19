package kr.or.ddit.buyer.commandhandler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.BuyerVO;

@CommandHandler
public class BuyerViewController {
	IBuyerService service = new BuyerServiceImpl();
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@URIMapping("/buyer/buyerView.do")
	public String buyerViewHandler(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String logicalViewName = null;
		String buyer_id = req.getParameter("what");
		if(StringUtils.isBlank(buyer_id)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		if(logger.isDebugEnabled()) {
			logger.debug("{} 에 대한 상세조회", buyer_id);
		}else {
			logger.info("{} 에 대한 상세조회", buyer_id);
		}
		
		BuyerVO buyer = service.retrieveBuyer(buyer_id);
		req.setAttribute("buyer", buyer);
		logicalViewName = "buyer/buyerView";
		
		return logicalViewName;
	}
}
