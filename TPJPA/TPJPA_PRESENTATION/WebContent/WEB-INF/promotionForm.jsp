<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link href="Semantic-UI-1.7.3/dist/semantic.min.css" rel="stylesheet"
	type="text/css" />

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
			<div classe="ui main container">
				<form class="ui form" method="post">
					<input type="hidden" value="${promotion.id}">
					<div class="field">
						<label for="nomInput"> nom </label> <input id="nomInput"
							type="text" name="lastName" value="${promotion.libelle}">
					</div>
					<div class="field">
						<table>

							<c:forEach items="${promotion.personnes}" var="personne">
								<tr>
									<td><c:out value="${personne.nom }"></c:out></td>
									<td><c:out value="${personne.prenom }"></c:out></td>
								</tr>
							</c:forEach>

						</table>
					</div>

					<c:choose>
						<c:when test="${! empty personne}">
							<button class="ui button icon" type="submit">
								<i class="big write icon"></i>
							</button>
						</c:when>
						<c:otherwise>
							<button class="ui button icon" type="submit">
								<i class="big plus icon"></i>
							</button>
						</c:otherwise>
					</c:choose>
				</form>
			</div>
		</div>
		<div style="flex-basis: 150px;"></div>
	</div>
	</section>

</body>
</html>