<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
<script type="text/javascript">
	function getUserLocation() {
	    if(navigator.geolocation) {
	    	navigator.geolocation.getCurrentPosition (function(pos) {
	    		document.getElementById('latitude').value = pos.coords.latitude;
	    		document.getElementById('longitude').value = pos.coords.longitude;
	    	});
	    } else {
	    	alert("이 브라우저에서는 Geolocation이 지원되지 않습니다.");
	    }
	}
</script>
</head>
<body>
	<h1>와이파이 정보 구하기</h1>
	<a href="index.jsp">홈</a> | <a>위치 히스토리 목록</a> | <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
	<br>
	LAT: <input type="text" id="latitude">,
	LNT: <input type="text" id="longitude">
	<button onclick="getUserLocation();">내 위치 가져오기</button>
	<button>근처 WIFI 정보 보기</button>
</body>
</html>