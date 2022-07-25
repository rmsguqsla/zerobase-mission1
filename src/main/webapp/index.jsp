<%@page import="db.HistoryService"%>
<%@page import="db.Wifi"%>
<%@page import="java.util.List"%>
<%@page import="db.WifiService"%>
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
<style>
	#wifi {
	  font-family: Arial, Helvetica, sans-serif;
	  border-collapse: collapse;
	  width: 100%;
	}
	
	#wifi td, #wifi th {
	  border: 1px solid #ddd;
	  padding: 8px;
	}
	
	#wifi tr:nth-child(even){background-color: #f2f2f2;}
	
	#wifi tr:hover {background-color: #ddd;}
	
	#wifi th {
	  padding-top: 12px;
	  padding-bottom: 12px;
	  text-align: center;
	  background-color: #04AA6D;
	  color: white;
	}
</style>
</head>
<body>
	<%
		WifiService wifiService = new WifiService();
		String latitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitude");
		double lat = 0;
		double lnt = 0;
	%>
	<h1>와이파이 정보 구하기</h1>
	<a href="index.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
	<br>
	<form action="index.jsp" method="get">
		<%
			if(latitude != null && longitude != null) {
				lat = Double.parseDouble(latitude);
				lnt = Double.parseDouble(longitude);
			}
		%>
		LAT: <input type="text" id="latitude" name="latitude" value=<%=lat%>>,
		LNT: <input type="text" id="longitude" name="longitude" value=<%=lnt%>>
		<input type="button" onclick="getUserLocation();" value="내 위치 가져오기">
		<input type="submit" value="근처 WIFI 정보 보기">
	</form>
	<br><br>
	<table id="wifi">
		<thead>
			<tr>
				<th>거리(Km)</th>
				<th>관리번호</th>
				<th>자치구</th>
				<th>와아파이명</th>
				<th>도로명주소</th>
				<th>상세주소</th>
				<th>설치위치(층)</th>
				<th>설치유형</th>
				<th>설치기관</th>
				<th>서비스구분</th>
				<th>망종류</th>
				<th>설치년도</th>
				<th>실내외구분</th>
				<th>WIFI접속환경</th>
				<th>X좌표</th>
				<th>Y좌표</th>
				<th>작업일자</th>
			</tr>
		</thead>
		<tbody>
			<%
				if(lat == 0 || lnt == 0) {
			%>
					<tr>
						<td colspan=17 style="text-align:center">위치 정보를 입력한 후에 조회해 주세요.</td>
					</tr>
			<%
				} else {
					HistoryService historyService = new HistoryService();
					historyService.insertHistory(latitude, longitude);
					List<Wifi> list = wifiService.selectWifi(latitude, longitude);
					for(Wifi wifi : list) {
			%>
						<tr>
							<td><%=wifi.getKM() %></td>
							<td><%=wifi.getX_SWIFI_MGR_NO() %></td>
							<td><%=wifi.getX_SWIFI_WRDOFC() %></td>
							<td><%=wifi.getX_SWIFI_MAIN_NM() %></td>
							<td><%=wifi.getX_SWIFI_ADRES1() %></td>
							<td><%=wifi.getX_SWIFI_ADRES2() %></td>
							<td><%=wifi.getX_SWIFI_INSTL_FLOOR() %></td>
							<td><%=wifi.getX_SWIFI_INSTL_TY() %></td>
							<td><%=wifi.getX_SWIFI_INSTL_MBY() %></td>
							<td><%=wifi.getX_SWIFI_SVC_SE() %></td>
							<td><%=wifi.getX_SWIFI_CMCWR() %></td>
							<td><%=wifi.getX_SWIFI_CNSTC_YEAR() %></td>
							<td><%=wifi.getX_SWIFI_INOUT_DOOR() %></td>
							<td><%=wifi.getX_SWIFI_REMARS3() %></td>
							<td><%=wifi.getLAT() %></td>
							<td><%=wifi.getLNT() %></td>
							<td><%=wifi.getWORK_DTTM() %></td>
						</tr>
			<%
					}
				}
			%>
		</tbody>
	</table>
</body>
</html>