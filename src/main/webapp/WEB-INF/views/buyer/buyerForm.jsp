<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.util.Objects"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://jqueryui.com/resources/demos/style.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
<%String msg = (String) request.getAttribute("message");
			if (StringUtils.isNotBlank(msg)) {%>
		
			alert("<%=msg%>
	");
<%}%>
	
</script>
</head>
<body>
	<jsp:useBean id="buyer" scope="request" class="kr.or.ddit.vo.BuyerVO" />
	<jsp:useBean id="errors" scope="request"
		class="java.util.LinkedHashMap" />
	<h4>신규거래처 등록 양식</h4>
	<form method="post">
		<table class="table">
			<tr>
				<th>거래처 코드</th>
				<td><input type="text" name="buyer_id" required
					value="<%=Objects.toString(buyer.getBuyer_id(), "")%>" /><span
					class="error"><%=Objects.toString(errors.get("buyer_id"), "")%></span></td>
			</tr>
			<tr>
				<th>거래처명</th>
				<td><input type="text" name="buyer_name" required
					value="<%=Objects.toString(buyer.getBuyer_name(), "")%>" /><span
					class="error"><%=Objects.toString(errors.get("buyer_name"), "")%></span></td>
			</tr>
			<tr>
				<th>분류코드</th>
				<td><input type="text" name="buyer_lgu" required
					value="<%=Objects.toString(buyer.getBuyer_lgu(), "")%>" /><span
					class="error"><%=Objects.toString(errors.get("buyer_lgu"), "")%></span></td>
			</tr>
			<tr>
				<th>은행명</th>
				<td><input type="text" name="buyer_bank"
					value="<%=Objects.toString(buyer.getBuyer_bank(), "")%>" /><span
					class="error"><%=Objects.toString(errors.get("buyer_bank"), "")%></span></td>
			</tr>
			<tr>
				<th>계좌번호</th>
				<td><input type="text" name="buyer_bankno"
					value="<%=Objects.toString(buyer.getBuyer_bankno(), "")%>" /><span
					class="error"><%=Objects.toString(errors.get("buyer_bankno"), "")%></span></td>
			</tr>
			<tr>
				<th>예금주</th>
				<td><input type="text" name="buyer_bankname"
					value="<%=Objects.toString(buyer.getBuyer_bankname(), "")%>" /><span
					class="error"><%=Objects.toString(errors.get("buyer_bankname"), "")%></span></td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td><input type="text" name="buyer_zip"
					value="<%=Objects.toString(buyer.getBuyer_zip(), "")%>" /><span
					class="error"><%=Objects.toString(errors.get("buyer_zip"), "")%></span></td>
			</tr>
			<tr>
				<th>주소1</th>
				<td><input type="text" name="buyer_add1"
					value="<%=Objects.toString(buyer.getBuyer_add1(), "")%>" /><span
					class="error"><%=Objects.toString(errors.get("buyer_add1"), "")%></span></td>
			</tr>
			<tr>
				<th>주소2</th>
				<td><input type="text" name="buyer_add2"
					value="<%=Objects.toString(buyer.getBuyer_add2(), "")%>" /><span
					class="error"><%=Objects.toString(errors.get("buyer_add2"), "")%></span></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type="text" name="buyer_comtel" required
					value="<%=Objects.toString(buyer.getBuyer_comtel(), "")%>" /><span
					class="error"><%=Objects.toString(errors.get("buyer_comtel"), "")%></span></td>
			</tr>
			<tr>
				<th>팩스번호</th>
				<td><input type="text" name="buyer_fax" required
					value="<%=Objects.toString(buyer.getBuyer_fax(), "")%>" /><span
					class="error"><%=Objects.toString(errors.get("buyer_fax"), "")%></span></td>
			</tr>
			<tr>
				<th>메일</th>
				<td><input type="text" name="buyer_mail" required
					value="<%=Objects.toString(buyer.getBuyer_mail(), "")%>" /><span
					class="error"><%=Objects.toString(errors.get("buyer_mail"), "")%></span></td>
			</tr>
			<tr>
				<th>담당자</th>
				<td><input type="text" name="buyer_charger"
					value="<%=Objects.toString(buyer.getBuyer_charger(), "")%>" /><span
					class="error"><%=Objects.toString(errors.get("buyer_charger"), "")%></span></td>
			</tr>
			<tr>
				<th>담당자연락처</th>
				<td><input type="text" name="buyer_telext"
					value="<%=Objects.toString(buyer.getBuyer_telext(), "")%>" /><span
					class="error"><%=Objects.toString(errors.get("buyer_telext"), "")%></span></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="전송" /> <input
					type="reset" value="취소" /> <input type="button" value="뒤로가기"
					onclick="history.go(-1);" /></td>
			</tr>
		</table>
	</form>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o"
		crossorigin="anonymous"></script>
</body>
</html>