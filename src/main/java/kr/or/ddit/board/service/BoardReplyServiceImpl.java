package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.IReplyDAO;
import kr.or.ddit.board.dao.ReplyDAOImpl;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.CommonException;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;

public class BoardReplyServiceImpl implements IBoardReplyService {
	IReplyDAO replyDAO = new ReplyDAOImpl();

	@Override
	public ServiceResult createReply(ReplyVO reply) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReplyVO> retrieveReplyList(PagingVO<ReplyVO> pagingVO) {
		List<ReplyVO> replyList = null;
		
		replyList = replyDAO.selectReplyList(pagingVO);
		if(replyList.size()==0) {
			throw new CommonException("댓글 목록이 존재하지 않습니다.");
		}
		return replyList;
	}

	@Override
	public long retrieveReplyCount(PagingVO<ReplyVO> pagingVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ServiceResult removeReply(ReplyVO reply) {
		// TODO Auto-generated method stub
		return null;
	}

}
