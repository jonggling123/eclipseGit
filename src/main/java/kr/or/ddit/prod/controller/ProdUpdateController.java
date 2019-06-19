package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.prod.dao.IOthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;
import kr.or.ddit.wrapper.FileUploadRequestWrapper;

@CommandHandler
public class ProdUpdateController {
	IProdService service = new ProdServiceImpl();
	IOthersDAO othersDAO = new OthersDAOImpl();
	
	private void withOthersData(HttpServletRequest req) {
		// prodForm 에서 사용할 분류정보/거래처정보를 전달.
		req.setAttribute("lprodList", othersDAO.selectLprodList());
		req.setAttribute("buyerList", othersDAO.selectBuyerList(null));
	}
	
	@URIMapping("/prod/prodUpdate.do")
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String prod_id = req.getParameter("what");
		if(StringUtils.isBlank(prod_id)) {
			resp.sendError(400);
			return null;
		}
		withOthersData(req);
		ProdVO prod = service.retrieveProd(prod_id);
		req.setAttribute("prod", prod);
		String view = "prod/prodForm";
		return view;
	}
	
	@URIMapping(value="/prod/prodUpdate.do", method=HttpMethod.POST)
	public String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		withOthersData(req);
		ProdVO prod = new ProdVO();
		req.setAttribute("prod", prod);
		try {
			BeanUtils.populate(prod, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		Map<String, String> errors = new LinkedHashMap<String, String>();
		req.setAttribute("errors", errors);
		
		//파일 업로드 처리
		//1. request가 원본인지 wrapper인지 확인
		if(req instanceof FileUploadRequestWrapper) {
			FileUploadRequestWrapper wrapper = (FileUploadRequestWrapper) req;
			FileItem fileItem = wrapper.getFileItem("prod_image");
			if(fileItem!=null) {
				String fileSystemPath = req.getServletContext().getRealPath("/prodImages");
				File saveFolder = new File(fileSystemPath);
				//상품 이미지의 저장 파일명은 validate() 전에 만들어져야 한다.
				String savename = UUID.randomUUID().toString();
				try(
					InputStream is = fileItem.getInputStream();
				){
					FileUtils.copyInputStreamToFile(is, new File(saveFolder, savename));
					//기존 파일 삭제
					String originName = prod.getProd_img();
					FileUtils.deleteQuietly(new File(saveFolder, originName));
					prod.setProd_img(savename);
					wrapper.deleteTempFiles();
				}
			}
		}
		
		validate(prod, errors);
		String view = null;
		String msg = null;
		if(errors.size()==0) {
			ServiceResult result = service.modifyProd(prod);
			if(ServiceResult.OK.equals(result)) {
				view = "redirect:/prod/prodView.do?what="+prod.getProd_id();
			}else {
				view = "prod/prodForm";
				msg = "서버 오류, 다시 시도.";
			}
		}else {
			view = "prod/prodForm";
		}
		
		req.setAttribute("message", msg);
		
		return view;
	}

	private boolean validate(ProdVO prod, Map<String, String> errors) {
		boolean valid = true;
		
		if (StringUtils.isBlank(prod.getProd_id())) {
			valid = false;
			errors.put("prod_id", "상품코드 누락");
		}
		if (StringUtils.isBlank(prod.getProd_name())) {
			valid = false;
			errors.put("prod_name", "상품명 누락");
		}
		if (StringUtils.isBlank(prod.getProd_lgu())) {
			valid = false;
			errors.put("prod_lgu", "상품분류코드 누락");
		}
		if (StringUtils.isBlank(prod.getProd_buyer())) {
			valid = false;
			errors.put("prod_buyer", "거래처코드 누락");
		}
		if (prod.getProd_cost()==null) {
			valid = false;
			errors.put("prod_cost", "구매가 누락");
		}
		if (prod.getProd_price()==null) {
			valid = false;
			errors.put("prod_price", "소비자가 누락");
		}
		if (prod.getProd_sale()==null) {
			valid = false;
			errors.put("prod_sale", "판매가 누락");
		}
		if (StringUtils.isBlank(prod.getProd_outline())) {
			valid = false;
			errors.put("prod_outline", "상품개략설명 누락");
		}
//		if (StringUtils.isBlank(prod.getProd_img())) {
//			valid = false;
//			errors.put("prod_img", "상품이미지 누락");
//		}
		if (prod.getProd_totalstock()==null) {
			valid = false;
			errors.put("prod_totalstock", "재고량 누락");
		}
		if (prod.getProd_properstock()==null) {
			valid = false;
			errors.put("prod_properstock", "안전재고수량 누락");
		}
		return valid;		
	}
}


























