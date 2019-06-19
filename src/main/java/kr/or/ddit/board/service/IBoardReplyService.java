package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;

/**
 * 게시판에서 댓글과 관련된 로직을 담당
 *
 */
public interface IBoardReplyService {
	public ServiceResult createReply(ReplyVO reply);
	
	/**
	 * 댓글 목록 조회
	 * @param pagingVO
	 * @return common exception : list.size()==0
	 */
	public List<ReplyVO> retrieveReplyList(PagingVO<ReplyVO> pagingVO);
	
	/**
	 * 댓글 개수 조회
	 * @param pagingVO
	 * @return
	 */
	public long retrieveReplyCount(PagingVO<ReplyVO> pagingVO);
	
	/**
	 * 게시글번호, 댓글번호, 비밀번호 필요
	 * @param reply
	 * @return
	 */
	public ServiceResult removeReply(ReplyVO reply);
}
