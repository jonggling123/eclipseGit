<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>0401 jspFlowControl.jsp</title>
<STYLE type="text/css">
	.yellow{
		background-color: yellow;
	}
	
	.green{
		background-color: green;
	}
	
	.crimson{
		background-color: crimson;
	}
</STYLE>
</head>
<body>
<H4>코어(c) 태그를 이용한 흐름 제어</H4>
<FORM>
	당신의 나이는 ? <INPUT type="number" name="age" value="${param.age }"/>
	<INPUT type="submit" value="주시오"/>
</FORM>
<DIV>
<!-- 	파라미터가 있다면, -->
<!-- 		10대 미만, "아직 이르다" -->
<!-- 		10대, "너네도 안돼" -->
<!-- 		20대, "서른되면 와라~" -->
<!-- 		30대, "이젠 봐도 된다, 뭘?????" -->
<!-- 		40대, "편안히 감상하세요. 뭘????" -->
<!-- 		그 이상, "심장주의!!!" -->
<!-- 	없다면, "나이를 입력하지 않았음." -->
	<c:set var="age" value="${param.age }" />
	<c:if test="${empty age }">
		<c:set var="msg" value="나이를 입력하지 않았음" />
	</c:if>
	<c:if test="${not empty age }">
<%-- 		<c:if test="${age lt 10}"> --%>
<%-- 			<c:set var="msg" value="아직 이르다" /> --%>
<%-- 		</c:if> --%>
<%-- 		<c:if test="${age ge 10 and age lt 20 }"> --%>
<%-- 			<c:set var="msg" value="너네도 안대" /> --%>
<%-- 		</c:if> --%>
<%-- 		<c:if test="${age ge 20 and age lt 30}"> --%>
<%-- 			<c:set var="msg" value="서른즈음에" /> --%>
<%-- 		</c:if> --%>
<%-- 		<c:if test="${age ge 30 and age lt 40 }"> --%>
<%-- 			<c:set var="msg" value="가능" /> --%>
<%-- 		</c:if> --%>
<%-- 		<c:if test="${age ge 40 and age lt 50}"> --%>
<%-- 			<c:set var="msg" value="편안히 감상하소" /> --%>
<%-- 		</c:if> --%>
<%-- 		<c:if test="${age ge 50 }"> --%>
<%-- 			<c:set var="msg" value="심쿵주의" /> --%>
<%-- 		</c:if> --%>
	<c:choose>
		<c:when test="${age lt 10 }">
			<c:set var="chooseMsg" value="가"/>
		</c:when>
		<c:when test="${age ge 10 and age lt 20}">
			<c:set var="chooseMsg" value="10"/>
		</c:when>
		<c:when test="${age ge 20 and age lt 30 }">
			<c:set var="chooseMsg" value="20"/>
		</c:when>
		<c:when test="${age ge 30 and age lt 40 }">
			<c:set var="chooseMsg" value="30"/>
		</c:when>
		<c:when test="${age ge 40 and age lt 50 }">
			<c:set var="chooseMsg" value="40"/>
		</c:when>
		<c:otherwise>
			<c:set var="chooseMsg" value="~"/>
		</c:otherwise>
	</c:choose>
	</c:if>
	${msg }
	${chooseMsg }
</DIV>
<PRE>
	1. 조건문 : &lt;c:if test="조건식(표현식|EL)" &gt;&lt;/c&gt;
	<c:if test="${not empty param.test }">
		파라미터가 있음 : ${param["test"] }
	</c:if>
	<c:if test="${empty param.test }">
		파라미터가 없음.
	</c:if>
	
	2. 다중조건문 : choose 
				 when test="조건식(표현식|EL)"
				 otherwise
	<c:choose>
		<c:when test="${not empty param.test }">
			파라미터가 있음.
		</c:when>
		<c:otherwise>
			파라미터가 없음.
		</c:otherwise>
	</c:choose>
	
	
	3. 반복문 : forEach, forTokens
		일반 for 문(선언절; 조건절; 증감절)
		var : 반복문 안에서 사용할 속성명
		begin : 초기값
		end : 종료값
		step : 증가치
		향상된 for 문(집합객체 요소에 대한 블럭 변수 선언 : 반복대상 집합 객체)
		items : 반복 대상이 되는 집합객체(표현식|EL)
		
		varStatus(LoopTagStatus 객체의 레퍼런스)
			- LoopTagStatus 객체의 프로퍼티 : 반복의 상태를 의미하는 데이터 캡슐화
				int begin, end, step
				int index(반복 수행시 변경되는 속성의 현재 값), count(반복 회수)
				boolean first(첫 번째 반복인 경우), last(마지막 반복인 경우) 
		<c:forEach var="num" begin="1" end="5">
			${num }
		</c:forEach>
		
		<%
			List list = Arrays.asList("a","b","c");
			pageContext.setAttribute("list", list);
		%>
		<c:forEach var="item" items="${list }" varStatus="vs">
			${item }${vs.last ? "" : "," }
		</c:forEach>
		<c:forTokens items="아버지 가방에 들어가신다" delims=" " var="token">
			${token }
		</c:forTokens>
		<c:forTokens items="1,2,3,4,5,6,7" delims="," var="number">
			${number * 2 }
		</c:forTokens>
		
		
		4. URI 재처리 태그 : 
			&lt;c:url var="속성명" value="서버사이드 경로"&gt;
				&lt;c:param name="파라미터 명" value="파라미터값";ㅓㅑ?
			&lt;/url&gt;, redirect
			- 클라이언트 사이드 경로를 자동으로 생성(contextPath를 붙여줌)
			- 클라이언트가 쿠키를 지원하지 않는다면 자동으로 세션 파라미터 생성
		<c:url value="/member/memberList.do" var="listURL">
			<c:param name="page" value="2" />
		</c:url>
		<a href="${listURL }">회원목록</a>
</PRE>

<TABLE>
	<c:forEach var="dan" begin="2" end="9" step="2" varStatus="vs">
		<c:choose>
			<c:when test="${vs.first}">
				<c:set var="clzValue" value="green" />
			</c:when>
			<c:when test="${vs.count eq 3 }">
				<c:set var="clzValue" value="yellow" />
			</c:when>
			<c:otherwise>
				<c:set var="clzValue" value="normal" />
			</c:otherwise>
		</c:choose>
		<tr class="${clzValue }">
			<th>${dan }단</th>
			<c:forEach var="num" begin="1" end="9" varStatus="vvs">
				<c:if test="${vvs.last }">
					<c:set var="clzValue" value="crimson"/>
				</c:if>
				<td class="${clzValue }">${dan}*${num}=${dan * num }</td>
				<c:remove var="clzValue" scope="page"/>
			</c:forEach>
		</tr>
	</c:forEach>
</TABLE>
</body>
</html>