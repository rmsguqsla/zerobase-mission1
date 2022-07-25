<%@page import="db.History"%>
<%@page import="java.util.List"%>
<%@page import="db.HistoryService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
	#history {
	  font-family: Arial, Helvetica, sans-serif;
	  border-collapse: collapse;
	  width: 100%;
	}
	
	#history td, #history th {
	  border: 1px solid #ddd;
	  padding: 8px;
	}
	
	#history tr:nth-child(even){background-color: #f2f2f2;}
	
	#history tr:hover {background-color: #ddd;}
	
	#history th {
	  padding-top: 12px;
	  padding-bottom: 12px;
	  text-align: center;
	  background-color: #04AA6D;
	  color: white;
	}
</style>
<title>와이파이 정보 구하기</title>
</head>
<body>
	<h1>위치 히스토리 목록</h1>
	<a href="index.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
	<br>
	<table id="history">
		<thead>
			<tr>
				<th>ID</th>
				<th>X좌표</th>
				<th>Y좌표</th>
				<th>조회일자</th>
				<th>비고</th>
			</tr>
		</thead>
		<tbody>
			<%
				HistoryService historyService = new HistoryService();
			
				String historyId = request.getParameter("historyId");
			
				if (historyId != null) {
					historyService.historyDelete(historyId);
				}

				List<History> list = historyService.historyList();
				
				for (History history : list) {
			%>
					<tr>
						<td><%=history.getId() %></td>
						<td><%=history.getLat() %></td>
						<td><%=history.getLnt() %></td>
						<td><%=history.getInquiryDate() %></td>
						<td style="display: block;">
							<form action = "history.jsp" method = "post">
								<input type="hidden" id="historyId" name="historyId" value=<%=history.getId() %>>
								<input type="submit" value="삭제" style="margin:0 auto; display: block;">
							</form>
						</td>
					</tr>
			<%
				}
			%>
		</tbody>
	</table>
</body>
</html>