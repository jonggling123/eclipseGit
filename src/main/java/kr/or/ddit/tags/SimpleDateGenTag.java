package kr.or.ddit.tags;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class SimpleDateGenTag extends SimpleTagSupport{
	private String pattern;
	
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
	@Override
	public void doTag() throws JspException, IOException {
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		getJspContext().getOut().print(sdf.format(today));
	}
	
}
