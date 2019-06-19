<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${'ROLE_ADMIN' eq authMember.mem_auth }">
<a class="nav-link text-white" href="<%=request.getContextPath()%>/member/memberList.do">회원관리</a>
<a class="nav-link text-white" href="<%=request.getContextPath()%>/alba/albaList.do">알바생관리</a>
<a class="nav-link text-white">거래처관리</a>
</c:if>
<a class="nav-link text-white" href="<%=request.getContextPath()%>/prod/prodList.do">상품관리</a>
<a class="nav-link text-white" href="<%=request.getContextPath()%>/board/boardList.do">자유게시판</a>
<a class="nav-link text-white">방명록</a>
