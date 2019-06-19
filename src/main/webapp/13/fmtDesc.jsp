<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>0402 fmtDesc.jsp</title>
</head>
<body>
<H4>fmt(국제화 태그, format 태그)</H4>
<form>
	<INPUT type="radio" name="language" value="ko_KR"
		onchange="document.forms[0].submit();"
		${"ko_KR" eq param.language ? "checked" : "" }
	/>한국어_한국
	<INPUT type="radio" name="language" value="en_US"
		onchange="document.forms[0].submit();"
		${"en_US" eq param.language ? "checked" : "" }
	/>영어_미국
</form>
<c:set var="language" value="${param.language }" />
<c:set var="client" value="${not empty language ? language : pageContext.request.locale }" />

<PRE>
	: 국제화 서비스 처리를 위한 태그(i18n(국제화), l10n(지역화) 지원)
	1) locale 에 따른 언어 처리(setLocale -> bundle -> message)
		- 언어의 종류 결정 : 영어, 한글
		- 언어별로 message bundle 작성
			basename : kr.or.ddit.msgs.message
			locale : en, ko (파일명에는 포함되지만 basename에 포함되지 않는다)
		- 서비스할 언어 결정 : setLocale	
			<fmt:setLocale value="${client}" />
		- 해당 언어의 message bundle 로딩 : bundle
			<fmt:bundle basename="kr.or.ddit.msgs.message">
		- 메세지 출력 : message
				<fmt:message key="bow"/>
			</fmt:bundle>
	2) locale 에 따른 메세지 형식 처리
		- parse : 문자열을 일정 형식에 따라 특정 데이터 타입으로 변환하는 과정
		- format : 특정 타입의 데이터를 일정 형식에 따라 문자열로 만들어내는 과정
		<fmt:parseNumber value="100,000" pattern="###,###" var="genNum" />
		${genNum * 3 }
		<fmt:formatNumber value="${genNum * 3 }" pattern="###,###" var="genStr" />
		${genStr }
		
		<fmt:formatNumber value="${genNum * 3 }" type="currency" var="genCurrency"/>
		${genCurrency }
		
		<fmt:formatDate value="<%=new Date() %>" type="both" 
			dateStyle="long" timeStyle="long" var="genDateStr"
		/>
		${genDateStr }
		<fmt:parseDate value="${genDateStr }" var="genDate"
			type="both" dateStyle="long" timeStyle="long"
		/>
		${genDate.time }
</PRE>
<%
	Locale[] locales = Locale.getAvailableLocales();
%>
1000000
1) locale 선택 ui
2) 1000000 데이터를 선택한 locale에 맞게 통화로 출력

<c:set var="locales" value="<%=locales %>" />
<c:set var="money" value="${param.money }"/>
<FORM>
	<SELECT name="choose">
		<c:forEach items="${locales }" var="locale">
			<c:if test="${not empty locale.language and not empty locale.country }">
				<c:set var="currency" value="${locale.language }_${locale.country }"/>
				<OPTION value="${currency }" 
					${currency eq param.choose ? "selected" : ""}
				>${locale.displayCountry }</OPTION>
			</c:if>
		</c:forEach>
	</SELECT>
	<INPUT type="number" name="money" value="${not empty money ? money : '' }" />
	<INPUT type="submit" value="전송"/>
</FORM>
<fmt:setLocale value="${param.choose}" />
<fmt:formatNumber value="${money }" type="currency"/>
<br>
<fmt:formatDate value="<%=new Date() %>" dateStyle="long" timeStyle="long" />
</body>
</html>