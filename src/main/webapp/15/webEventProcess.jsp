<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>
<H4>웹 어플리케이션의 이벤트 처리</H4>
<PRE>
	1. 타겟 : 웹 컨텍스트
	
	2. 이벤트 종류 : lifecycle, attribute
		request : 생성/소멸(객체), add/remove/replace(속성)
		session : 생성/소멸(객체), add/remove/replace(속성)
		ServletContext : 생성/소멸(객체), add/remove/replace(속성)
	3. 핸들러 구현(Listener 구현)
	4. 타겟에 이벤트 핸들러 바인드 : web.xml
</PRE>
</body>
</html>