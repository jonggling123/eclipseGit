<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.Objects"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="https://jqueryui.com/resources/demos/style.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
   <c:if test="${not empty message}">
<%-- <% --%>
//    String msg = (String) request.getAttribute("message");
//    if(StringUtils.isNotBlank(msg)){
<%--       %> --%>
      
         alert("${message}");
<%--       <% --%>
//    }
   </c:if>
   $(function(){
      $("[type='date']").datepicker({
         dateFormat:"yy-mm-dd",
         changeYear:true,
         changeMonth:true,
         language:"ko"
      });
         var previewArea = $("#previewArea");
      $("#mem_image").on("change", function(){
         var files = $(this).prop("files");
         var reader = new FileReader();
         reader.onloadend=function(event){
            var imgTag = $("<img />")
                              .attr({src:event.target.result})
                              .css({width:"100%", height:"100%"});
//             이벤트타겟은 리더
            previewArea.html(imgTag);
         }
         reader.readAsDataURL(files[0]);
         
      })
   });
</script>
</head>
<body>
<jsp:useBean id="member" scope="request" class="kr.or.ddit.vo.MemberVO" />
<jsp:useBean id="errors" scope="request" class="java.util.LinkedHashMap" />
   <h4>신규회원 가입 양식</h4>
   <form method="post" enctype="multipart/form-data">
      <table class="table">
         <tr>
            <th>회원아이디</th>
            <td><input type="text" name="mem_id" required
            value="${requestScope['member']['mem_id']}" />
<%--                value="<%=Objects.toString(member.getMem_id(), "")%>" /> --%>
               <span class="error">${requestScope['errors']['mem_id']}</span></td>
         </tr>
         <tr>
            <th>비밀번호</th>
            <td><input type="text" name="mem_pass" required
               value="${requestScope['member']['mem_pass']}" /><span class="error">${requestScope['errors']['mem_pass']}</span></td>
         </tr>
         <tr>
            <th>회원명</th>
            <td><input type="text" name="mem_name" required
               value="${requestScope['member']['mem_name']}" /><span class="error">${requestScope['errors']['mem_name']}</span></td>
         </tr>
         <tr>
            <th>이미지</th>
            <td>
               <input type="file" id="mem_image" name="mem_image" accept="image/*"/>
               <span style="width:200px; height:200px;" id="previewArea"></span>
            </td>
         </tr>
         <tr>
            <th>주민번호1</th>
            <td><input type="text" name="mem_regno1"
               value="${requestScope['member']['mem_regno1']}" /><span class="error">${requestScope['errors']['mem_regno1']}</span></td>
         </tr>
         <tr>
            <th>주민번호2</th>
            <td><input type="text" name="mem_regno2"
               value="${requestScope['member']['mem_regno2']}" /><span class="error">${requestScope['member']['mem_regno2']}</span></td>
         </tr>
         <tr>
            <th>생일</th>
            <td><input type="date" name="mem_bir"
               value="${requestScope['member']['mem_bir']}" /><span class="error">${requestScope['errors']['mem_bir']}</span></td>
         </tr>
         <tr>
            <th>우편번호</th>
            <td><input type="text" name="mem_zip" required
               value="${requestScope['member']['mem_zip']}" /><span class="error">${requestScope['errors']['mem_zip']}</span></td>
         </tr>
         <tr>
            <th>주소1</th>
            <td><input type="text" name="mem_add1" required
               value="${requestScope['member']['mem_add1']}" /><span class="error">${requestScope['errors']['mem_add1']}</span></td>
         </tr>
         <tr>
            <th>주소2</th>
            <td><input type="text" name="mem_add2" required
               value="${requestScope['member']['mem_add2']}" /><span class="error">${requestScope['errors']['mem_add2']}</span></td>
         </tr>
         <tr>
            <th>집전번</th>
            <td><input type="text" name="mem_hometel"
               value="${requestScope['member']['mem_hometel']}" /><span class="error">${requestScope['errors']['mem_hometel']}</span></td>
         </tr>
         <tr>
            <th>회사전번</th>
            <td><input type="text" name="mem_comtel"
               value="${requestScope['member']['mem_comtel']}" /><span class="error">${requestScope['errors']['mem_comtel']}</span></td>
         </tr>
         <tr>
            <th>휴대폰</th>
            <td><input type="text" name="mem_hp"
               value="${requestScope['member']['mem_hp']}" /><span class="error">${requestScope['errors']['mem_hp']}</span></td>
         </tr>
         <tr>
            <th>이메일</th>
            <td><input type="text" name="mem_mail" required
               value="${requestScope['member']['mem_mail']}" /><span class="error">${requestScope['errors']['mem_mail']}</span></td>
         </tr>
         <tr>
            <th>직업</th>
            <td><input type="text" name="mem_job"
               value="${requestScope['member']['mem_job']}" /><span class="error">${requestScope['errors']['mem_job']}</span></td>
         </tr>
         <tr>
            <th>취미</th>
            <td><input type="text" name="mem_like"
               value="${requestScope['member']['mem_like']}" /><span class="error">${requestScope['errors']['mem_like']}</span></td>
         </tr>
         <tr>
            <th>기념일</th>
            <td><input type="text" name="mem_memorial"
               value="${requestScope['member']['mem_memorial']}" /><span class="error">${requestScope['errors']['mem_memorial']}</span></td>
         </tr>
         <tr>
            <th>기념일자</th>
            <td><input type="date" name="mem_memorialday"
               value="${requestScope['member']['mem_memorialday']}" 
               placeholder="1999-01-01"
               /><span class="error">${requestScope['errors']['mem_memorialday']}</span></td>
         </tr>
         <tr>
            <th>마일리지</th>
            <td>1000</td>
         </tr>
         <tr>
            <td colspan="2">
               <input type="submit" value="전송" />
               <input type="reset" value="취소" />
               <input type="button" value="뒤로가기" 
                  onclick="history.go(-1);"
               />
            </td>
         </tr>
      </table>
   </form>
   <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o" crossorigin="anonymous"></script>
</body>
</html>




