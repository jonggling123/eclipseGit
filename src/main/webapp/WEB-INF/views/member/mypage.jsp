<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	<c:if test="${not empty message }">
		alert("${sessionScope.message}");
	</c:if>
	
	$(function() {
		var myPageForm = $("[name='myPageForm']");
		$("#delBtn").on("click",
			function() {
				var origin = myPageForm.attr("action");
				myPageForm
						.attr("action",
								"${pageContext.request.contextPath}/member/memberDelete.do")
				myPageForm.find(":input:not(.both)").prop(
						"disabled", true);
				var password = myPageForm.find("[name='mem_pass']")
						.val();
				if (password) {
					myPageForm.submit();
				} else {
					alert("비밀번호 입력하셈.");
				}
				myPageForm.attr("action", origin);
				myPageForm.find(":input").prop("disabled", false);
			});
		
		   var previewArea = $("#previewArea");
		      $("#mem_image").on("change", function(){
		         var files = $(this).prop("files");
		         var reader = new FileReader();
		         reader.onloadend=function(event){
		            var imgTag = $("<img />")
		                              .attr({src:event.target.result})
		                              .css({width:"100%", height:"100%"});
//		             이벤트타겟은 리더
		            previewArea.html(imgTag);
		         }
		         reader.readAsDataURL(files[0]);
		         
		      })
	});
</script>
</head>
<body>
	<jsp:useBean id="errors" scope="request"
		class="java.util.LinkedHashMap" />
	<h4>${sessionScope.authMember.mem_name}님의마이페이지</h4>
	<form name="myPageForm"
		action="<%=request.getContextPath()%>/member/memberUpdate.do"
		method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<th>회원아이디</th>
				<td><input type="text" class="both" name="mem_id" required
					value="${requestScope.member.mem_id}" /><span class="error">${errors.mem_id }</span></td>
			</tr>
			<tr>
				<th>비밀번호(${requestScope.member.mem_pass})</th>
				<td><input type="text" class="both" name="mem_pass" required
					value="" /><span class="error">${errors.mem_pass}</span></td>
			</tr>
			<tr>
				<th>회원명</th>
				<td><input type="text" name="mem_name" required
					value="${requestScope.member.mem_name}" /><span class="error">${errors.mem_name}</span></td>
			</tr>
			<tr>
	            <th>이미지</th>
	            <td>
	            	<c:if test="${not empty member.mem_img }">
		               <img src="data:image/*;base64,${member.mem_imgBase64 }" />
	            	</c:if>
	               <input type="file" id="mem_image" name="mem_image" accept="image/*"/>
	               <SPAN id="previewArea"></SPAN>
	            </td>
         	</tr>
			<tr>
				<th>주민번호1</th>
				<td><input type="text" name="mem_regno1"
					value="${requestScope.member.mem_regno1}" /><span class="error">${errors.mem_regno1}</span></td>
			</tr>
			<tr>
				<th>주민번호2</th>
				<td><input type="text" name="mem_regno2"
					value="${requestScope.member.mem_regno2}" /><span class="error">${errors.mem_regno2 }</span></td>
			</tr>
			<tr>
				<th>생일</th>
				<td><input type="text" name="mem_bir"
					value="${requestScope.member.mem_bir}" /><span class="error">${errors.mem_bir }</span></td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td><input type="text" name="mem_zip" required
					value="${requestScope.member.mem_zip}" /><span class="error">${errors.mem_zip }</span></td>
			</tr>
			<tr>
				<th>주소1</th>
				<td><input type="text" name="mem_add1" required
					value="${requestScope.member.mem_add1}" /><span class="error">${errors.mem_add1 }</span></td>
			</tr>
			<tr>
				<th>주소2</th>
				<td><input type="text" name="mem_add2" required
					value="${requestScope.member.mem_add2}" /><span class="error">${errors.mem_add2 }</span></td>
			</tr>
			<tr>
				<th>집전번</th>
				<td><input type="text" name="mem_hometel"
					value="${requestScope.member.mem_hometel}" /><span class="error">${errors.mem_hometel }</span></td>
			</tr>
			<tr>
				<th>회사전번</th>
				<td><input type="text" name="mem_comtel"
					value="${requestScope.member.mem_comtel}" /><span class="error">${errors.mem_comtel }</span></td>
			</tr>
			<tr>
				<th>휴대폰</th>
				<td><input type="text" name="mem_hp"
					value="${requestScope.member.mem_hp}" /><span class="error">${errors.mem_hp }</span></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" name="mem_mail" required
					value="${requestScope.member.mem_mail}" /><span class="error">${errors.mem_mail }</span></td>
			</tr>
			<tr>
				<th>직업</th>
				<td><input type="text" name="mem_job"
					value="${requestScope.member.mem_job}" /><span class="error">${errors.mem_id }</span></td>
			</tr>
			<tr>
				<th>취미</th>
				<td><input type="text" name="mem_like"
					value="${requestScope.member.mem_like}" /><span class="error">${errors.mem_id }</span></td>
			</tr>
			<tr>
				<th>기념일</th>
				<td><input type="text" name="mem_memorial"
					value="${requestScope.member.mem_memorial}" /><span class="error">${errors.mem_id }</span></td>
			</tr>
			<tr>
				<th>기념일자</th>
				<td><input type="text" name="mem_memorialday"
					value="${requestScope.member.mem_memorialday}" /><span
					class="error">${errors.mem_id }</span></td>
			</tr>
			<tr>
				<th>마일리지</th>
				<td><input type="number" name="mem_mileage"
					value="${requestScope.member.mem_mileage}" /><span class="error">${errors.mem_id }</span></td>
			</tr>
			<tr>
				<th>탈퇴여부</th>
				<td><input type="text" name="mem_delete"
					value="${requestScope.member.mem_delete}" /><span class="error">${errors.mem_id }</span></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="저장" /> <input
					type="reset" value="취소" /> <input type="button" value="탈퇴"
					id="delBtn" class="both" /></td>
			</tr>
		</table>
	</form>
</body>
</html>















