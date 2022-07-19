<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
	  text-align: left;
	  background-color: #04AA6D;
	  color: white;
	}
</style>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
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
			<tr>
				<td colspan=17 style="text-align:center">위치 정보를 입력한 후에 조회해 주세요.</td>
			</tr>
		</tbody>
	</table>
</body>
</html>