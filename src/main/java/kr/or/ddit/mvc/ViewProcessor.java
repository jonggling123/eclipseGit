package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewProcessor implements IViewProcessor {
	//forwarding 할 때만 사용
	private String prefix = "";
	private String suffix = "";

	@Override
	public void setPrefix(String prefix) {
		this.prefix = prefix;

	}

	@Override
	public void setSuffix(String suffix) {
		this.suffix = suffix;

	}

	private static final String REDIRECTFLAG = "redirect:";
	@Override
	public void viewProcess(String viewName, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(viewName.startsWith(REDIRECTFLAG)) { //redirect
			String redirectURI = req.getContextPath() + viewName.substring(REDIRECTFLAG.length());
			resp.sendRedirect(redirectURI);
			
		}else { //forward
			String view = prefix+viewName+suffix;
			RequestDispatcher rd = req.getRequestDispatcher(view);
			rd.forward(req, resp);
		}

	}

}
