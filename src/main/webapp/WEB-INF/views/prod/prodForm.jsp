<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="https://jqueryui.com/resources/demos/style.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.3.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
	$( function() {
	    $( "#dialog-message" ).dialog({
	      modal: true,
	      autoOpen:${not empty message },
	      buttons: {
	        Ok: function() {
	          $( this ).dialog( "close" );
	        }
	      }
	    });
	  } );
</script>
</head>
<body>
<div id="dialog-message">
	${message }
</div>
	<form method="post" enctype="multipart/form-data">
		<input type="hidden" name="prod_id" value="${prod.prod_id }" />
		<table>
			<tr>
				<th>상품명</th>
				<td><input type="text" name="prod_name" required
					value="${prod.prod_name }" /><span class="error">${errors["prod_name"] }</span></td>
			</tr>
			<tr>
				<th>상품분류코드</th>
				<td>
					<select name="prod_lgu">
						<c:forEach items="${lprodList }" var="lprod">
							<option value="${lprod.lprod_gu }">${lprod.lprod_nm }</option>
						</c:forEach>
					</select>
					<script type="text/javascript">
						$("[name='prod_lgu']").on("change", function(){
							var selLgu = $(this).val();
							$("[name='prod_buyer']>option:not(:first)").hide();
							$("."+selLgu).show();
							$("[name='prod_buyer']").val("");
						});
						$("[name='prod_lgu']").val("${prod.prod_lgu }");
					</script>
					<span class="error">${errors["prod_lgu"] }</span></td>
			</tr>
			<tr>
				<th>거래처코드</th>
				<td>
					<select name="prod_buyer">
						<option value="">거래처선택</option>
						<c:forEach items="${buyerList }" var="buyer">
							<option class="${buyer.buyer_lgu }" value="${buyer.buyer_id }"
									 ${buyer.buyer_id eq prod.prod_buyer ? "selected":"" }>
									 ${buyer.buyer_name }
							</option>
						</c:forEach>
					</select>
					<span class="error">${errors["prod_buyer"] }</span></td>
			</tr>
			<tr>
				<th>구매가</th>
				<td><input type="number" name="prod_cost" required
					value="${prod.prod_cost }" /><span class="error">${errors["prod_cost"] }</span></td>
			</tr>
			<tr>
				<th>소비자가</th>
				<td><input type="number" name="prod_price" required
					value="${prod.prod_price }" /><span class="error">${errors["prod_price"] }</span></td>
			</tr>
			<tr>
				<th>판매가</th>
				<td><input type="number" name="prod_sale" required
					value="${prod.prod_sale }" /><span class="error">${errors["prod_sale"] }</span></td>
			</tr>
			<tr>
				<th>상품개략설명</th>
				<td><input type="text" name="prod_outline" required
					value="${prod.prod_outline }" /><span class="error">${errors["prod_outline"] }</span></td>
			</tr>
			<tr>
				<th>상세설명</th>
				<td><input type="text" name="prod_detail"
					value="${prod.prod_detail }" /><span class="error">${errors["prod_detail"] }</span></td>
			</tr>
			<tr>
				<th>상품이미지</th>
				<td>
					<input type="file" name="prod_image" />	
					<INPUT type="text" name="prod_img" value="${prod.prod_img }"/>
				</td>
			</tr>
			<tr>
				<th>재고량</th>
				<td><input type="number" name="prod_totalstock" required
					value="${prod.prod_totalstock }" /><span class="error">${errors["prod_totalstock"] }</span></td>
			</tr>
			<tr>
				<th>신규일자등록일</th>
				<td><input type="text" name="prod_insdate"
					value="${prod.prod_insdate }" /><span class="error">${errors["prod_insdate"] }</span></td>
			</tr>
			<tr>
				<th>안전재고수량</th>
				<td><input type="number" name="prod_properstock" required
					value="${prod.prod_properstock }" /><span class="error">${errors["prod_properstock"] }</span></td>
			</tr>
			<tr>
				<th>크기</th>
				<td><input type="text" name="prod_size"
					value="${prod.prod_size }" /><span class="error">${errors["prod_size"] }</span></td>
			</tr>
			<tr>
				<th>색상</th>
				<td><input type="text" name="prod_color"
					value="${prod.prod_color }" /><span class="error">${errors["prod_color"] }</span></td>
			</tr>
			<tr>
				<th>배달특기사항</th>
				<td><input type="text" name="prod_delivery"
					value="${prod.prod_delivery }" /><span class="error">${errors["prod_delivery"] }</span></td>
			</tr>
			<tr>
				<th>단위수량</th>
				<td><input type="text" name="prod_unit"
					value="${prod.prod_unit }" /><span class="error">${errors["prod_unit"] }</span></td>
			</tr>
			<tr>
				<th>총입고량</th>
				<td><input type="number" name="prod_qtyin"
					value="${prod.prod_qtyin }" /><span class="error">${errors["prod_qtyin"] }</span></td>
			</tr>
			<tr>
				<th>총판매량</th>
				<td><input type="number" name="prod_qtysale"
					value="${prod.prod_qtysale }" /><span class="error">${errors["prod_qtysale"] }</span></td>
			</tr>
			<tr>
				<th>개당마일리지</th>
				<td><input type="number" name="prod_mileage"
					value="${prod.prod_mileage }" /><span class="error">${errors["prod_mileage"] }</span></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="저장" />
					<input type="reset" value="취소" />
					<input type="button" value="목록으로" 
						onclick="location.href='<c:url value="/prod/prodList.do"/>';"
					/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>



