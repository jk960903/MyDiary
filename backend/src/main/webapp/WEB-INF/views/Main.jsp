<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file ="shared/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>메인페이지</title>
</head>
<body>
	<div id ="map" style="width:100%;height:400px;"></div>
	<div class="search">
		<input id="address" type="text" placeholder="검색할 주소" value="불정로 6">
		<input id="submit" type ="button" value="주소검색">
	</div>
    <script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=p41uq6jhpp"></script>
    <script type="text/javascript">
        function initMap() {
            map = new naver.maps.Map('map', {
                center: new naver.maps.LatLng(37.3595704, 127.105399),
                zoom: 10
            });
            
        }
        
        var map = new naver.maps.Map('map',initMap);
        
        var infoWindow = new naver.maps.InfoWindow({
        	anchorSkew: true
        })
        
        map.setCursot('pointer');
        
        
    </script>
</body>
</html>