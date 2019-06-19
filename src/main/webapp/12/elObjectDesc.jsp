<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="kr.or.ddit.utils.CookieUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>0401/elObjectDesc.jsp</title>
</head>
<body>
<H4>EL 기본 객체</H4>
<PRE>
	1. Scope 객체 : pageScope, requestScope, sessionScope, applicationScope
		${pageScope.pageAttr }, ${pageScope["pageAttr"] }
	
	2. request parameter 객체 : param(Map&lt;String, String&gt;), paramValues(Map&lt;String, String[]&gt;)
		<%=request.getParameter("paramName") %>, ${param.paramName }, ${param["paramName"] }
		<%=request.getParameterValues("paramName") %>, ${paramValues.paramName[0] }, ${paramValues["paramName"][0] }
		=>원본 파라미터 맵이 아님(복사본)
	
	3. request header 객체 : header(Map&lt;String, String&gt;), headerValues(Map&lt;String, String[]&gt;)
		<%=request.getHeader("Accept") %>, ${header.accept }, ${header["accept"] }
		<%=request.getHeader("user-agent") %>, ${header.user-agent }, ${header["user-agent"] }
		<%=request.getHeaders("cookie") %>, ${headerValues.cookie[0] }, ${headerValues["cookie"][0] }
	
	4. cookie 객체 : cookie(Map&lt;String, Cookie&gt;)
		<%=new CookieUtil(request).getCookieValue("JSESSIONID") %>
		${cookie.JSESSIONID.getValue() }, ${cookie.JSESSIONID.value }
		${cookie["JSESSIONID"]["value"] }, ${cookie["JSESSIONID"]["name"] }
	
	5. 컨텍스트 파라미터 객체 : initParam(Map&lt;String, String&gt;)
		<%=application.getInitParameter("copyright") %>
		${initParam.copyright }, ${initParam["copyright"] }
		
	6. pageContext 객체
		<%=request %> ${pageContext.request.contextPath }, ${pageContext.request }
		${pageContext["request"] }
		<%=request.getContextPath() %>, ${pageContext.request.contextPath }, ${pageContext["request"]["contextPath"] }
	==> EL 의 모든 기본 객체는 Map
</PRE>
</body>
</html>