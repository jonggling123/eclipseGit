package kr.or.ddit.prod.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BuyerVO;

public class OthersDAOImpl implements IOthersDAO {
	
	SqlSessionFactory sessionFactory = 
			CustomSqlSessionFactoryBuilder.getSessionFactory();

	@Override
	public List<Map<String, String>> selectLprodList() {
		try(
				SqlSession sqlSession = sessionFactory.openSession();
		){		
			return sqlSession.getMapper(IOthersDAO.class).selectLprodList();
		}
	}

	@Override
	public List<BuyerVO> selectBuyerList(String buyer_lgu) {
		try(
				SqlSession sqlSession = sessionFactory.openSession();
		){		
			return sqlSession.getMapper(IOthersDAO.class).selectBuyerList(buyer_lgu);
		}
	}

}












