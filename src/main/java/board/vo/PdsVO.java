package board.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="pds_no")
public class PdsVO {
	private int pds_no;
	private int bo_no;
	private String pds_filename;
	private String pds_savepath;
	private String pds_mime;
	private int pds_size;
	private String pds_fancysize;
}
