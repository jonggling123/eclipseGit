package board.dao;

import java.util.List;

import board.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

/**
 * 게시판 CRUD를 위한 인터페이스
 *
 */
public interface IBoardDAO {
	/**
	 * 게시글 등록 메서드
	 * @param board
	 * @return row count >0 : 성공
	 */
	public int insertBoard(BoardVO board);
	
	/**
	 * 게시글 하나를 가져오는 메서드
	 * @param bo_no
	 * @return 존재하지 않으면 null 반환
	 */
	public BoardVO selectBoard(int bo_no);
	
	/**
	 * 게시글 리스트를 가져오는 메서드
	 * @param pagingVO
	 * @return 존재하지 않으면 list.size()==0
	 */
 	public List<BoardVO> selectBoardList(PagingVO<BoardVO> pagingVO);
	
 	/**
 	 * 게시글 총 개수를 가져오는 메서드
 	 * @return
 	 */
 	public long selectBoardCount();
 	
 	/**
 	 * 조회수 증가, updateBoard 사용
 	 * @param bo_no
 	 * @return row count >0 : 성공
 	 */
 	public int incrementHit(int bo_no);
 	
 	/**
 	 * 신고회수 증가, updateBoard 사용
 	 * @param bo_no
 	 * @return row count >0 : 성공
 	 */
 	public int incrementReport(int bo_no);
 	
 	/**
 	 * 좋아요, 싫어요 증가
 	 * updateBoard 사용
 	 * @param board
 	 * @return row count >0 : 성공
 	 */
 	public int incrementLikeOrHate(BoardVO board);
 	
 	
 	/**
 	 * 게시글 수정
 	 * @param board
 	 * @return row count >0 : 성공
 	 */
 	public int updateBoard(BoardVO board);
	
 	/**
 	 * 게시글 삭제
 	 * @param bo_no
 	 * @return row count >0 : 성공
 	 */
 	public int deleteBoard(int bo_no);
}
