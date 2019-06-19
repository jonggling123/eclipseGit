package board.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import board.vo.BoardVO;
import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.PagingVO;

public class BoardDAOImpl implements IBoardDAO {
	SqlSessionFactory sessionFactory = CustomSqlSessionFactoryBuilder.getSessionFactory();

	@Override
	public int insertBoard(BoardVO board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BoardVO selectBoard(int bo_no) {
		// TODO Auto-generated method stub
		return null;
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
	public long selectBoardCount() {
		long cnt = 0;
		try(
			SqlSession sqlSession = sessionFactory.openSession();
		){
			cnt = sqlSession.getMapper(IBoardDAO.class).selectBoardCount();
		}
		return cnt;
	}

	@Override
	public int incrementHit(int bo_no) {
		// TODO Auto-generated method stub
		return 0;
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
