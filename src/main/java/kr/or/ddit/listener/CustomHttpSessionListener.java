package kr.or.ddit.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import kr.or.ddit.Constants;

//@WebListener
public class CustomHttpSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		Integer userCount = (Integer) se.getSession().getServletContext().getAttribute(Constants.USERCOUNTATTRNAME);
		se.getSession().getServletContext().setAttribute(Constants.SECUREDRESOURCENAME, userCount+1);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		Integer userCount = (Integer) se.getSession()
				.getServletContext().getAttribute(Constants.USERCOUNTATTRNAME);
		se.getSession().getServletContext()
		.setAttribute(Constants.SECUREDRESOURCENAME, userCount-1);
		
	}

}
