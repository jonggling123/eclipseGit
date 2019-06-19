<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>0401/elCollectionDesc.jsp</title>
</head>
<body>
<H4>EL 을 이용한 집합 객체의 요소 접근 방법</H4>
<PRE>
	<%
		String[] array  = new String[]{"arrayValue1", "arrayValue2"};
		pageContext.setAttribute("arrayAttr", array);
		
		List list = new ArrayList<>();
		list.add("listValue1");
		list.add("listValue2");
		pageContext.setAttribute("listAttr", list);
		
		Map map = new HashMap<>();
		map.put("key1", "mapValue1");
		map.put("key 2", "mapValue2");
		pageContext.setAttribute("mapAttr", map);
		
		Set set = new HashSet<>();
		set.add("setValue1");
		set.add("setValue2");
		pageContext.setAttribute("setAttr", set);
	%>
	<%=pageContext.getAttribute("array") %>, ${array } -> 자체적으로 null을 공백으로 처리
	<%=pageContext.getAttribute("arrayAttr") %>, ${arrayAttr }
	<%=((String[])pageContext.getAttribute("arrayAttr"))[0] %>, ${arrayAttr[0]} -> 객체를 타입을 신경쓰지 않고 원형 그대로 사용 가능
	<%--=((String[])pageContext.getAttribute("arrayAttr1"))[3] --%>, ${arrayAttr1[3]} 
	-> 대부분의 에러를 내부적으로 공백으로 바꿈(값을 출력하기 위한 언어이기 때문에 에러를 신경쓰지 않는다)
	=> 에러를 처리히고 싶다, 제어문을 사용하고 싶다(JSTL)
	\${arrayAttr.prop1} : 객체의 전역변수 호출(배열은 전역변수가 없음), \${arrayAttr.1 } : 배열의 인덱스 호출(변수명 정의 원칙에 위배)
	
	since servlet 3.0 / EL 2.2 메서드 호출 지원
	${listAttr.get(0) }, ${listAttr[0] }, ${listAttr[3] }
	=> 집합 객체의 타입보다 특징에 집중한다.
	${mapAttr.get("key 2") }, \${mapAttr.key 2 }, ${mapAttr["key 2"] }
	${setAttr } -> set의 element를 direct로 꺼낼 수 없다
</PRE>
</body>
</html>