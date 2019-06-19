package board.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import board.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public class BoardDAOImplTest {
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
		long cnt = dao.selectBoardCount();
		assertNotEquals(0, cnt);
	}
	
	@Test
	public void selectBoardListTest() {
		List<BoardVO> boardList = dao.selectBoardList(pagingVO);
		assertNotEquals(0, boardList.size());
	}

}
