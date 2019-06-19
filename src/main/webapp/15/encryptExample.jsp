<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="kr.or.ddit.utils.EncryptUtils"%>
<%@page import="javax.crypto.SecretKey"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<SCRIPt	src="<%=request.getContextPath() %>/js/jquery-3.3.1.min.js"></SCRIPT>
<SCRIPT type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/crypto-js.min.js"></SCRIPT>
<SCRIPT type="text/javascript">
	$(function() {
		$("#submitBtn").on("click", function() {
			console.log("됨?");
			$.ajax({
				url : "getSecretKey.jsp",
				dataType : "json", // request header(Accept), response header(Content-Type)         
				success : function(resp) {
					console.log("?");
					var encodedKey = resp.encodedKey;
					var encodedIv = resp.encodedIv;
					var key = CryptoJS.enc.Base64.parse(encodedKey);
					var iv = CryptoJS.enc.Base64.parse(encodedIv);
					var plain = $("#password").val();
					var encrypt = CryptoJS.AES.encrypt(plain , key, { iv: iv });
					var encodedPassword = encrypt.ciphertext.toString(CryptoJS.enc.Base64);
					document.loginForm.encodedPassword.value = encodedPassword;
					document.loginForm.submit();
				},
				error : function(errorResp) {
					console.log(errorResp.status);
				}
			});
		});
	});
</SCRIPT>
</head>
<body>
	<FORM method="post" name="loginForm">
		<INPUT type="text" name="encodedPassword" />
	</FORM>
	<HR />
	<INPUT type="text" id="password" />
	<INPUT type="button" value="로그인" id="submitBtn" />
	<hr />
	
	<c:if test="${not empty param.encodedPassword }">
		전송된 암호문 : ${param.encodedPassword }
		<%
			String encoded = request.getParameter("encodedPassword");
			SecretKey key = (SecretKey)session.getAttribute("secretKey");
			byte[] iv = (byte[])session.getAttribute("iv");
			byte[] decrypted = EncryptUtils.decryptAESFromBase64(encoded, key, iv);
		%>
		복호화 결과 : <%=new String(decrypted) %>
	</c:if>
</body>
</html>