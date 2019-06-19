<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.BuyerVO"%>
<%@page import="kr.or.ddit.vo.PagingVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.3.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o" crossorigin="anonymous"></script>
<SCRIPT type="text/javascript" src="<%=request.getContextPath() %>/js/paging.js"></SCRIPT>

<script type="text/javascript">
	
	$(function(){
		$("tbody>tr").on("click", function(){
			var what = $(this).prop("id");
			location.href="<%=request.getContextPath()%>/buyer/buyerView.do?what="+what;
		}).css({cursor:"pointer"});
	});
</script>
</head>
<body>
<table>
	<thead>
		<tr>
			<th>거래처코드</th>
			<th>거래처명</th>
			<th>전화번호</th>
			<th>주소</th>
			<th>이메일</th>
			<th>담당자</th>
		</tr>
	</thead>
	<tbody id="listBody">
		<%
			//공유된 데이터 꺼내기
			//페이징 처리
			//부트스트랩 링크 연결
			
			PagingVO<BuyerVO> pagingVO = (PagingVO) request.getAttribute("pagingVO");
			List<BuyerVO> buyerList = pagingVO.getDataList();
			for(BuyerVO buyer : buyerList){
				%>
				<tr id="<%=buyer.getBuyer_id()%>">
					<td><%=buyer.getBuyer_id() %></td>
					<td><a href="<%=request.getContextPath() %>/buyer/buyerView.do?what=<%=buyer.getBuyer_id()%>"><%=buyer.getBuyer_name() %></a></td>
					<td><%=buyer.getBuyer_comtel() %></td>
					<td><%=buyer.getBuyer_add1() %></td>
					<td><%=buyer.getBuyer_add2() %></td>
					<td><%=buyer.getBuyer_charger() %></td>
				</tr>
				<%
			}
		%>
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
				<%=pagingVO.getPagingHTML() %> 
				</DIV>
			</TD>
		</tr>
	</TFOOT>
</table>
<SCRIPT type="text/javascript">
makePaging({
	formTagName:"searchHiddenForm",
	functionName:"<%=pagingVO.getFunctionName()%>",
	submitHandler:function(event){
		event.preventDefault();
		var queryString = $(event.target).serialize();
		event.target.page.value="";
		$.ajax({
			url : "<%=request.getContextPath()%>/member/memberList.do",
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
									$("<td>").text(member.mem_name),"
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
		location.href="<%=request.getContextPath() %>/member/memberView.do?who="+what;
	}).css({cursor:"pointer"});
</SCRIPT>
</body>
</html>