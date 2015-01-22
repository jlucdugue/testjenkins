<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TP JPA</title>

<link href="Semantic-UI-1.7.3/dist/semantic.min.css" rel="stylesheet"
	type="text/css" />
<%--
<script type="text/javascript"
	src="Semantic-UI-1.7.3/dist/semantic.min.js">
</script>--%>
</head>
<body>
	<header>
		<div class="ui fixed main inverted menu">
			<div class="container">
				<div class="item">TP JPA</div>
			</div>
		</div>
	</header>
	<section style="">
		<div style="display: flex; width: 100%; padding-top: 35px;">

			<div style="flex-basis: 150px;"></div>
			<div style="flex: 1;">
				<table class="ui table celled striped ">
					<tr>
						<th>nom</th>
						<th>prenom</th>
						<th>promotion</th>
						<th>action</th>
					</tr>
					<c:forEach items="${persons}" var="person">
						<tr>
							<td><c:out value="${person.nom}" /></td>
							<td><c:out value="${person.prenom}" /></td>
							<td><c:out value="${person.promotion.libelle}" /></td>
							<td class="right aligned collapsing"><a
								href="PersonForm?id=${person.id}"><i class="write icon"></i></a>
								<a href="PersonDelete?id=${person.id}"><i
									class="remove icon"></i></a></td>
						</tr>
					</c:forEach>
				</table>
				<a href="PersonForm"><i class="plus icon"></i></a>
			</div>
			<div style="flex-basis: 150px;"></div>
		</div>
	</section>
</body>
</html>