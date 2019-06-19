package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.BoardDAOImpl;
import kr.or.ddit.board.dao.IBoardDAO;
import kr.or.ddit.board.dao.IPdsDAO;
import kr.or.ddit.board.dao.PdsDAOImpl;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.CommonException;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.PdsVO;
import kr.or.ddit.vo.ReplyVO;

public class BoardServiceImpl implements IBoardService {
	IBoardDAO boardDAO = new BoardDAOImpl();
	IPdsDAO pdsDAO = new PdsDAOImpl();

	@Override
	public ServiceResult createBoard(BoardVO board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BoardVO> retrieveBoardList(PagingVO<BoardVO> pagingVO) {
		List<BoardVO> boardList = boardDAO.selectBoardList(pagingVO);
		
		if(boardList.size()==0) {
			throw new CommonException("게시글이 존재하지 않습니다.");
		}
		return boardList;
	}

	@Override
	public long retrieveBoardCount(PagingVO<BoardVO> pagingVO) {
		return boardDAO.selectBoardCount(pagingVO);
	}

	@Override
	public BoardVO retrieveBoard(int bo_no) {
		BoardVO board = null;
		
		//조회수 증가 처리
		int cnt = boardDAO.incrementHit(bo_no);
		if(cnt<=0) {
			throw new CommonException(bo_no+"에 해당하는 게시글이 없습니다.");
		}else {
			board = boardDAO.selectBoard(bo_no);
		}
		return board;
	}

	@Override
	public ServiceResult singo(int bo_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult likeOrHate(BoardVO board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult modifyBoard(BoardVO board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult removeBoard(BoardVO board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PdsVO downloadPds(int pds_no) {
		// TODO Auto-generated method stub
		return null;
	}

}
