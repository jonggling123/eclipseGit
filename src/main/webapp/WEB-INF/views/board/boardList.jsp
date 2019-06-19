<?xml version="1.0" encoding="UTF-8" ?>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<!-- Bootstrap core CSS -->
<title>Insert title here</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.3.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o" crossorigin="anonymous"></script>
<SCRIPT type="text/javascript" src="<%=request.getContextPath() %>/js/paging.js"></SCRIPT>
<script type="text/javascript">
	
	$(function(){
		$('').DataTable();
		$("tbody>tr").on("click", function(){
			var what = $(this).prop("id");
			location.href="${pageContext.request.contextPath}/board/boardView.do?what="+what;
		}).css({cursor:"pointer"});
	});
</script>
</head>
<body>
<table>
	<thead>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
	</thead>
	<tbody id="listBody">
		<c:set var="boardList" value="${pagingVO['dataList'] }"></c:set>
		<c:forEach var="board" items="${boardList }">
			<tr id="${board['bo_no']}">
				<td>${board['bo_no']}</td>
				<td><a href="${pageContext.request.contextPath }/board/boardView.do?what=${board['bo_no']}">${board['bo_title'] }</a></td>
				<td>${board['bo_writer']}</td>
				<td>${board['bo_date']}</td>
				<td>${board['bo_hit']}</td>
			</tr>
		</c:forEach>
	</tbody>
	<TFOOT>
		<tr>
			<TD colspan="6">
				<FORM name="searchHiddenForm" >
					<INPUT type="hidden" name="searchType" value="${pagingVO.searchType}"/>
					<INPUT type="hidden" name="searchWord" value="${pagingVO.searchWord}"/>
					<INPUT type="hidden" name="page"/>
				</FORM>
				<FORM name="searchForm">
					<SELECT name="searchType">
						<OPTION value="all" ${pagingVO.searchType eq 'all' ? "selected" : ""}>전체</OPTION>
						<OPTION value="bo_title" ${pagingVO.searchType eq 'mem_name' ? "selected" : ""}>제목</OPTION>
						<OPTION value="bo_writer" ${pagingVO.searchType eq 'mem_add1' ? "selected" : ""}>작성자</OPTION>
						<OPTION value="bo_content" ${pagingVO.searchType eq 'mem_hp' ? "selected" : ""}>내용</OPTION>
					</SELECT>
					<INPUT type="text" name="searchWord" value="${pagingVO.searchWord}"/>
					<INPUT type="hidden" name="page"/>
					<INPUT type="submit" value="검색"/>
				</FORM>
				<DIV id="pagingArea">
				${pagingVO.pagingHTML } 
				</DIV>
			</TD>
		</tr>
	</TFOOT>
</table>
<SCRIPT type="text/javascript">
makePaging({
	formTagName:"searchHiddenForm",
	functionName:"${pagingVO.functionName}",
	submitHandler:function(event){
		event.preventDefault();
		var queryString = $(event.target).serialize();
		event.target.page.value="";
		$.ajax({
			url : "${pageContext.request.contextPath}/board/boardList.do",
			data : queryString,
			dataType : "json", // request header(Accept), response header(Content-Type)         
			success : function(resp) {
				var boardList = resp.dataList;
				var pagingHTML = resp.pagingHTMl;
				var trTags = [];
				$(boardList).each(function(idx, board){
					var tr = $("<tr>").prop("id", board.bo_no)
								.append(
									$("<td>").text(board.bo_no),		
									$("<td>").text(board.bo_title),
									$("<td>").text(board.bo_writer),		
									$("<td>").text(board.bo_date),		
									$("<td>").text(board.bo_hit)		
								);
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
		
		return false; //동기 요청 취소
	}
	
});
	$("tbody#listBody").on("click",  "tr", function(){
		var what = $(this).prop("id");
		location.href="${pageContext.request.contextPath}/board/boardView.do?what="+what;
	}).css({cursor:"pointer"});
</SCRIPT>
</body>
</html>










