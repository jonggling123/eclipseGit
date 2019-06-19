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
public class BuyerUpdateController {
	IBuyerService service = new BuyerServiceImpl();
	
	@URIMapping(value="/buyer/buyerUpdate.do", method=HttpMethod.POST)
	public String buyerUpdateHandler(HttpServletRequest req, HttpServletResponse resp) {
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
			ServiceResult result = service.modifyBuyer(buyer);
			if(ServiceResult.OK.equals(result)) {
				logicalViewName = "redirect:buyer/buyerView.do";
			}else {
				logicalViewName = "buyer/buyerView";
			}
		}else {
			logicalViewName = "buyer/buyerView";
		}
		req.setAttribute("message", msg);
		return logicalViewName;
	}

	private boolean validate(BuyerVO buyer, Map<String, String> errors) {
		boolean valid = true;
		
		return valid;
	}
}
