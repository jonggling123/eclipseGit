<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.AlbaVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.3.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/feather-icons/4.9.0/feather.min.js"></script>
<script type="text/javascript">
	function clickHandler(albaId){
		location.href="<%=request.getContextPath()%>/alba/albaView.do?who="+albaId;
	}
</script>
</head>
<body>
<%
	List<AlbaVO> albaList = (List)request.getAttribute("albaList");
%>
<a class="btn 	badge-info" href="<%=request.getContextPath() %>/alba/albaInsert.do">신규등록하러 가기</a>
<table class="table table-striped table-bordered">
	<thead class="table-header thead-dark">
		<tr>
			<th>알바생아이디</th>
			<th>알바생이름</th>
			<th>알바생나이</th>
			<th>알바생주소</th>
			<th>알바생휴대폰</th>
		</tr>
	</thead>
	<tbody>
		<%
			for(AlbaVO alba : albaList){	
				%>
				<tr>
					<td><%=alba.getAl_id() %></td>
					<td>
						<a href="javascript:clickHandler('<%=alba.getAl_id()%>')"><%=alba.getAl_name() %></a>
					</td>
					<td><%=alba.getAl_age() %></td>
					<td><%=alba.getAl_address() %></td>
					<td><%=alba.getAl_hp() %></td>
				</tr>
				<%
			}
		%>
	</tbody>
</table>
</body>
</html>







