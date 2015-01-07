<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<span>Welcome <c:out value="${connectedUser.login}"></c:out> </span><a class="uk-button" href="Deconnect">Deconnecter</a>