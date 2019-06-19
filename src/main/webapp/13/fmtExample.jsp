<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.util.TimeZone"%>
<%@page import="java.util.Date"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.Locale"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.ddit.or.kr/simpleDateFunctions" prefix="sdf" %>
<%@ taglib uri="locales" prefix="lcs"%>
<%@ taglib uri="timeZoneFunctions" prefix="tzf" %>
<%@ taglib uri="timeZoneOptions" prefix="tzst" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>0402 fmtExample</title>
</head>
<body>
	<c:if test="${not empty param.locale }">
		<c:set var="paramLocale" value="${lcs:forLanguageTag(param.locale) }"/>
	</c:if>
	<c:set value="${empty paramLocale ? pageContext.request.locale : paramLocale }" var="currentLocale" />
	<FORM>
		금액 : <INPUT type="number" name="digits" value="${param.digits }"/>
		<SELECT name="locale">
			<c:forEach items="${lcs:getAvailableLocales() }" var="loc">
				<c:if test="${not empty loc.country }">
					<option value="${loc.toLanguageTag() }" 
						${loc eq currentLocale ? "selected" : "" }
					>${loc.displayCountry }</option>
				</c:if>
			</c:forEach>
		</SELECT>
		<fmt:setLocale value="${currentLocale }" />
		<pre>
			통화 : <fmt:formatNumber value="${param.digits }" type="currency"/>
			
			<c:set var="zoneIdArr" value="${tzf:getTimeZoneIds() }"/>
			TimeZone 정보 : 
			
			<SELECT name="timeZone">
				<c:forEach items="${zoneIdArr }" var="tzi">
					<c:set var="tz" value="${tzf:getTimeZone(tzi) }"/>
					<OPTION value="${tzi }"
						${tzi eq param.timeZone ? "selected" : "" }
					>${tz.displayName }</OPTION> 
				</c:forEach>
			</SELECT>
			TimeZone에 해당하는 현재 시각 : 
			<fmt:formatDate value="${sdf:generateDate() }" type="both" 
				timeZone="${tzf:getTimeZone(param.timeZone) }"
			/>
		</pre>
		<INPUT type="submit" value="전송"/>
	</FORM>
	
</body>
</html>