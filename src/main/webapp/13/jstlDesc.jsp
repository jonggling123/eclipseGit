<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>0401jstlDesc.jsp</title>
</head>
<body>
<H4>JSTL(Jsp Standart Tag Library)</H4>
<PRE>
	: JSR-52 스펙에 따라 지원되는 커스텀 태그 라이브러리.
	
	사용 단계
	1. jar 파일 빌드패스에 추가
	2. taglib 지시자를 이용해 커스텀 태그 로딩
		커스텀 태그 종류
		1) core 태그(c 태그)
			- 속성 지원 : set, remove
			&lt;c:set var="속성명", value="값|표현식|EL", scope="영역의 종류" /&gt;
			&lt;c:remove var="삭제하려는 속성명", scope="속성을 제거할 대상 영역" /&gt;
			=> 영역을 특정하지 않으면 이름이 동일한 모든 속성을 삭제
			<c:set var="now" value="<%=new Date() %>" />
			${now }
			<c:set var="copyNow" value="${now }"/>
			${copyNow }
			
			<c:set var="testAttr" value="세션속성값" scope="session" />
			<c:set var="testAttr" value="요청속성값" scope="request" />
			${requestScope.testAttr }, ${sessionScope.testAttr }
			<c:remove var="testAttr" scope="session" />
			${requestScope.testAttr }, ${sessionScope.testAttr }
			
			- ****흐름 제어 jstlFlow.jsp 참고
				조건문 : if
				다중조건문 : choose when otherwise
				반복문 : forEach, forTokens
			- URL 재작성(URL Rewriting) : url, redirect
			
			- 기타 : import, out
<%-- 			<c:import url="https://www.naver.com" var="naver" scope="application"/> --%>
<%--  			<jsp:include page="https://www.naver.com" /> --%>
				: 동적으로 다른 사이트를 가져오는 태그(include와 유사)
				: include는 context가 가지고 있는 것만 가져올 수 있다
<%-- 			<c:out value="${naver }" escapeXml="false"/> --%>
				: value의 모든 태그를 escape 시킴
		2) ftm 태그 (fmtDesc.jsp 참고)
			: locale(언어, 지역) 처리 지원
		3) ftn 라이브러리
			: servlet스펙 3.0전을 지원하는 태그(메서드 사용)
			${fn:containsIgnoreCase("ABC","aBc") }
			<%
				String[] array = new String[]{"a","b","c"};
				pageContext.setAttribute("array", array);
			%>
			${array }, ${fn:join(array, ",") }, 
			<c:set var="genStr" value="${fn:join(array, ',') }" />
			${fn:split(genStr, ",") }
			${fn:escapeXml("<h4>타이틀</h4>") }
</PRE>
<FORM>
	import url : <INPUT type="url" name="importUrl" value="${param.importUrl }"/>
	<INPUT type="checkbox" name="toSource" value="true" 
		${not empty param.toSource ? "checked" : "" }	
	/>
	<INPUT type="submit" value="IMPORT" />
</FORM>

<c:if test="${not empty param['importUrl'] }">
	<DIV>
		<c:import url="${param['importUrl'] }" var="url"/>
		<c:out value="${url }" escapeXml="${not empty param['toSource'] and param['toSource'] ? true : false}"/>
	</DIV>
</c:if>
</body>
</html>