package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.buyer.dao.BuyerDAOImpl;
import kr.or.ddit.buyer.dao.IBuyerDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.CommonException;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public class BuyerServiceImpl implements IBuyerService {
	IBuyerDAO buyerDAO = new BuyerDAOImpl();

	@Override
	public ServiceResult createBuyer(BuyerVO buyer) {
		ServiceResult result = null;
		boolean flag = false;
		
		try {
			BuyerVO selectedBuyer = retrieveBuyer(buyer.getBuyer_id());
		} catch (CommonException e) {
			flag = true;
		}
		
		if(flag) {
			int rowCnt = buyerDAO.insertBuyer(buyer);
			
			if(rowCnt>0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		}else {
			result = ServiceResult.PKDUPLICATED;
		}
		
		return result;
	}

	@Override
	public BuyerVO retrieveBuyer(String buyer_id) {
		BuyerVO buyer = null;
		buyer = buyerDAO.selectBuyer(buyer_id);
		
		if(buyer==null) {
			throw new CommonException(buyer_id+"에 해당하는 거래처가 없습니다");
		}
		return buyer;
	}

	@Override
	public List<BuyerVO> retrieveBuyerList(PagingVO<BuyerVO> pagingVO) {
		List<BuyerVO> buyerList = null;
		buyerList = buyerDAO.selectBuyerList(pagingVO);
		
		if(buyerList.size()==0) {
			throw new CommonException("거래처 정보가 존재하지 않습니다");
		}
		return buyerList;
	}

	@Override
	public long retrieveBuyerCount(PagingVO<BuyerVO> pagingVO) {
		long totalRecord = 0;
		totalRecord = buyerDAO.selectBuyerCount(pagingVO);
		
		return totalRecord;
	}

	@Override
	public ServiceResult modifyBuyer(BuyerVO buyer) {
		ServiceResult result = null;
		int rowCnt = buyerDAO.updateBuyer(buyer);
		
		if(rowCnt>0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

}
