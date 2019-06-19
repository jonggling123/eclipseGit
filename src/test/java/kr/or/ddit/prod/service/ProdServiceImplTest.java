package kr.or.ddit.prod.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.CommonException;
import kr.or.ddit.vo.ProdVO;

public class ProdServiceImplTest {
	IProdService service = new ProdServiceImpl();
	ProdVO prod;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		prod = new ProdVO();
		prod.setProd_id("P101000001");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected=CommonException.class)
	public void testModifyProdNotExist() {
		prod.setProd_id("asdasdf");
		service.modifyProd(prod);
	}

	@Test(expected=Exception.class)
	public void testModifyProd() {
		ServiceResult result = service.modifyProd(prod);
		assertEquals(ServiceResult.OK, result);
	}
}
















