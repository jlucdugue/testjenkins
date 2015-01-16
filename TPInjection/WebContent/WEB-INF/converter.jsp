<%@page import="fr.imie.DataGui"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post">
		<div>
			<input type="text" name="initialE" /> <input type="submit" value="=>" />
			<input type="text" value="${finalD}" />
		</div>
		<div>
			<input type="text" name="initialD" /> <input type="submit" value="=>" />
			<input type="text" value="${finalE}" />
		</div>
		<div>
			<ul>
			<%
				List<DataGui> list = (List<DataGui>) request.getAttribute("histo");
				for (DataGui dataGui : list) {
			%>
			<li>
			<%= dataGui.getInitialValue()%>-><%= dataGui.getFinalValue()%>
			</li>
			<%
				}
			%>
			</ul>
		</div>
	</form>
</body>
</html>