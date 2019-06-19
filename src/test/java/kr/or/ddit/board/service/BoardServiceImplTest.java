package kr.or.ddit.board.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.exception.CommonException;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public class BoardServiceImplTest {
	IBoardService service = new BoardServiceImpl();
	PagingVO<BoardVO> pagingVO;

	@Before
	public void setUp() throws Exception {
		pagingVO = new PagingVO<BoardVO>();
	}

	@Test(expected=CommonException.class)
	public void retrieveBoardListTest() {
		List<BoardVO> list = service.retrieveBoardList(pagingVO);
	}
	
	@Test
	public void retrieveBoardCountTest() {
		long cnt = service.retrieveBoardCount(pagingVO);
		assertNotEquals(0, cnt);
	}
	
	@Test(expected=CommonException.class)
	public void retrieveBoardNotExistTest() {
		BoardVO board = service.retrieveBoard(3465);
	}
	
	@Test
	public void retrieveBoardTest() {
		BoardVO board = service.retrieveBoard(343);
		assertNotEquals(null, board);
	}

}
