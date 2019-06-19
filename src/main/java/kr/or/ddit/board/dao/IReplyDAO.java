package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;

public interface IReplyDAO {
	public int insertReply(ReplyVO reply);
	
	/**
	 * 댓글 목록 조회
	 * @param pagingVO
	 * @return list.size()==0 : not exist
	 */
	public List<ReplyVO> selectReplyList(PagingVO<ReplyVO> pagingVO);
	
	/**
	 * 댓글 개수 조회
	 * @param pagingVO
	 * @return
	 */
	public long selectReplyCount(PagingVO<ReplyVO> pagingVO);
	
	/**
	 * 
	 * @param rep_no
	 * @return
	 */
	public int deleteReply(int rep_no);
}
