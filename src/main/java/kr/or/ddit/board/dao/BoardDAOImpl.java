package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public class BoardDAOImpl implements IBoardDAO {
	SqlSessionFactory sessionFactory = CustomSqlSessionFactoryBuilder.getSessionFactory();

	@Override
	public int insertBoard(BoardVO board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BoardVO> selectBoardList(PagingVO<BoardVO> pagingVO) {
		List<BoardVO> boardList = null;
		
		try(
			SqlSession sqlSession = sessionFactory.openSession();
		){
			boardList = sqlSession.getMapper(IBoardDAO.class).selectBoardList(pagingVO);
		}
		return boardList;
	}

	@Override
	public long selectBoardCount(PagingVO<BoardVO> pagingVO) {
		long totalRecord = 0;
		
		try(
			SqlSession sqlSession = sessionFactory.openSession();
		){
			totalRecord = sqlSession.getMapper(IBoardDAO.class).selectBoardCount(pagingVO);
		}
		return totalRecord;
	}

	@Override
	public BoardVO selectBoard(int bo_no) {
		BoardVO board = null;
		try(
			SqlSession sqlSession = sessionFactory.openSession();
		){
			board = sqlSession.getMapper(IBoardDAO.class).selectBoard(bo_no);
		}
		return board;
	}

	@Override
	public int incrementHit(int bo_no) {
		int cnt = 0;
		
		try(
			SqlSession sqlSession = sessionFactory.openSession();
		){
			cnt = sqlSession.getMapper(IBoardDAO.class).incrementHit(bo_no);
			sqlSession.commit();
		}
		return cnt;
	}

	@Override
	public int incrementReport(int bo_no) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int incrementLikeOrHate(BoardVO board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateBoard(BoardVO board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBoard(int bo_no) {
		// TODO Auto-generated method stub
		return 0;
	}

}
