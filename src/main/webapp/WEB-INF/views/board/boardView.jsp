<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.3.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o" crossorigin="anonymous"></script>
<SCRIPT type="text/javascript" src="<%=request.getContextPath() %>/js/paging.js"></SCRIPT>
<LINK href="http://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css"/>
<SCRIPT type="text/javascript" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></SCRIPT>
<SCRIPT type="text/javascript">
	$(function() {
		$("#pagingArea").DataTable();
	});
</SCRIPT>
</head>
<c:set var="allReplyList" value="${board.replyList }"/>
<body>
	<TABLE>
			<tr>
				<th>게시글번호</th>
				<td>${board.bo_no}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${board.bo_title}</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${board.bo_writer}</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>${board.bo_pass}</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${board.bo_date}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${board.bo_content}</td>
			</tr>
			<tr>
				<th>코드 값</th>
				<td>${board.code_name}</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${board.bo_hit}</td>
			</tr>
			<tr>
				<th>신고회수</th>
				<td>${board.bo_report}</td>
			</tr>
			<tr>
				<th>좋아요</th>
				<td>${board.bo_like}</td>
			</tr>
			<tr>
				<th>싫어요</th>
				<td>${board.bo_hate}</td>
			</tr>
			<tr>
				<th>아이피</th>
				<td>${board.bo_ip}</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>${board.bo_mail}</td>
			</tr>
			<tr>
				<th>부모글번호</th>
				<td>${board.bo_parent}</td>
			</tr>
		</table>
		<br>
		<table>
		<thead>
		<tr>
			<th>작성자</th>
			<th>작성일</th>
			<th>내용</th>
		</tr>
	</thead>
			<tbody id="listBody">
			<c:set var="replyList" value="${pagingVO['replyList'] }"></c:set>
			<c:forEach var="reply" items="${replyList }">
				<tr id="${reply['rep_no']}">
					<td>${reply['rep_writer']}</td>
					<td>${reply['rep_date']}</td>
					<td>${reply['rep_content']}</td>
				</tr>
			</c:forEach>
			</tbody>
			<TFOOT>
			<tr>
				<TD colspan="3">
					<FORM name="searchHiddenForm" >
					<INPUT type="hidden" name="page"/>
					<INPUT type="hidden" name="what" value="${board.bo_no }"/>
					<INPUT type="submit" value="댓글보기"/>
					</FORM>
					<DIV id="pagingArea">
					${allReplyList } 
					</DIV>
				</TD>
			</tr>
			</TFOOT>
	</TABLE>
	<SCRIPT type="text/javascript">
makePaging({
	formTagName:"searchHiddenForm",
	functionName:"paging",
	submitHandler:function(event){
		event.preventDefault();
		var queryString = $(event.target).serialize();
		event.target.page.value="";
		$.ajax({
			url : "${pageContext.request.contextPath}/reply/replyList.do",
			data : queryString,
			dataType : "json", // request header(Accept), response header(Content-Type)         
			success : function(resp) {
				var replyList = resp.dataList;
				var pagingHTML = resp.pagingHTMl;
				
				var start = resp.startRow;
				var end = resp.endRow;
				var trTags = [];
				//client side paging
				//조회 데이터가 약 1000건 이하인 경우는 클라이언트에게
				//데이터를 보내서 클라이언트가 직접 잘라서 쓰도록 하는 방법
				//클라이언트의 가용 메모리에 저장 시킨다
				$(replyList).each(function(idx, reply){
					if(idx>=start && idx<=end){
					var tr = $("<tr>").prop("id", reply.rep_no)
					.append(
						$("<td>").text(reply.rep_writer),		
						$("<td>").text(reply.rep_date),		
						$("<td>").text(reply.rep_content)
					);
				trTags.push(tr);
						
					}
				});
				$("#listBody").html(trTags);
				var pagingHTML = resp.pagingHTML;
				$("#pagingArea").html(pagingHTML);
			},
			error : function(errorResp) {
				console.log(errorResp.status);
			}
		});
		
		return false; //동기 요청 취소
	}
	
});
</SCRIPT>
</body>
</html>