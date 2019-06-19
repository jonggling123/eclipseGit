<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="kr.or.ddit.vo.AlbaVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>02/albaForm.jsp</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.3.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/feather-icons/4.9.0/feather.min.js"></script>
</head>
<body>
<h4>알바천국 가입 및 수정 양식</h4>
<!-- 프로필 : 이름(name, 문자), 나이(age, 숫자), 생년월일(birth, 문자),
			 주소(address, 문자), 전번(hp, 문자), -->
<!--          특기사항(spec, 문자), 자기소개(desc, 문자), 
				자격증(lic, 코드형문자), -->
<!--          학력(grade, 코드형문자), 경력사항(career, 문자), 
			성별(gen, 선택형문자), 혈액형(btype, 선택형문자),  -->
<!--          이메일(mail, 문자) -->

<jsp:useBean id="alba" class="kr.or.ddit.vo.AlbaVO" scope="request" />
<jsp:useBean id="errors" class="java.util.HashMap" scope="request" />
<%
	List<Map<String,String>> licenses = (List<Map<String,String>>) request.getAttribute("licenses"); 
	List<Map<String,String>> grades = (List<Map<String,String>>) request.getAttribute("grades"); 
%>
<form method="post" >
<input type="hidden" name="al_id" value="<%=alba.getAl_id()%>" />
	<table class="table table-bordered">
			<tr>
				<th>이름</th>
				<td>
				<div class="form-group form-inline">
				<input type="text" name="al_name" required class="form-control col-6"
					value="<%=alba.getAl_name()%>" />
				<span class="error form-control-plaintext col-6"><%=errors.get("al_name")%></span></div></td>
			</tr>
			<tr>
				<th>나이</th>
				<td><div class="form-group form-inline"><input type="text" name="al_age" required class="form-control col-6"
					value="<%=alba.getAl_age()%>" />
				<span class="error form-control-plaintext col-6"><%=errors.get("al_age")%></span></div></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><div class="form-group form-inline"><input type="text" name="al_address" required class="form-control col-6"
					value="<%=alba.getAl_address()%>" />
				<span class="error form-control-plaintext col-6"><%=errors.get("al_address")%></span></div></td>
			</tr>
			<tr>
				<th>휴대폰</th>
				<td><div class="form-group form-inline"><input type="text" name="al_hp" required class="form-control col-6"
					value="<%=alba.getAl_hp()%>" />
				<span class="error form-control-plaintext col-6"><%=errors.get("al_hp")%></span></div></td>
			</tr>
			<tr>
				<th>성별</th>
				<td>
				<div class="form-check form-check-inline">
					<input type="radio" name="al_gen" id="al_gen_m" value="M" class="form-check-input"
							<%="M".equals(alba.getAl_gen()) || StringUtils.isBlank(alba.getAl_gen())?"checked":"" %> />
							<label for="al_gen_m"  class="form-check-label">남</label>
				</div>			
				<div class="form-check form-check-inline">
					<input type="radio" name="al_gen" id="al_gen_f" value="F" class="form-check-input"
							<%="F".equals(alba.getAl_gen())?"checked":"" %> /><label for="al_gen_f" class="form-check-label">여</label>
				</div>
				<span class="error"><%=errors.get("al_gen")%></span></td>
			</tr>
			<tr>
				<th>혈액형</th>
				<td>
					<select name="al_btype" required  class="form-control col-6">
						<option value="A" <%="A".equals(alba.getAl_btype())?"selected":"" %>>A형</option>
						<option value="B" <%="B".equals(alba.getAl_btype())?"selected":"" %>>B형</option>
						<option value="AB" <%="AB".equals(alba.getAl_btype())?"selected":"" %>>AB형</option>
						<option value="O" <%="O".equals(alba.getAl_btype())?"selected":"" %>>O형</option>
					</select>
				<span class="error form-control-plaintext col-6"><%=errors.get("al_btype") %></span></div></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><div class="form-group form-inline"><input type="text" name="al_mail" required  class="form-control col-6"
					value="<%=alba.getAl_mail() %>" />
				<span class="error form-control-plaintext col-6"><%=errors.get("al_mail") %></span></div></td>
			</tr>
			<tr>
				<th>최종학력</th>
				<td><div class="form-group form-inline">
					<select name="gr_code"  class="form-control col-6">
						<%
							for(Map<String, String> map : grades){
								String gr_code = map.get("gr_code");
								String gr_name = map.get("gr_name");
								%>
								<option value="<%=gr_code%>" <%=gr_code.equals(alba.getGr_code())?"selected":"" %>><%=gr_name %></option>
								<%
							}
						%>
					</select>
					<script>
						$("[name='al_grade']").val("<%=alba.getGr_code() %>");
					</script>
				<span class="error form-control-plaintext col-6"><%=errors.get("gr_code")%></span></div></td>
			</tr>
			<tr>
				<th>자격증</th>
				<td><div class="form-group form-inline">
				<select name="lic_codes" multiple size="6"  class="form-control col-6">
					<%
						if(alba.getLic_codes()!=null) Arrays.sort(alba.getLic_codes());
						for(Map<String, String> map : licenses){
							String lic_code = map.get("lic_code");
							String lic_name = map.get("lic_name");
							%>
							<option value="<%=lic_code%>" <%=alba.getLicenses()!=null && alba.getLicenses().contains(lic_code) ?"selected":"" %>><%=lic_name %></option>
							<%
						}
					%>
				</select>
				<span class="error form-control-plaintext col-6"><%=errors.get("lic_codes")%></span></div></td>
			</tr>
			<tr>
				<th>특기사항</th>
				<td><div class="form-group form-inline"><textarea  name="al_spec"  class="form-control col-6"><%=alba.getAl_spec()%></textarea>
				<span class="error form-control-plaintext col-6"><%=errors.get("al_spec")%></span></div></td>
			</tr>
			<tr>
				<th>자기소개</th>
				<td><div class="form-group form-inline"><textarea  name="al_desc"  class="form-control col-6"><%=alba.getAl_desc()%></textarea>
				<span class="error form-control-plaintext col-6"><%=errors.get("al_desc")%></span></div></td>
			</tr>
			<tr>
				<th>경력사항</th>
				<td><div class="form-group form-inline"><textarea  name="al_career"  class="form-control col-6"><%=alba.getAl_career()%></textarea>
				<span class="error form-control-plaintext col-6"><%=errors.get("al_career")%></span></div></td>
			</tr>
			<tr>
			<td colspan="2">
				<button type="submit" class="btn btn-primary">등록</button>
				<button type="reset" class="btn btn-primary">취소</button>
				<button type="button" onclick="history.back();" class="btn btn-primary">뒤로가기</button>
			</td>
		</tr>
	</table>
</form>
</body>
</html>



















