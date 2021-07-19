<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>로그인</title>
</head>
<body>
	<Form action = "/member/Login" method ="get">
		ID : <input type ="text" name = "userID"><br>
		PASSWORD : <input type ="text" name = "password"><br>
		<input type ="checkbox" name = "autologin" value="1">자동로그인<br>
		<input type ="submit" value = "로그인">
	</Form>
	<a href ="FindID">아이디찾기</a>
	<a href ="FindPassWord">비밀번호 찾기</a>
</body>
</html>