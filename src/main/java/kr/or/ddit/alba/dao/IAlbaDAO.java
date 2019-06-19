package kr.or.ddit.alba.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.vo.AlbaVO;

public interface IAlbaDAO {

	public int insertAlba(AlbaVO albaVO);

	public AlbaVO selectAlba(String al_id);

	public List<AlbaVO> selectAlbaList();

	public int updateAlba(AlbaVO albaVO, SqlSession sqlSession);

	public int deleteAlba(String al_id, SqlSession sqlSession);

	public int insertLicenses(AlbaVO albaVO, SqlSession sqlSession);
	
	public int deleteLicenses(AlbaVO albaVO, SqlSession sqlSession);
}