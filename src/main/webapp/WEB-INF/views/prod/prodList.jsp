<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<!-- Bootstrap core CSS -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" >
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.3.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" 	></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/paging.js"></script>
<script type="text/javascript">
	$(function(){
		makePaging({
			formTagName:"searchForm",
			functionName:"${pagingVO.functionName }",
			submitHandler:function(event){
				var queryString = $(event.target).serialize();
				$(event.target.page).val("");
				$.ajax({
					url:"${pageContext.request.contextPath }/prod/prodList.do",
					data : queryString,
					dataType : "json", // request header(Accept), response header(Content-Type)
					success : function(resp) {
						var prodList = resp.dataList;
						var trTags = [];
						$(prodList).each(function(idx, prod){
							var tr = $("<tr>").prop("id", prod.prod_id)
											 .append(
												$("<td>").text(prod.rnum),		 
												$("<td>").text(prod.prod_name),		 
												$("<td>").text(prod.lprod_nm),		 
												$("<td>").text(prod.buyer_name),		 
												$("<td>").text(prod.prod_cost),		 
												$("<td>").text(prod.prod_sale),		 
												$("<td>").text(prod.prod_mileage)
											 );
							trTags.push(tr);
						});
						$("#listBody").html(trTags);
						var pagingHTML = resp.pagingHTML;
						$("#pagingArea").html(pagingHTML);
						var stateObj = {
							pagingHTML:pagingHTML,
							listHTML:$("#listBody").html()
						}
						history.pushState(stateObj, "prodList", "?"+queryString);
					},
					error : function(errorResp) {
						console.log(errorResp.status);
					}
				});
				return false;
			}
		});
		$("tbody#listBody").on("click", "tr",function(){
			var what = $(this).prop("id");
			location.href="${pageContext.request.contextPath }/prod/prodView.do?what="+what;
		}).css({cursor:"pointer"});
		
		window.onpopstate=function(event){
			if(event.state){
				$("#pagingArea").html(event.state.pagingHTML);
				$("#listBody").html(event.state.listHTML);
			}else{
				location.reload();
			}
		}
	});
	// 비동기 테스트용
// 	function paging(){
		
// 	}
</script>
</head>
<body>
<h4> 상품 목록 조회 </h4>
<c:set var="user_auth" value="${sessionScope.authMember.mem_auth }" />
<c:if test="${'ROLE_ADMIN' eq user_auth}">
<a href="${pageContext.request.contextPath }/prod/prodInsert.do">신규상품 등록</a>
</c:if>
<form name="searchForm">
	상품분류 : 
	<select name="prod_lgu">
		<option value>분류선택</option>
		<c:forEach items="${lprodList }" var="lprod">
			<option value="${lprod.lprod_gu }">${lprod.lprod_nm }</option>
		</c:forEach>
	</select>
	
	<select name="prod_buyer">
		<option value="">거래처선택</option>
		<c:forEach items="${buyerList }" var="buyer">
			<option class="${buyer.buyer_lgu }" value="${buyer.buyer_id }"
					 ${buyer.buyer_id eq prod.prod_buyer ? "selected":"" }>
					 ${buyer.buyer_name }
			</option>
		</c:forEach>
	</select>
	상품명 : <input type="text" name="prod_name" value="${pagingVO.searchData.prod_name }"/>
	<input type="text" name="page" />
	<input type="submit" value="검색" />
</form>
<table>
	<thead>
		<tr>
			<th>행번호</th>
			<th>상품명</th>
			<th>상품분류명</th>
			<th>상품거래처명</th>
			<th>상품구매가</th>
			<th>상품판매가</th>
			<th>판매마일리지</th>
		</tr>
	</thead>
	<tbody id="listBody">
		<c:set var='prodList' value="${pagingVO.dataList }" />
		<c:if test="${not empty prodList }">
			<c:forEach items="${prodList }" var="prod">
				<tr id="${prod.prod_id }">
					<td>${prod.rnum }</td>
					<td>${prod.prod_name }</td>
					<td>${prod.lprod_nm }</td>
					<td>${prod.buyer_name }</td>
					<td>${prod.prod_cost }</td>
					<td>${prod.prod_sale }</td>
					<td>${prod.prod_mileage }</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty prodList }">
			<tr>
				<td colspan="7">
				 	상품이 존재하지 않습니다.
				</td>
			</tr>
		</c:if>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="7" id="pagingArea">
				${pagingVO.pagingHTML } 
			</td>
		</tr>
	</tfoot>
</table>
<script type="text/javascript">
	$("[name='prod_lgu']").on("change", function(){
		var selLgu = $(this).val();
		if(selLgu){
			$("[name='prod_buyer']>option:not(:first)").hide();
			$("."+selLgu).show();
		}
	});
	$("[name='prod_lgu']").val("${pagingVO.searchData.prod_lgu }");
	$("[name='prod_lgu']").trigger("change");
</script>
</body>
</html>






