package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.wrapper.FileUploadRequestWrapper;

@CommandHandler
public class MemberInsertController{
	IMemberService service = new MemberServiceImpl();

	@URIMapping("/member/memberInsert.do")
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 가입 양식 제공
		String view = "member/memberForm";
		return view;
	}

	@URIMapping(value="/member/memberInsert.do", method=HttpMethod.POST)
	public String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberVO member = new MemberVO();
		req.setAttribute("member", member);
		try {
			BeanUtils.populate(member, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}

		Map<String, String> errors = new LinkedHashMap<String, String>();
		req.setAttribute("errors", errors);
		
		boolean valid = validate(member, errors);
		
		//mem_img는 어떠한 제약도 없기 때문에 검증 대상이 아니다
		if(req instanceof FileUploadRequestWrapper) {
			FileUploadRequestWrapper wrapper = (FileUploadRequestWrapper) req;
			//파일 자체를 DB에 넣을거기 때문에 파일을 저장할 외부의 경로를 지정하지 않는다.
			FileItem imageFile = wrapper.getFileItem("mem_image");
			if(imageFile!=null) {
				String imageMime = imageFile.getContentType();
				if(imageMime==null || !imageMime.startsWith("image/")) {
					//1. 400 error
					//2. 그냥 이미지 저장 안함
				}else {
					byte[] imageBinary = imageFile.get();
					member.setMem_img(imageBinary);
				}
			}
		}
		
		String view = null;
		String msg = null;
		if (valid) {
			ServiceResult result = service.createMember(member);
			switch (result) {
			case PKDUPLICATED:
				view = "member/memberForm";
				msg = "아이디 중복, 딴거로 바꾸셈.";
				break;
			case FAILED:
				view = "member/memberForm";
				msg = "서버 오류, 잠시 뒤 다시 한번 해보셈.";
				break;
			default: // OK
				view = "redirect:/";
			}
		} else {
			view = "member/memberForm";
		}
		
		req.setAttribute("message", msg);
		return view;

	}

	private boolean validate(MemberVO member, Map<String, String> errors) {
		boolean valid = true;
		
		if (StringUtils.isBlank(member.getMem_id())) {
			valid = false;
			errors.put("mem_id", "회원아이디 누락");
		}
		if (StringUtils.isBlank(member.getMem_pass())) {
			valid = false;
			errors.put("mem_pass", "비밀번호 누락");
		}
		if (StringUtils.isBlank(member.getMem_name())) {
			valid = false;
			errors.put("mem_name", "회원명 누락");
		}
		if (StringUtils.isBlank(member.getMem_zip())) {
			valid = false;
			errors.put("mem_zip", "우편번호 누락");
		}
		if (StringUtils.isBlank(member.getMem_add1())) {
			valid = false;
			errors.put("mem_add1", "주소1 누락");
		}
		if (StringUtils.isBlank(member.getMem_add2())) {
			valid = false;
			errors.put("mem_add2", "주소2 누락");
		}
		if (StringUtils.isBlank(member.getMem_mail())) {
			valid = false;
			errors.put("mem_mail", "이메일 누락");
		}

		if (StringUtils.isNotBlank(member.getMem_memorialday())
				&& !member.getMem_memorialday().matches("\\d{4}-[0-9]{2}-\\d{2}")) {
			valid = false;
			errors.put("mem_memorialday", "날짜 형식 확인");
		}

		return valid;
	}
}
