<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="./uikit-2.15.0/css/uikit.gradient.min.css" rel="stylesheet"
	type="text/css" />
</head>
<body>
	<div
		class="uk-panel uk-panel-box uk-panel-header uk-width-1-2 uk-container-center uk-panel-box-secondary">
		<form class="uk-form uk-form-horizontal" method="post">
			<h3 class="uk-panel-title">Login</h3>
			<div class="uk-form-row">
				<label for="loginInput" class="uk-form-label">login</label>
				<div class="uk-form-controls">
					<input type="text" id="loginInput" name="loginInput"/>
				</div>
			</div>
			<div class="uk-form-row">
				<label for="passwordInput" class="uk-form-label">password</label>
				<div class="uk-form-controls">
					<input type="password" id="passwordInput" name="passwordInput"/>
				</div>
			</div>
			<input type="submit" value="login"/>
		</form>
	</div>
</body>
</html>