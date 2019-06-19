package kr.or.ddit.prod.dao;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import kr.or.ddit.vo.BuyerVO;

public class OthersDAOImplTest {
	IOthersDAO othersDAO = new OthersDAOImpl();

	@Test
	public void testSelectLprodList() {
		List<Map<String, String>> lprodList = othersDAO.selectLprodList();
		assertNotEquals(0, lprodList.size());
	}

	@Test
	public void testSelectBuyerList() {
		List<BuyerVO> buyerList = othersDAO.selectBuyerList(null);
		assertNotEquals(0, buyerList.size());
		buyerList = othersDAO.selectBuyerList("asfasdfas");
		assertEquals(0, buyerList.size());
	}

}









