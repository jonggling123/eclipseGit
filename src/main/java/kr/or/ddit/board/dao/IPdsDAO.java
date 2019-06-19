package kr.or.ddit.board.dao;

import kr.or.ddit.vo.PdsVO;

public interface IPdsDAO {
	public int insertPds(PdsVO pds);
	
	public PdsVO selectPds(int pds_no);
	
	//첨부파일은 수정이 아닌 삭제 후 새로이 넣는 것
	public int deletePds(int pds_no);
}
