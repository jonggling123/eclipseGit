<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.util.Objects"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="buyer" class="kr.or.ddit.vo.BuyerVO" scope="request" />
	
	<table>
		<tr>
				<th>거래처 코드</th>
				<td><%=Objects.toString(buyer.getBuyer_id(), "")%></td>
			</tr>
			<tr>
				<th>거래처명</th>
				<td><%=Objects.toString(buyer.getBuyer_name(), "")%></td>
			</tr>
			<tr>
				<th>분류명</th>
				<td><%=Objects.toString(buyer.getLprod_nm(), "")%></td>
			</tr>
			<tr>
				<th>은행명</th>
				<td><%=Objects.toString(buyer.getBuyer_bank(), "")%></td>
			</tr>
			<tr>
				<th>계좌번호</th>
				<td><%=Objects.toString(buyer.getBuyer_bankno(), "")%></td>
			</tr>
			<tr>
				<th>예금주</th>
				<td><%=Objects.toString(buyer.getBuyer_bankname(), "")%></td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td><%=Objects.toString(buyer.getBuyer_zip(), "")%></td>
			</tr>
			<tr>
				<th>주소1</th>
				<td><%=Objects.toString(buyer.getBuyer_add1(), "")%></td>
			</tr>
			<tr>
				<th>주소2</th>
				<td><%=Objects.toString(buyer.getBuyer_add2(), "")%></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><%=Objects.toString(buyer.getBuyer_comtel(), "")%></td>
			</tr>
			<tr>
				<th>팩스번호</th>
				<td><%=Objects.toString(buyer.getBuyer_fax(), "")%></td>
			</tr>
			<tr>
				<th>메일</th>
				<td><%=Objects.toString(buyer.getBuyer_mail(), "")%></td>
			</tr>
			<tr>
				<th>담당자</th>
				<td><%=Objects.toString(buyer.getBuyer_charger(), "")%></td>
			</tr>
			<tr>
				<th>담당자연락처</th>
				<td><%=Objects.toString(buyer.getBuyer_telext(), "")%></td>
			</tr>
	</table>
</body>
</html>