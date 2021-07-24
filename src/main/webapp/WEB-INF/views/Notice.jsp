<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file ="shared/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>공지사항</title>
</head>
<body>
	<table id='NoticeTable' border="1">
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>등록일</td>
			<td>조회수</td>
		</tr>
		
	</table>
	<input type='text' id='search' placeholder='검색어 입력'>
	<input type='search' id='search'/>
	<script>
	$(document).ready(function(){
		$.ajax({
			type:'get',
			url: '/Notice/NoticeGet',
			data:{
				'email' : ''
			},
			success: function(get){
				
			}
			error: function(data){
				
			}
		})
	})
	$("#search").click(function(){
		var search = document.getElementbyId('search').value;
		$.ajax({
			type:'get',
			url:'/Notice/NoticeGet',
			data:{
				'search' : search
			}
			success: function(get){
				
			}
			error: function(data){
				
			}
		})
	})
	</script>
</body>
</html>