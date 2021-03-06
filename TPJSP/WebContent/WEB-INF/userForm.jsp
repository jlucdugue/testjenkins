<%@ page import="fr.imie.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:template>
	<div
		class="uk-panel uk-panel-box uk-panel-header uk-width-1-2 uk-container-center uk-panel-box-secondary">
		<form class="uk-form uk-form-horizontal" method="post">
			<input type="hidden" value="${user.id}" name="id">
			<h3 class="uk-panel-title">Formulaire</h3>
			<div class="uk-form-row">
				<label for="loginInput" class="uk-form-label">login</label>
				<div class="uk-form-controls">
					<input type="text" id="loginInput" value="${user.nom}"
						name="login" />
				</div>
			</div>
			<div class="uk-form-row">
				<label for="passwordInput" class="uk-form-label">password</label>
				<div class="uk-form-controls">
					<input type="password" id="passwordInput" value="${user.password}"
						name="password" />
				</div>
			</div>
			<div class="uk-form-row">
			<c:url value="UserList" var="listeURL" ></c:url>
			<a class="uk-button" href="${listeURL}" >retour</a>
			<c:choose>
				<c:when test="${empty user}">
					<button type="submit" name="create" class="uk-button">créer</button>
				</c:when>
				<c:otherwise>
					<button type="submit" name="update" class="uk-button">modifier</button>
				</c:otherwise>
			</c:choose>
			</div>
		</form>
	</div>
</t:template>
