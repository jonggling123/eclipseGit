package kr.or.ddit.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class TimeZoneOptions extends BodyTagSupport {
	private String[] zoneArr;
	private int index = 1;
	
	public void setZoneArr(String[] zoneArr) {
		this.zoneArr = zoneArr;
	}
	
	@Override
	public int doStartTag() throws JspException {
		return EVAL_BODY_INCLUDE;
	}
	
	@Override
	public int doAfterBody() throws JspException {
		if(++index<=zoneArr.length) {
			return EVAL_BODY_AGAIN;
		}else {
			return SKIP_BODY;
		}
	}
	
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}
