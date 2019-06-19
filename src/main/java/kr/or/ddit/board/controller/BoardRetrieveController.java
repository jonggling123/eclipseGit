package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.board.dao.BoardDAOImpl;
import kr.or.ddit.board.dao.ReplyDAOImpl;
import kr.or.ddit.board.service.BoardReplyServiceImpl;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardReplyService;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.exception.CommonException;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;

/**
 * 게시글 목록조회, 상세조회
 *
 */
@CommandHandler
public class BoardRetrieveController {
	IBoardService boardService = new BoardServiceImpl();
	
	//목록조회 메서드
	@URIMapping("/board/boardList.do")
	public String boardList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String page = req.getParameter("page");
		String searchType = req.getParameter("searchType");
		String searchWord = req.getParameter("searchWord");
		
		PagingVO<BoardVO> pagingVO = new PagingVO<BoardVO>();
		
		long currentPage = 1;
		if(StringUtils.isNoneBlank(page) && StringUtils.isNumeric(page)) {
			currentPage = Long.parseLong(page);
		}
		pagingVO.setSearchType(searchType);
		pagingVO.setSearchWord(searchWord);
		pagingVO.setCurrentPage(currentPage);
		
		long totalRecord = boardService.retrieveBoardCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<BoardVO> boardList = boardService.retrieveBoardList(pagingVO);
		pagingVO.setDataList(boardList);
		
		boolean ajaxFlag = 
				StringUtils.containsIgnoreCase(req.getHeader("X-Requested-With"), "xmlhttprequest");
		String accept = req.getHeader("Accept");
		if(ajaxFlag && StringUtils.containsIgnoreCase(accept, "json")) {
			resp.setContentType("application/json;charset=UTF-8");
			ObjectMapper mapper = new ObjectMapper();
			try(
				PrintWriter out = resp.getWriter();
			){
				mapper.writeValue(out, pagingVO);
			}
			return null;
		}else {
			req.setAttribute("pagingVO", pagingVO);
			return "board/boardList";
		}
		
	}
	
	//상세조회 메서드
	@URIMapping("/board/boardView.do")
	public String boardView(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String logicalViewName = null;
		String what = req.getParameter("what");
		
		int bo_no = 0;
		if(StringUtils.isNotBlank(what) && StringUtils.isNumeric(what)) {
			bo_no = Integer.parseInt(what);
			
			BoardVO board = boardService.retrieveBoard(bo_no);
			req.setAttribute("board", board);
			logicalViewName = "board/datatable";
		}else {
			//web.xml에 error code에 따라 view를 설정해놨기 때문에
			//응답이 view에서 나가는 구조는 같다
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
		
		return logicalViewName;
	}
	
	@URIMapping("/reply/replyList.do")
	public String replyList(HttpServletRequest req, HttpServletResponse resp) throws JsonGenerationException, JsonMappingException, IOException {
		String logicalViewName = null;
		
		String page = req.getParameter("page");
		
		PagingVO<ReplyVO> pagingVO = new PagingVO<>(10,5);
		
		long currentPage = 1;
		if(StringUtils.isNoneBlank(page) && StringUtils.isNumeric(page)) {
			currentPage = Long.parseLong(page);
		}
		pagingVO.setCurrentPage(currentPage);
		
		String what = req.getParameter("what");
		
		int bo_no = 0;
		if(StringUtils.isNotBlank(what) && StringUtils.isNumeric(what)) {
			bo_no = Integer.parseInt(what);
			
			BoardVO board = boardService.retrieveBoard(bo_no);
			List<ReplyVO> replyList = board.getReplyList();
			long totalRecord = replyList.size();
			pagingVO.setTotalRecord(totalRecord);
			
//			int start = Integer.parseInt(pagingVO.getStartRow()+"");
//			int end = Integer.parseInt(pagingVO.getEndRow()+"");
//			pagingVO.setDataList(replyList.subList(start,end+1));
//			pagingVO.setDataList(replyList);
			
			boolean ajaxFlag = 
					StringUtils.containsIgnoreCase(req.getHeader("X-Requested-With"), "xmlhttprequest");
			String accept = req.getHeader("Accept");
			if(ajaxFlag && StringUtils.containsIgnoreCase(accept, "json")) {
				resp.setContentType("application/json;charset=UTF-8");
				ObjectMapper mapper = new ObjectMapper();
				try(
						PrintWriter out = resp.getWriter();
						){
					mapper.writeValue(out, pagingVO);
				}
				return null;
			}else {
				req.setAttribute("pagingVO", pagingVO);
				logicalViewName = "board/boardView";
			}
		}else {
			//web.xml에 error code에 따라 view를 설정해놨기 때문에
			//응답이 view에서 나가는 구조는 같다
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
		return logicalViewName;
	}
}
