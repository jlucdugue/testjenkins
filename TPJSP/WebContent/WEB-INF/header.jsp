<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="uk-clearfix">

<div class="uk-align-medium-right"">
<a class="uk-button" href="Deconnect">Deconnecter</a>
</div>
<div class="">
<span>Welcome <c:out value="${connectedUser.login}"></c:out> </span>
</div>
</div>