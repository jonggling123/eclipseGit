package board.vo;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="bo_no")
public class BoardVO {
	private int bo_no;
	private String bo_title;
	private String bo_writer;
	private String bo_date;
	private String bo_content;
	private String code_id;
	private int bo_hit;
	private int bo_report;
	private int bo_parent;
	private int bo_like;
	private int bo_hate;
	private String bo_ip;
	private String bo_mail;
	private String bo_pass;
	
	private boolean likeFlag; //true : 좋아요
	
	private List<PdsVO> pdsList;
	private List<ReplyVO> replyList;
	
	private String code_name;
}
