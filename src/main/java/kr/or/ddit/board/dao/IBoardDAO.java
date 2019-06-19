package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public interface IBoardDAO {
	/**
	 * 게시글 삽입
	 * @param board
	 * @return row count >0 : success
	 */
	public int insertBoard(BoardVO board);
	
	/**
	 * 게시글 목록 검색
	 * @param pagingVO
	 * @return list.size()==0 : not exist
	 */
	public List<BoardVO> selectBoardList(PagingVO<BoardVO> pagingVO);
	
	/**
	 * 게시글 개수 조회
	 * @param pagingVO
	 * @return
	 */
	public long selectBoardCount(PagingVO<BoardVO> pagingVO);
	
	/**
	 * 게시글 검색
	 * @param bo_no
	 * @return null : not exist
	 */
	public BoardVO selectBoard(int bo_no);
	
	/**
	 * 조회수 증가
	 * updateBoard 이용
	 * @param bo_no
	 * @return row count >0 : success
	 */
	public int incrementHit(int bo_no);
	
	/**
	 * 신고회수 증가
	 * updateBoard 이용
	 * @param bo_no
	 * @return row count >0 : success
	 */
	public int incrementReport(int bo_no);
	
	/**
	 * 좋아요/싫어요 증가
	 * updateBoard 이용
	 * @param board
	 * @return row count >0 : success
	 */
	public int incrementLikeOrHate(BoardVO board);
	
	/**
	 * 게시글 수정
	 * @param board
	 * @return row count >0 : success
	 */
	public int updateBoard(BoardVO board);
	
	/**
	 * 게시글 삭제
	 * @param bo_no
	 * @return row count >0 : success
	 */
	public int deleteBoard(int bo_no);
}
