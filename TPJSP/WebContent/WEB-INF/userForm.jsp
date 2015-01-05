<%@page import="fr.imie.UserDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<link href="./uikit-2.15.0/css/uikit.gradient.min.css" rel="stylesheet"
	type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		UserDTO userDTO = (UserDTO) request.getAttribute("user");
	%>
	<div
		class="uk-panel uk-panel-box uk-panel-header uk-width-1-2 uk-container-center uk-panel-box-secondary">
		<form class="uk-form uk-form-horizontal">
			<div class="uk-form-row">
				<label for="loginInput" class="uk-form-label">login</label>
				<div class="uk-form-controls">
					<input type="text" id="loginInput" value="<%=userDTO.getLogin()%>" />
				</div>
			</div>
			<div class="uk-form-row">
				<label for="passwordInput" class="uk-form-label">password</label>
				<div class="uk-form-controls">
					<input type="password" id="passwordInput"
						value="<%=userDTO.getPassword()%>" />
				</div>
			</div>
		</form>
	</div>

</body>
</html>