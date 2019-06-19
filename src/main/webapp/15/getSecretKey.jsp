<%@page import="kr.or.ddit.utils.EncryptUtils"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Date"%>
<%@page import="javax.crypto.SecretKey"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//대칭키(비밀키) 생성
	SecretKey key = EncryptUtils.generate128bitSecretKey();
	//initialize vector 생성
	byte[] iv = EncryptUtils.generate128Iv(new Date().toString());
	//json 데이터 생성
	Map<String, String> jsonMap = new HashMap<>();
	//jsonMap에 담기 위해(String) Base64로 encoding처리
	String encodedKey = EncryptUtils.encodingBase64(key.getEncoded());
	String encodedIv = EncryptUtils.encodingBase64(iv);
	
	jsonMap.put("encodedKey", encodedKey);
	jsonMap.put("encodedIv", encodedIv);
	
	session.setAttribute("secretKey", key);
	session.setAttribute("iv", iv);
	
	//marshalling
	ObjectMapper mapper = new ObjectMapper();
	String json = mapper.writeValueAsString(jsonMap);
%>
<%=json %>