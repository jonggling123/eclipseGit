package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public class ProdDAOImpl implements IProdDAO {
	private static ProdDAOImpl prodDAO;
	private ProdDAOImpl(){}
	public static ProdDAOImpl getInstance(){
		if(prodDAO==null) prodDAO = new ProdDAOImpl();
		return prodDAO;
	}
	
	SqlSessionFactory sessionFactory = 
			CustomSqlSessionFactoryBuilder.getSessionFactory();

	@Override
	public int insertProd(ProdVO prod) {
		try(
				SqlSession sqlSession = sessionFactory.openSession();
		){		
			int rowCnt = sqlSession.getMapper(IProdDAO.class).insertProd(prod);
			sqlSession.commit();
			return rowCnt;
		}
	}

	@Override
	public long selectProdCount(PagingVO pagingVO) {
		try(
			SqlSession sqlSession = sessionFactory.openSession();
			){		
			return sqlSession.getMapper(IProdDAO.class).selectProdCount(pagingVO);
		}
	}
	
	@Override
	public List<ProdVO> selectProdList(PagingVO pagingVO) {
		try(
			SqlSession sqlSession = sessionFactory.openSession();
			){		
			return sqlSession.getMapper(IProdDAO.class).selectProdList(pagingVO);
		}
	}

	@Override
	public ProdVO selectProd(String prod_id) {
		try(
			SqlSession sqlSession = sessionFactory.openSession();
			){		
			return sqlSession.getMapper(IProdDAO.class).selectProd(prod_id);
		}
	}

	@Override
	public int updateProd(ProdVO prod) {
		try(
			SqlSession sqlSession = sessionFactory.openSession();
		){		
			int rowCnt = sqlSession.getMapper(IProdDAO.class).updateProd(prod);
			sqlSession.commit();
			return rowCnt;
		}
	}

}









