package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(of="bo_no")
@NoArgsConstructor
public class BoardVO implements Serializable{
	private Integer bo_no;
	private String bo_title;
	private String bo_writer;
	private String bo_pass;
	private String bo_date;
	private String bo_content;
	private String code_id;
	private Integer bo_hit;
	private Integer bo_report;
	private Integer bo_like;
	private Integer bo_hate;
	private String bo_ip;
	private String bo_mail;
	private Integer bo_parent;
	
	//has 관계 형성
	private List<ReplyVO> replyList;
	private List<PdsVO> pdsList;
	
	//좋아요 싫어요 구분을 위한 변수
	private boolean likeFlag; //true : 좋아요
	
	//분류 코드명을 담기 위한 변수
	private String code_name;
}
