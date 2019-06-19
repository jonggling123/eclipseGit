package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.PdsVO;

public interface IBoardService {
	/**
	 * 첨부파일 작성 기능
	 * @param board
	 * @return OK, FAILED
	 */
	public ServiceResult createBoard(BoardVO board);
	
	/**
	 * 게시글 목록 조회
	 * @param pagingVO
	 * @return COMMON EXCEPTION(NOT EXIST)
	 */
	public List<BoardVO> retrieveBoardList(PagingVO<BoardVO> pagingVO);
	
	/**
	 * 게시글 개수 조회
	 * @param pagingVO
	 * @return
	 */
	public long retrieveBoardCount(PagingVO<BoardVO> pagingVO);
	
	/**
	 * 이 메서드 안에서 조회수 증가 처리
	 * @param bo_no
	 * @return COMMON EXCEPTION(NOT EXIST)
	 */
	public BoardVO retrieveBoard(int bo_no);
	
	/**
	 * 신고 처리
	 * @param bo_no
	 * @return
	 */
	public ServiceResult singo(int bo_no);
	
	/**
	 * 좋아요/싫어요 처리
	 * @param board
	 * @return
	 */
	public ServiceResult likeOrHate(BoardVO board);
	
	/**
	 * 첨부파일 삭제 기능 작용
	 * @param board
	 * @return COMMON EXCEPTION(NOT EXIST), INVALIDPASSWORD, FAILED, OK
	 */
	public ServiceResult modifyBoard(BoardVO board);
	
	/**
	 * 
	 * @param board
	 * @return COMMON EXCEPTION(NOT EXIST), INVALIDPASSWORD, FAILED, OK
	 */
	public ServiceResult removeBoard(BoardVO board);
	
	
	/**
	 * 첨부파일 다운로드용 메서드
	 * @param pds_no
	 * @return
	 */
	public PdsVO downloadPds(int pds_no);
}
