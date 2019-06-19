package board.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import board.dao.BoardDAOImpl;
import board.dao.IBoardDAO;
import board.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public class BoardServiceImplTest {
	IBoardService service = new BoardServiceImpl();
	IBoardDAO dao = new BoardDAOImpl();
	PagingVO<BoardVO> pagingVO;
	
	@Before
	public void setUp() {
		pagingVO = new PagingVO<BoardVO>();
		pagingVO.setTotalRecord(dao.selectBoardCount());
		pagingVO.setCurrentPage(1);
	}
	
	@Test
	public void test() {
		List<BoardVO> list = service.retrieveBoardList(pagingVO);
		assertNotEquals(0, list);
	}

}
