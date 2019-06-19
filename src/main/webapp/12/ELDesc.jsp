<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>
<H4>EL(Expression Language : 표현 언어)</H4>
<PRE>
	: 데이터를 출력하기 위한 목적의 스크립트 형태 언어. (serverside 언어)
	토큰 구분 기호 : \${속성명(EL변수명)}
	<%
		pageContext.setAttribute("pageAttr", "페이지 영역의 속성");
		pageContext.setAttribute("requestAttr", "리퀘스트 영역의 속성", pageContext.REQUEST_SCOPE);
		pageContext.setAttribute("sameName", "페이지 데이터");
		request.setAttribute("sameName", "요청 데이터");
	%>
	1) 네 가지 기본 영역의 속성 데이터 출력
	<%=pageContext.getAttribute("pageAttr") %>, ${pageAttr}
	<%=request.getAttribute("requestAttr") %>, ${requestAttr}
	<%=pageContext.getAttribute("requestAttr", pageContext.REQUEST_SCOPE) %>
	<%=pageContext.getAttribute("sameName") %>, ${pageScope.sameName}
	<%=request.getAttribute("sameName") %>, ${requestScope.sameName}
		: 속성 데이터를 제일 작은 스코프부터 뒤진다 (나오면 더 안찾음)
	2) EL 연산자 지원 -> 피연산자 (리터럴, 속성만 가능)
		- 산술연산자 (+, -, /, *, %)
			${3+4}, ${'3'+4}, ${"3"+"4"} -> 단순한 산술 연산자로만 동작
			\${'a'+4} -> 숫자로 자동으로 파싱한다.
			=> **EL에서는 연산의 중심이 연산자이다.**
			
			${6/3}, ${5/2}, ${5%2} -> 나누기 연산자의 기본형은 double
			=> 캐스팅이 따로 필요 없다 (casting 연산자가 존재하지 않음)
		- 논리연산자 (&&(and), ||(or), |(not))
		<%
			pageContext.setAttribute("leftOp", false);
			pageContext.setAttribute("rightOp", "true");
			pageContext.setAttribute("leftStr", new String("스트링"));
			pageContext.setAttribute("rightStr", new String("스트링"));
		%>
			${true and true}, ${leftOp or rightOp} -> 피연산자를 자동으로 논리형으로 변환
			${not false}, ${not testOp}
		- 비교연산자 (gt, lt, ge, le, eq(==, equals 주소가 다르면 상태 비교 후 같으면 true), ne)
			${3 lt 5}, \${leftOp gt 4}, ${leftOp eq rightOp}
			${leftStr ne rightStr}
		- 단항연산자 (empty) -> 제일 많이 씀(표현식에 비해 코드가 많이 간결함)
			: 3.0까지는 할당 연산자 사용 불가(대입 연산자)
			=> 증감 연산자 사용 불가
			<%
				pageContext.setAttribute("testOp", "   ");
				List testList = new ArrayList();
				testList.add("");
				pageContext.setAttribute("testList1", testList);
			%>
			${empty testOp}, ${not empty testOp}
				- 판단 순서
				  1. 속성의 존재성 판단
				  2. null 체크 (null 이면 empty)
				  3. length(문자열)/size(컬렉션) 체크 (0 이면 empty) -> 공백여부 체크는 아님
			${empty testList1}, <%=testList!=null ? testList.size()==0 : false %>
		- 삼항연산자 (?:) \${조건식 ? 참 : 거짓}
			${not empty testList1 ? testList1.size() : "비어있음"}
	3) 속성으로 공유되고 있는 집합 객체(array, list, map, set)에 대한 접근 방법 제공
		elCollectionDesc.jsp 참고
	4) 속성으로 공유되고 있는 자바 객체에 대한 접근 방법 제공(dot notation)
		<jsp:useBean id="member" class="kr.or.ddit.vo.MemberVO" />
		<jsp:setProperty property="mem_name" name="member" value="김은대"/>
		${member }, \${member.getMem_name() }, \${member.mem_name }, ${member["mem_name"] }
		=> member의 객체에 직접 접근하는 방식은 아니다(private)
		\${member.getTestStr() }, ${member.testStr }, ${member["testStr"] }
		=> getter를 호출하는 코드
	5) EL의 기본 객체 11가지
		: elObjectDesc.jsp 참고
		: pageScope, requestScope, sessionScope, applicationScope (속성명의 중복으로 인한 문제를 해결하기 위해)
</PRE>
</body>
</html>