package kr.or.ddit.alba.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.AlbaVO;

public class AlbaDAOImpl implements IAlbaDAO {
	SqlSessionFactory sessionFactory = 
			CustomSqlSessionFactoryBuilder.getSessionFactory();
	
	@Override
	public int insertAlba(AlbaVO albaVO) {
		try(
				SqlSession sqlSession = sessionFactory.openSession();
			){
			int rowCnt = sqlSession.getMapper(IAlbaDAO.class).insertAlba(albaVO);
			sqlSession.commit();
			return rowCnt;
		}	
	}

	@Override
	public AlbaVO selectAlba(String albaId) {
		try(
			SqlSession sqlSession = sessionFactory.openSession();
		){
			return sqlSession.getMapper(IAlbaDAO.class).selectAlba(albaId);
		}
	}

	@Override
	public List<AlbaVO> selectAlbaList() {
		try(
			SqlSession session = sessionFactory.openSession();
		){
			return session.getMapper(IAlbaDAO.class).selectAlbaList();
		}
	}

	@Override
	public int updateAlba(AlbaVO albaVO, SqlSession sqlSession) {
		int rowCnt = sqlSession.update("kr.or.ddit.alba.dao.IAlbaDAO.updateAlba", albaVO);
		return rowCnt;
	}

	@Override
	public int insertLicenses(AlbaVO albaVO, SqlSession sqlSession) {
		int rowCnt = sqlSession.insert("kr.or.ddit.alba.dao.IAlbaDAO.insertLicenses", albaVO);
		return rowCnt;
	}
	
	@Override
	public int deleteLicenses(AlbaVO albaVO, SqlSession sqlSession) {
		int rowCnt = sqlSession.delete("kr.or.ddit.alba.dao.IAlbaDAO.deleteLicenses", albaVO);
		return rowCnt;
	}
	
	@Override
	public int deleteAlba(String albaId, SqlSession sqlSession) {
		int rowCnt = sqlSession.delete("kr.or.ddit.alba.dao.IAlbaDAO.deleteAlba", albaId);
		return rowCnt;
	}
	

}
