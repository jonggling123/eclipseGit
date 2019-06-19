<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
   href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
   rel="stylesheet"
   integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
   crossorigin="anonymous">
<script type="text/javascript"
   src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<script
   src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
   integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o"
   crossorigin="anonymous"></script>
<script type="text/javascript"
   src="${pageContext.request.contextPath}/js/paging.js"></script>
</head>
<body>
   <table>
      <tr>
         <th>글번호</th>
         <td></td>
      </tr>
      <tr>
         <th>제목</th>
         <td>${board.bo_title }</td>
      </tr>
      <tr>
         <th>작성자</th>
         <td>${board.bo_writer }</td>
      </tr>
      <tr>
         <th>비밀번호</th>
         <td>${board.bo_pass }</td>
      </tr>
      <tr>
         <th>작성일</th>
         <td>${board.bo_date }</td>
      </tr>
      <tr>
         <th>내용</th>
         <td>${board.bo_content }</td>
      </tr>
      <tr>
         <th>게시판분류</th>
         <td>${board.code_id }</td>
      </tr>
      <tr>
         <th>조회수</th>
         <td>${board.bo_hit }</td>
      </tr>
      <tr>
         <th>신고수</th>
         <td>${board.bo_report }</td>
      </tr>
      <tr>
         <th>좋아요수</th>
         <td>${board.bo_like }</td>
      </tr>
      <tr>
         <th>싫어요수</th>
         <td>${board.bo_hate }</td>
      </tr>
      <tr>
         <th>아이피</th>
         <td>${board.bo_ip }</td>
      </tr>
      <tr>
         <th>이메일</th>
         <td>${board.bo_mail }</td>
      </tr>
      <tr>
         <th>부모글</th>
         <td>${board.bo_parent }</td>
      </tr>
   </table>
   <table>
   <thead>
      <tr>
         
         <th>댓글 내용</th>
      </tr>
      </thead>
      <tbody id="listBody">
   <!--댓글의 목록을 비동기로  -->
         
      </tbody>
      <tfoot id="pagingArea">
      
      </tfoot>
   </table>
   <form name="searchHiddenForm"><!-- jquery 에선 id를 주로 씀 --><!-- paging 함수가 호출되면 이 태그가 submit(3)  -->
      <input type="hidden" name="page"><!--  paging 함수에서 넘기는 파라미터 데이터(2)-->
      <input type="hidden" name="bo_no" value="${board.bo_no }">
   </form>
   
   <script type="text/javascript">
   makePaging({
      formTagName : "searchHiddenForm",
      functionName : "paging",
      submitHandler : function(event) {/* submit 핸들러가 동작(4) */
         event.preventDefault();
         var queryString = $(event.target).serialize();/*target이 searchHiddenForm  */
         event.target.page.value = "";/* target을 대상으로 queryString을 뽑아내는것(5) */
         $.ajax({/* 뽑아낸 데이터가 세팅이 되서 비동기 요청이 날아감(6)  */
                  url : "${pageContext.request.contextPath}/reply/replyList.do",
                  data : queryString,
                  dataType : "json", // request header(Accept), response header(Content-Type)
                  success : function(resp) { /* 비동기 요청이 날아가서 json 데이터를 받아옴(7) */
                     var replyList = resp.dataList;
                     var pagingHTML = resp.pagingHTML;
                     var trTags = [];
                     $(replyList).each(
                                 function(idx, reply) {
                                    var tr = $("<tr>")
                                       .prop("id",reply.rep_no)
                                       .append($("<td>").text(reply.rep_content));
                                    trTags.push(tr);
                                 });
                     $("#listBody").html(trTags);
                     var pagingHTML = resp.pagingHTML;
                     $("#pagingArea").html(pagingHTML);

                  },
                  error : function(errorResp) {
                     console.log(errorResp.status);
                  }
               });
         return false; // 동기 요청 취소
      }
   });
   paging(1);//여기에서 페이지에 대한 요청(1)
   
</script>
</body>
</html>