package kr.or.ddit.buyer.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.buyer.dao.BuyerDAOImpl;
import kr.or.ddit.buyer.dao.IBuyerDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.CommonException;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public class BuyerServiceImplTest {
	IBuyerService service = new BuyerServiceImpl();
	BuyerVO buyer;
	PagingVO<BuyerVO> pagingVO;

	@Before
	public void setUp() throws Exception {
		buyer = new BuyerVO();
		buyer.setBuyer_id("P10109");
		buyer.setBuyer_name("test");
		buyer.setBuyer_lgu("P101");
		buyer.setBuyer_comtel("000-0000-0000");
		buyer.setBuyer_fax("111-1111");
		buyer.setBuyer_mail("jonggling123@nate.com");
		
		pagingVO = new PagingVO<BuyerVO>();
	}
	
	@Test(expected=CommonException.class)
	public void testRetrieveBuyerNotExist() {
		service.retrieveBuyer("asdfadads");
	}
	
	@Test
	public void testRetrieveBuyer() {
		BuyerVO buyer = service.retrieveBuyer("P10101");
		assertEquals("P10101", buyer.getBuyer_id());
	}

	@Test
	public void testCreateBuyerPkDuplicated() {
		ServiceResult result = service.createBuyer(buyer);
		assertEquals(ServiceResult.PKDUPLICATED, result);
	}
	
	@Test
	public void testBuyerList() {
		pagingVO.setCurrentPage(1);
		pagingVO.setTotalRecord(service.retrieveBuyerCount(pagingVO));
		List<BuyerVO> buyerList = service.retrieveBuyerList(pagingVO);
		assertNotEquals(0, buyerList.size());
	}
	
	@Test
	public void testModifyBuyer() {
		buyer.setBuyer_fax("1111-1111");
		ServiceResult result = service.modifyBuyer(buyer);
		assertEquals(ServiceResult.OK, result);
	}

}
