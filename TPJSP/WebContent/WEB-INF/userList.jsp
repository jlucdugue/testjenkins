<%@ page import="fr.imie.UserDTO"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>
<html>
<head>
<link href="./uikit-2.15.0/css/uikit.gradient.min.css" rel="stylesheet"
	type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div
		class="uk-panel uk-panel-box uk-panel-header uk-width-1-2 uk-container-center uk-panel-box-secondary">
		<table class="uk-table uk-table-hover uk-table-striped ">
			<tr>
				<th>login</th>
			</tr>
			<c:forEach items="${users}" var="user">
				<tr>
					<td><a href="UserForm?id=${user.id}">
							<div>
								<c:out value="${user.login }"></c:out>
							</div>
					</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>