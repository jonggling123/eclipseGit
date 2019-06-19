package board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.BoardServiceImpl;
import board.service.IBoardService;
import board.vo.BoardVO;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.PagingVO;

@CommandHandler
public class BoardRetrieveController {
	IBoardService service = new BoardServiceImpl();
	
	@URIMapping(value="/board/boardList.do")
	public String boardList(HttpServletRequest req, HttpServletResponse resp) {
		String logicalViewName = null;
		PagingVO<BoardVO> pagingVO = new PagingVO<BoardVO>();
		long totalRecord = service.retrieveBoardCount();
		
		List<BoardVO> boardList = service.retrieveBoardList(pagingVO);
		
		return logicalViewName;
	}
}
