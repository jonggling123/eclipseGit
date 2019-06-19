package board.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="rep_no")
public class ReplyVO {
	private int rep_no;
	private int bo_no;
	private String rep_writer;
	private String rep_ip;
	private String rep_content;
	private String rep_date;
	private String rep_pass;
}
