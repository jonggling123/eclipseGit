package kr.or.ddit.alba.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.alba.dao.AlbaDAOImpl;
import kr.or.ddit.alba.dao.IAlbaDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.AlbaVO;

public class AlbaServiceImpl implements IAlbaService {
	IAlbaDAO albaDAO = new AlbaDAOImpl();
	SqlSessionFactory sessionFactory = CustomSqlSessionFactoryBuilder.getSessionFactory();
	@Override
	public ServiceResult createAlba(AlbaVO albaVO) {
		try(
			SqlSession sqlSession = sessionFactory.openSession();
		){
			int rowCnt = albaDAO.insertAlba(albaVO);
			ServiceResult result = ServiceResult.FAILED;
			if(rowCnt > 0){
				result = ServiceResult.OK;
				sqlSession.commit();
			}
			return result;
		}
	}

	@Override
	public AlbaVO retrieveAlba(String al_id) {
		AlbaVO albaVO = albaDAO.selectAlba(al_id);
		if(albaVO==null)
			throw new RuntimeException(al_id+" 알바생이 없음.");
		return albaVO;
	}

	@Override
	public List<AlbaVO> retrieveAlbaList() {
		return albaDAO.selectAlbaList();
	}

	@Override
	public ServiceResult modifyAlba(AlbaVO albaVO) {
		try(
				SqlSession sqlSession = sessionFactory.openSession();
		){
			int rowCnt = albaDAO.updateAlba(albaVO, sqlSession);
			ServiceResult result = ServiceResult.FAILED;
			if(rowCnt > 0){
				//******** 기존 라이센스 제거 후 신규 라이센스 추가
				rowCnt += albaDAO.deleteLicenses(albaVO, sqlSession);
				rowCnt += albaDAO.insertLicenses(albaVO, sqlSession);
				result = ServiceResult.OK;
				sqlSession.commit();
			}
			return result;
		}
	}

	@Override
	public ServiceResult removeAlba(String al_id) {
		try(
				SqlSession sqlSession = sessionFactory.openSession();
		){
		int rowCnt = albaDAO.deleteAlba(al_id, sqlSession);
		ServiceResult result = ServiceResult.FAILED;
		if(rowCnt > 0){
			//******** 기존 라이센스 제거 후 신규 라이센스 추가
			AlbaVO albaVO = new AlbaVO();
			albaVO.setAl_id(al_id);
			rowCnt += albaDAO.deleteLicenses(albaVO, sqlSession);
			result = ServiceResult.OK;
			sqlSession.commit();
		}
		return result;
	}
	}

}
