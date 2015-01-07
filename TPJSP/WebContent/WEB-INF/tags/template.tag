<%@ tag language="java" description="application template"
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
	
		<header class="uk-panel uk-panel-box uk-panel-box-secondary uk-position-up">
			<jsp:include page="header.jsp"></jsp:include>
		</header>
		<section>
			<jsp:doBody></jsp:doBody>
		</section>
		<footer class="uk-panel uk-panel-box uk-panel-box-secondary uk-position-bottom uk-width-1-1">
			<div class="uk-text-center">
				<span >TP réulisé dans le cadre des cours de
					l'IMIE</span>
			</div>
		</footer>
	
</body>
</html>