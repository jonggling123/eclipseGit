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
		$("tbody>tr").on("click", function(){
			var what = $(this).prop("id");
			location.href="${pageContext.request.contextPath}/prod/prodView.do?what="+what;
		}).css({cursor:"pointer"});
	});
</script>
</head>
<body>
<table>
	<thead>
		<c:set var="locale" value="${pageContext.request.locale.language }" />
		<fmt:setLocale value="${locale }" />
<%-- 		<fmt:setBundle basename="kr.or.ddit.msgs.message" var="bundle" /> --%>
		<fmt:bundle basename="kr.or.ddit.msgs.message" prefix="member."></fmt:bundle>
		
		<tr>
<%-- 			<c:forEach items="bundle" var="entry"> --%>
<%-- 				<th><fmt:message key="${entry['key'] }" /></th> --%>
<%-- 			</c:forEach> --%>
				<th><fmt:message key="mem_id" /></th>
				<th><fmt:message key="mem_name" /></th>
				<th><fmt:message key="mem_hp" /></th>
				<th><fmt:message key="mem_add1" /></th>
				<th><fmt:message key="mem_mail" /></th>
				<th><fmt:message key="mem_mileage" /></th>
		</tr>
	</thead>
	<tbody id="listBody">
		<c:set var="memberList" value="${pagingVO['dataList'] }"></c:set>
		<c:forEach var="member" items="${memberList }">
			<tr id="${member['mem_id']}">
				<td>${member['mem_id']}</td>
				<td><a href="${pageContext.request.contextPath }/member/memberView.do?who=${member['mem_id']}">${member['mem_name'] }</a></td>
				<td>${member['mem_hp']}</td>
				<td>${member['mem_add1']}</td>
				<td>${member['mem_mail']}</td>
				<td>${member['mem_mileage']}</td>
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
						<OPTION value="mem_name" ${pagingVO.searchType eq 'mem_name' ? "selected" : ""}>이름</OPTION>
						<OPTION value="mem_add1" ${pagingVO.searchType eq 'mem_add1' ? "selected" : ""}>지역</OPTION>
						<OPTION value="mem_hp" ${pagingVO.searchType eq 'mem_hp' ? "selected" : ""}>휴대폰</OPTION>
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
			url : "${pageContext.request.contextPath}/member/memberList.do",
			data : queryString,
			dataType : "json", // request header(Accept), response header(Content-Type)         
			success : function(resp) {
				var memberList = resp.dataList;
				var pagingHTML = resp.pagingHTMl;
				var trTags = [];
				$(memberList).each(function(idx, member){
					var tr = $("<tr>").prop("id", member.mem_id)
								.append(
									$("<td>").text(member.mem_id),		
									$("<td>").text(member.mem_name),
									$("<td>").text(member.mem_hp),		
									$("<td>").text(member.mem_add1),		
									$("<td>").text(member.mem_mail),		
									$("<td>").text(member.mem_mileage)		
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
		location.href="${pageContext.request.contextPath}/member/memberView.do?who="+what;
	}).css({cursor:"pointer"});
</SCRIPT>
</body>
</html>










