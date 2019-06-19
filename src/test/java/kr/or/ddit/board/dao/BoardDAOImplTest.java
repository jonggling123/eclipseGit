package kr.or.ddit.board.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public class BoardDAOImplTest {
	IBoardDAO dao = new BoardDAOImpl();
	PagingVO<BoardVO> pagingVO;
	
	@Before
	public void setUp() throws Exception {
		pagingVO = new PagingVO<BoardVO>();
	}

	@Test
	public void selectBoardListTest() {
		List<BoardVO> list = dao.selectBoardList(pagingVO);
		assertEquals(0, list.size());
	}
	
	@Test
	public void selectBoardCountTest() {
		long cnt = dao.selectBoardCount(pagingVO);
		assertNotEquals(0, cnt);
	}
	
	@Test
	public void selectBoardTest() {
		BoardVO a = dao.selectBoard(343);
		assertNotEquals(null, a);
	}
}
