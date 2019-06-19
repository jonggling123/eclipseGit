package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

/**
 * 거래처 관리를 위한 business logic layer의 추상화
 * persistence layer을 사용해 DB에서 raw data를 가져와서 가공한다
 *
 */
public interface IBuyerService {
	/**
	 * 신규 등록
	 * @param buyer 
	 * @return OK, FAILED, PKDUPLICATED
	 */
	public ServiceResult createBuyer(BuyerVO buyer);
	
	/**
	 * 거래처 상세 조회
	 * @param buyer_id
	 * @return 존재하지 않는 경우 CommonException 발생
	 */
	public BuyerVO retrieveBuyer(String buyer_id);
	
	/**
	 * 거래처 목록 조회
	 * @param pagingVO
	 * @return 존재하지 않는 경우 CommonException 발생
	 */
	public List<BuyerVO> retrieveBuyerList(PagingVO<BuyerVO> pagingVO);
	
	/**
	 * totalRecord 조회
	 * @param pagingVO
	 * @return
	 */
	public long retrieveBuyerCount(PagingVO<BuyerVO> pagingVO);
	
	/**
	 * 거래처 정보 수정
	 * @param buyer
	 * @return OK, FAILED
	 */
	public ServiceResult modifyBuyer(BuyerVO buyer);
}
