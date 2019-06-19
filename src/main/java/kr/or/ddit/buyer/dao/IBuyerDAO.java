package kr.or.ddit.buyer.dao;

import java.util.List;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

/**
 * 거래처 관리를 위한 persistence layer의 추상화
 * Buyer 테이블을 대상으로 CRUD Data Access Method 
 *
 */
public interface IBuyerDAO {
	/**
	 * 신규 거래처 등록
	 * @param buyer 신규 등록할 거래처의 정보를 가진 VO
	 * @return row count > 0 이면 성공 
	 */
	public int insertBuyer(BuyerVO buyer);
	
	/**
	 * 거래처 상세정보 조회
	 * @param buyer_id 조회할 거래처의 id
	 * @return 존재하지 않으면 null 반환
	 */
	public BuyerVO selectBuyer(String buyer_id);
	
	/**
	 * 거래처의 목록 조회
	 * @param pagingVO 조회한 리스트와 페이징 처리를 위한 데이터를 담은 VO
	 * @return 존재하지 않으면 list.size() = 0
	 */
	public List<BuyerVO> selectBuyerList(PagingVO<BuyerVO> pagingVO);
	
	/**
	 * 페이징 처리를 위한 totalRecord 조회
	 * @param pagingVO
	 * @return
	 */
	public long selectBuyerCount(PagingVO<BuyerVO> pagingVO);
	
	/**
	 * 거래처 정보 수정
	 * @param buyer PK를 비롯해 수정할 거래처에 대한 정보
	 * @return row count > 0 이면 성공
	 */
	public int updateBuyer(BuyerVO buyer);
	
}
