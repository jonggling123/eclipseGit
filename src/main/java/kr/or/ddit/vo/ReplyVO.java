package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * REPLYBOARD 테이블 대상
 *
 */
@Data
@EqualsAndHashCode(of="rep_no")
public class ReplyVO implements Serializable{
	private Integer rep_no;
	private Integer bo_no;
	private String rep_writer;
	private String rep_pass;
	private String rep_ip;
	private String rep_content;
	private String rep_date;
}
