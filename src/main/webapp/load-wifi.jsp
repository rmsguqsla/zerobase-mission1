<%@page import="db.WifiService"%>
<%@page import="api.OpenApi" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
</head>
<body>
	<div style="text-align:center">
	<%
		WifiService wifiService = new WifiService();
		OpenApi openApi = new OpenApi();
		int listTotalCount = openApi.getListTotalCount();
		
		if (wifiService.insertApi()) {
	%>
			<h1><%=listTotalCount %>개의 WIFI 정보를 정상적으로 저장하였습니다.</h1>
	<%
		} else {
	%>
			<h1>WIFI 정보가 이미 저장되있거나 저장하는데 실패하였습니다.</h1>
	<%
		}
	%>
		<a href="index.jsp">홈으로 가기</a>
	</div>
</body>
</html>