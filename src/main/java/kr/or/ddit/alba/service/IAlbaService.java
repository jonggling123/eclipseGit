package kr.or.ddit.alba.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.AlbaVO;

public interface IAlbaService {
	public ServiceResult createAlba(AlbaVO alba);
	public AlbaVO retrieveAlba(String al_id);
	public List<AlbaVO> retrieveAlbaList();
	public ServiceResult modifyAlba(AlbaVO alba);
	public ServiceResult removeAlba(String al_id);
}
