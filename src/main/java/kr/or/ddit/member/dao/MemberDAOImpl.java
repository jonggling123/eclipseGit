package kr.or.ddit.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

public class MemberDAOImpl implements IMemberDAO {
	SqlSessionFactory sessionFactory = 
				CustomSqlSessionFactoryBuilder.getSessionFactory();
	@Override
	public int insertMember(MemberVO member) {
		try(
				SqlSession sqlSession = sessionFactory.openSession();
				){		
			int rowCnt = sqlSession.getMapper(IMemberDAO.class).insertMember(member);
			sqlSession.commit();
			return rowCnt;
		}
	}

	@Override
	public MemberVO selectMember(String mem_id) {
		try(
				SqlSession sqlSession = sessionFactory.openSession();
			){		
				MemberVO member = sqlSession.getMapper(IMemberDAO.class).selectMember(mem_id);
				return member;
			}
	}

	@Override
	public List<MemberVO> selectMemberList(PagingVO<MemberVO> pagingVO) {
		try(
			SqlSession sqlSession = sessionFactory.openSession();
		){		
			List<MemberVO> list = sqlSession.getMapper(IMemberDAO.class).selectMemberList(pagingVO); 
			return list;
		}
	}
	
	@Override
	public long selectMemberCount(PagingVO<MemberVO> pagingVO) {
		try(
				SqlSession sqlSession = sessionFactory.openSession();
			){		
				return sqlSession.getMapper(IMemberDAO.class).selectMemberCount(pagingVO); 
			}
	}

	@Override
	public int updateMember(MemberVO member) {
		try(
			SqlSession sqlSession = sessionFactory.openSession();
		){		
			IMemberDAO memberDAO = sqlSession.getMapper(IMemberDAO.class);
			int rowCnt = memberDAO.updateMember(member); 
			sqlSession.commit();
			return rowCnt;
		}
	}

	@Override
	public int deleteMember(String mem_id) {
		try(
				SqlSession sqlSession = sessionFactory.openSession();
			){	
			int rowCnt = sqlSession.getMapper(IMemberDAO.class).deleteMember(mem_id);
			sqlSession.commit();
			return rowCnt;
		}
	}

}












