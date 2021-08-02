<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<Form action = "member/MakeAccount" method ="post">
		ID : <input type ="text" name = "ID">
		PASSWORD : <input type ="text" name = "pwd"><br>
		Email : <input type = "text" name = "Email"><br>
		phone : <input type = "text" name = "phone"><br>
		name : <input type = "text" name = "name"><br>
		<input type='radio' name ='sex' value='1'>남성<br>
		<input type='radio' name ='sex' value='2'>여성<br>
		<input type ="submit" value = "로그인">
	</Form>
</body>
</html>