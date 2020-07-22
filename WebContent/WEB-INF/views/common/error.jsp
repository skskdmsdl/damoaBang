<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<%
	//isErrorPage = "true"
	String errorMessage = exception != null? exception.getMessage() : String.valueOf(response.getStatus());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오류 페이지</title>
</head>
<body style="text-align: center;">
	<h1>오류페이지</h1>
	<p>로그인을 해주세요!</p>
	<a href="#" onClick="javascript:history.back()" >뒤로가기</a><br>
</body >
</html>