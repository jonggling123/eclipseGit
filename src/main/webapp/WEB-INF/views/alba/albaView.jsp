<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="kr.or.ddit.vo.AlbaVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.3.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/feather-icons/4.9.0/feather.min.js"></script>
<title>Insert title here</title>
<%
	String message = (String)session.getAttribute("message");
	session.removeAttribute("message");
	if(StringUtils.isNotBlank(message)){
		%>
		<script type="text/javascript">
			alert("<%=message%>");
		</script>
		<%
	}
%>
</head>
<body>
	<!-- 한명의 알바생의 정보를 table 태그로 UI 구성 -->
	<%
		AlbaVO alba = (AlbaVO) request.getAttribute("albaVO");
	%>
	<h4><%=alba.getAl_name() %>의 정보</h4>
	<table class="table table-bordered">
		<tr>
			<th>아이디</th>
			<td><%=alba.getAl_id()%></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><%=alba.getAl_name()%></td>
		</tr>
		<tr>
			<th>나이</th>
			<td><%=alba.getAl_age()%></td>
		</tr>
		<tr>
			<th>주소</th>
			<td><%=alba.getAl_address()%></td>
		</tr>
		<tr>
			<th>휴대폰</th>
			<td><%=alba.getAl_hp()%></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><%=alba.getAl_mail()%></td>
		</tr>
		<tr>
			<th>성별</th>
			<td><%="M".equals(alba.getAl_gen())?"남":"여"%></td>
		</tr>
		<tr>
			<th>혈액형</th>
			<td><%=alba.getAl_btype()%>형</td>
		</tr>
		<tr>
			<th>최종학력</th>
			<td><%=alba.getGr_name()%></td>
		</tr>
		<tr>
			<th>자격증</th>
			<td><%=alba.getLicense_names()%></td>
		</tr>
		
		<tr>
			<th>특기사항</th>
			<td><%=alba.getAl_spec()%></td>
		</tr>
		<tr>
			<th>자기소개</th>
			<td><%=alba.getAl_desc()%></td>
		</tr>
		<tr>
			<th>경력사항</th>
			<td><%=alba.getAl_career()%></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" value="수정" class="btn btn-primary"
					onclick="location.href='<%=request.getContextPath() %>/alba/albaUpdate.do?who=<%=alba.getAl_id()%>';" />
				<input type="button" value="삭제"  class="btn btn-primary"
					onclick="location.href='<%=request.getContextPath() %>/alba/albaDelete.do?who=<%=alba.getAl_id()%>';" />
				<button type="button" onclick="history.back();"  class="btn btn-primary">뒤로가기</button>	
			</td>
		</tr>
	</table>
</body>
</html>











