<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>
	<FORM action="${pageContext.request.contextPath }/fileUpload2_5.do" method="post"
		enctype="multipart/form-data">
		업로더 명 : <INPUT type="text" name="uploader" />
		업로드할 자료 : <INPUT type="file" name="uploadFile" />
		<INPUT type="submit" value="업로드"/>
	</FORM>
<c:if test="${not empty sessionScope.dataMap }">
	업로드 결과 : ${sessionScope.dataMap }
	<c:remove var="dataMap" scope="session"/>
</c:if>
</body>
</html>