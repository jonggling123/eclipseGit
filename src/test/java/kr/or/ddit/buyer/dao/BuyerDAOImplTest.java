package kr.or.ddit.buyer.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public class BuyerDAOImplTest {
	IBuyerDAO buyerDAO = new BuyerDAOImpl();
	BuyerVO buyer;
	PagingVO<BuyerVO> pagingVO;
	
	@Before
	public void setUp() {
		buyer = new BuyerVO();
		buyer.setBuyer_id("P10109");
		buyer.setBuyer_name("test");
		buyer.setBuyer_lgu("P101");
		buyer.setBuyer_comtel("000-0000-0000");
		buyer.setBuyer_fax("111-1111");
		buyer.setBuyer_mail("jonggling123@nate.com");
		
		pagingVO = new PagingVO<BuyerVO>(10, 10);
	}
	
	@Test(expected=PersistenceException.class)
	public void testInsertBuyer() {
		int rowCnt = buyerDAO.insertBuyer(buyer);
	}
	
	@Test
	public void testSelectBuyer() {
		BuyerVO select = buyerDAO.selectBuyer("P10101");
		assertNotNull(select);
	}
	
	@Test
	public void testSelectBuyerCount() {
		long tr = buyerDAO.selectBuyerCount(pagingVO);
		assertEquals(15, tr);
	}
	
	@Test
	public void testSelectBuyerList() {
		pagingVO.setTotalRecord(15);
		pagingVO.setCurrentPage(1);
		List<BuyerVO> buyerList = buyerDAO.selectBuyerList(pagingVO);
		assertEquals(10, buyerList.size());
	}
	
	@Test
	public void testUpdateBuyer() {
		buyer.setLprod_nm("lee");
		int c = buyerDAO.updateBuyer(buyer);
		assertEquals(1, c);
	}

}
