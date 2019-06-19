<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.ddit.or.kr/genDate" prefix="gd" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.ddit.or.kr/loopPrintString" prefix="lp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>0402 customDesc.jsp</title>
</head>
<body>
<h4>커스텀 라이브러리 작성</h4>
<PRE>
	EL 커스텀 함수 정의
	1. 자바 클래스를 정의하고, 실제 함수의 실행 코드를 가진 static 메서드 정의
		ex) SimpleDateFunctions.java -> generateDate
	2. tld(Tag Library Definition) 파일 작성
		1) /WEB-INF/tlds 폴더 생성
			다른 위치에 tld 파일 생성시, web.xml 에 등록이 필요함.
		2) *.tld 파일 형태로 파일 생성(xml)
			ex) simpleDateFunctions.tld
		3) uri, short-name, function -> name/function-class/function-signiture
		
	커스텀 태그 정의
	<c:set var="pattern" value="yyyy-MM-dd"/>
	<gd:genDate pattern="${pattern }"/>
	<lp:printString count="5">
		텍스트
	</lp:printString>
</PRE>
</body>
</html>