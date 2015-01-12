<%@ page import="fr.imie.UserDTO"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:template>
<div
		class="uk-panel uk-panel-box uk-panel-header uk-width-1-2 uk-container-center uk-panel-box-secondary">
		<table class="uk-table uk-table-hover uk-table-striped ">
			<tr>
				<th>login</th>
				<th></th>
			</tr>
			<c:forEach items="${users}" var="user">
				<tr>
					<td class="uk-width-6-10"><c:out value="${user.nom}"></c:out>						
					<td class="uk-width-4-10">
						<div style="display: inline-block;">
							<c:url value="UserForm" var="userEditURL">
								<c:param name="id" value="${user.id}">
								</c:param>
							</c:url>
							<a href="${userEditURL}" class="uk-button">éditer</a>
						</div>
						<div style="display: inline-block;">
							<form method="post">
								<input type="hidden" value="${user.id}" name="userId" />
								<button type="submit" class="uk-button">supprimer</button>
							</form>
						</div>
					</td>
				</tr>

			</c:forEach>
		</table>

		<c:url value="UserForm" var="userCreateURL">
			<c:param name="id" value="create">
			</c:param>
		</c:url>
		<a href="${userCreateURL}" class="uk-button">créer</a>

	</div>
</t:template>
