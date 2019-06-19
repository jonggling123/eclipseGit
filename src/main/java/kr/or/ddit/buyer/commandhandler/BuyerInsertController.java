package kr.or.ddit.buyer.commandhandler;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.BuyerVO;

@CommandHandler
public class BuyerInsertController {
	IBuyerService service = new BuyerServiceImpl();
	
	@URIMapping("/buyer/buyerInsert.do")
	public String buyerInsertHandlerGet(HttpServletRequest req, HttpServletResponse resp) {
		String logicalViewName = "buyer/buyerForm";
		return logicalViewName;
	}
	
	@URIMapping(value="/buyer/buyerInsert.do", method=HttpMethod.POST)
	public String buyerInsertHandlerPost(HttpServletRequest req, HttpServletResponse resp) {
		String logicalViewName = null;
		BuyerVO buyer = new BuyerVO();
		req.setAttribute("buyer", buyer);
		
		try {
			BeanUtils.populate(buyer, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
		Map<String, String> errors = new LinkedHashMap<String, String>();
		req.setAttribute("errors", errors);
		
		boolean valid = validate(buyer, errors);
		String msg = null;
		if(valid) {
			ServiceResult result = service.createBuyer(buyer);
			switch (result) {
			case PKDUPLICATED:
				msg = "아이디가 중복되었습니다";
				logicalViewName = "buyer/buyerForm";
				break;
			case FAILED:
				msg = "서버 오류";
				logicalViewName = "buyer/buyerForm";
				break;

			default:
				logicalViewName = "redirect:/";
				break;
			}
		}else {
			logicalViewName = "buyer/buyerForm";
		}
		
		req.setAttribute("message", msg);
		return logicalViewName;
	}

	private boolean validate(BuyerVO buyer, Map<String, String> errors) {
		boolean valid = true;
		return valid;
	}
}
