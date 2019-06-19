package board.service;

import java.util.List;

import board.dao.BoardDAOImpl;
import board.dao.IBoardDAO;
import board.vo.BoardVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.CommonException;
import kr.or.ddit.vo.PagingVO;

public class BoardServiceImpl implements IBoardService {
	IBoardDAO boardDAO = new BoardDAOImpl();

	@Override
	public ServiceResult createBoard(BoardVO board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardVO retrieveBoard(int bo_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BoardVO> retrieveBoardList(PagingVO<BoardVO> pagingVO) {
		List<BoardVO> boardList = null;
		
		boardList = boardDAO.selectBoardList(pagingVO);
		if(boardList.size()==0) {
			throw new CommonException("게시글 목록이 존재하지 않습니다.");
		}
		return boardList;
	}

	@Override
	public long retrieveBoardCount() {
		return boardDAO.selectBoardCount();
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
	public ServiceResult downloadPds(int pds_no) {
		// TODO Auto-generated method stub
		return null;
	}

}
