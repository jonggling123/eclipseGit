package board.service;

import java.util.List;

import board.vo.BoardVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.PagingVO;

/**
 * 게시판의 CRUD를 위한 service의 추상화
 *
 */
public interface IBoardService {
	/**
	 * 신규 등록
	 * @param board
	 * @return OK, FAILED
	 */
	public ServiceResult createBoard(BoardVO board);
	
	/**
	 * 게시글 상세 조회
	 * @param bo_no
	 * @return 존재하지 않으면 common Exception
	 */
	public BoardVO retrieveBoard(int bo_no);
	
	/**
	 * 게시글 목록 조회
	 * @param pagingVO
	 * @return 존재하지 않으면 common exception
	 */
	public List<BoardVO> retrieveBoardList(PagingVO<BoardVO> pagingVO);
	
	/**
	 * 게시글 개수
	 * @return
	 */
	public long retrieveBoardCount();
	
	/**
	 * 신고
	 * @param bo_no
	 * @return
	 */
	public ServiceResult singo(int bo_no);
	
	/**
	 * 좋아요, 싫어요
	 * @param board
	 * @return
	 */
	public ServiceResult likeOrHate(BoardVO board);
	
	/**
	 * 게시글 수정
	 * 첨부파일 삭제 메서드도 작용한다
	 * @param board
	 * @return NOTEXIST, INVALIDPASSWORE, OK, FAILED
	 */
	public ServiceResult modifyBoard(BoardVO board);
	
	/**
	 * 게시글 삭제
	 * @param board
	 * @return OK, FAILED, INVALIDPASSWORD, NOTEXIST
	 */
	public ServiceResult removeBoard(BoardVO board);
	
	/**
	 * 첨부파일 삭제
	 * @param pds_no
	 * @return
	 */
	public ServiceResult downloadPds(int pds_no);
}
