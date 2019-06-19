package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.core.pattern.ConverterKeys;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
/*
 * Sbring 프레임웍 적용 규칙 (controller)
 * 0. extends httpservlet 삭제
 * 1. @webServlet 삭제 후 @commandhandler로 대체
 * 2. 해당하는 메서드에 @override 삭제 후 @urimapping("@webservlet의 매핑 주소") 으로 대체
 * 3. 해당 메서드의 접근제어를 public으로, 리턴타입을 String으로 변경
 * 4. 디코딩 구문 삭제
 * 5. 뷰 경로에서 컨텍스트패스(맨 마지막 슬래시 포함), 확장자 삭제
 * 6. 뷰 이동 코드 삭제 후 수정한 뷰 경로 리턴으로 대체
 * 
 */

@CommandHandler
public class MemberListController{
	IMemberService service = new MemberServiceImpl();
	
	@URIMapping("/member/memberList.do")
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//페이징 처리
		//1. "page"라는 이름의 파라미터를 가져온다(client가 요청한 페이지)
		//2. 요청받은 페이지로 currentPage 설정
		//2-1. 파라미터 검증
		//3. pagingVO 객체 생성 및 초기값 세팅(screensize, blocksize)		
		//3-1. pagingVO에 currentPage세팅 (startRow, endRow, startPage, endPage 연쇄 세팅)
		//3-2. totalRecord 세팅 (totalPage 연쇄 세팅)
		//3-3. dataList 세팅 (페이징 처리 대상 세팅)
		String param = req.getParameter("page");
		String searchType = req.getParameter("searchType");
		String searchWord = req.getParameter("searchWord");
		
		long currentPage = 1;
		if(StringUtils.isNotBlank(param) && StringUtils.isNumeric(param)) {
			currentPage = Long.parseLong(param);
		}
		PagingVO<MemberVO> pagingVO = new PagingVO<MemberVO>(3, 5);
		pagingVO.setSearchType(searchType);
		pagingVO.setSearchWord(searchWord);
		pagingVO.setCurrentPage(currentPage);
		long totalRecord = service.retrieveMemberCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<MemberVO> memberList = service.retrieveMemberList(pagingVO);
		pagingVO.setDataList(memberList);
		
	 	boolean ajaxFlag = StringUtils.equalsIgnoreCase(req.getHeader("X-Requested-With"), "xmlhttprequest");
		String accept = req.getHeader("Accept");
	 	String viewName = null;
		if(ajaxFlag && StringUtils.containsIgnoreCase(accept, "json")) { //비동기 요청, datatype : json
			resp.setContentType("application/json;charset=UTF-8");
			ObjectMapper mapper = new ObjectMapper();
			try(
				PrintWriter out = resp.getWriter();
			){
				mapper.writeValue(out, pagingVO);
			}
			
		}else {
			//5. 뷰 선택 및 이동
			req.setAttribute("pagingVO", pagingVO);
			viewName = "member/memberList";
		}
		return viewName;
	}
}
