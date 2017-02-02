<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="text" var="rb" />
<form class="form-signin" role="form" action=<c:url value="/logIn" />
	method="post">
	<h2 class="form-signin-heading">
		<fmt:message key="signIn.header" bundle="${rb}" />
	</h2>
	<c:if test="${not empty param.message }">
		<h3 class="text-danger">
			<fmt:message key="signIn.message" bundle="${rb}" />
		</h3>
	</c:if>
	<input type="text" class="form-control" name="login"
		placeholder=<fmt:message key="signIn.login" bundle="${rb}" /> required
		autofocus> <input type="password" class="form-control"
		name="inputPassword"
		placeholder=<fmt:message key="signIn.password" bundle="${rb}" />
		required>
	<button class="btn btn-lg btn-primary btn-block" type="submit">
		<fmt:message key="header.signIn" bundle="${rb}" />
	</button>
</form>