package kr.or.ddit.buyer.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public class BuyerDAOImpl implements IBuyerDAO {
	SqlSessionFactory sessionFactory = 
			CustomSqlSessionFactoryBuilder.getSessionFactory();

	@Override
	public int insertBuyer(BuyerVO buyer) {
		int rowCnt = 0;
		try(
			SqlSession sqlSession = sessionFactory.openSession();
		){
			rowCnt = sqlSession.getMapper(IBuyerDAO.class).insertBuyer(buyer);
			sqlSession.commit();
		}
		
		return rowCnt;
	}

	@Override
	public BuyerVO selectBuyer(String buyer_id) {
		BuyerVO buyer = null;
		
		try(
			SqlSession sqlSession = sessionFactory.openSession();
		){
			buyer = sqlSession.getMapper(IBuyerDAO.class).selectBuyer(buyer_id);
		}
		return buyer;
	}

	@Override
	public List<BuyerVO> selectBuyerList(PagingVO<BuyerVO> pagingVO) {
		List<BuyerVO> buyerList = null;
		
		try(
			SqlSession sqlSession = sessionFactory.openSession();
		){
			buyerList = sqlSession.getMapper(IBuyerDAO.class).selectBuyerList(pagingVO);
		}
		return buyerList;
	}

	@Override
	public long selectBuyerCount(PagingVO<BuyerVO> pagingVO) {
		long totalRecord = 0;
		
		try(
			SqlSession sqlSession = sessionFactory.openSession();
		){
			totalRecord = sqlSession.getMapper(IBuyerDAO.class).selectBuyerCount(pagingVO);
		}
		return totalRecord;
	}

	@Override
	public int updateBuyer(BuyerVO buyer) {
		int rowCnt = 0;
		
		try(
			SqlSession sqlSession = sessionFactory.openSession();
		){
			rowCnt = sqlSession.getMapper(IBuyerDAO.class).updateBuyer(buyer);
			sqlSession.commit();
		}
		return rowCnt;
	}

}
