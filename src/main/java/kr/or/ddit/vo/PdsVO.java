package kr.or.ddit.vo;

import java.io.Serializable;

import org.apache.commons.fileupload.FileItem;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="pds_no")
public class PdsVO implements Serializable{
	private Integer pds_no;
	private Integer bo_no;
	private String pds_filename;
	private String pds_savepath;
	private String pds_mime;
	private Integer pds_size;
	private String pds_fancysize;
	
	//실제 파일을 담을 변수
	private FileItem fileItem;
}
