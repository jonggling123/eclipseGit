package kr.or.ddit.tags;

import java.io.IOException;
import java.util.TimeZone;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class SimpleTimeZoneFunctions extends SimpleTagSupport {
	public static String[] getTimeZoneIds() {
		return TimeZone.getAvailableIDs();
	}
	
	public static TimeZone getTimeZone(String zoneId) {
		return TimeZone.getTimeZone(zoneId);
	}
}
