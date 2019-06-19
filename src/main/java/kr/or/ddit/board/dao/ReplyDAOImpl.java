package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;

public class ReplyDAOImpl implements IReplyDAO {
	SqlSessionFactory sessionFactory = CustomSqlSessionFactoryBuilder.getSessionFactory();

	@Override
	public int insertReply(ReplyVO reply) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ReplyVO> selectReplyList(PagingVO<ReplyVO> pagingVO) {
		List<ReplyVO> replyList = null;
		
		try(
			SqlSession sqlSession = sessionFactory.openSession();
		){
			replyList = sqlSession.getMapper(IReplyDAO.class).selectReplyList(pagingVO);
		}
		return replyList;
	}

	@Override
	public long selectReplyCount(PagingVO<ReplyVO> pagingVO) {
		long totalRecord = 0;
		
		try(
				SqlSession sqlSession = sessionFactory.openSession();
			){
				totalRecord = sqlSession.getMapper(IReplyDAO.class).selectReplyCount(pagingVO);
			}
		return totalRecord;
	}

	@Override
	public int deleteReply(int rep_no) {
		// TODO Auto-generated method stub
		return 0;
	}

}
