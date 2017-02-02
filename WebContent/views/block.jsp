<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="text" var="rb" />
<div class="jumbotron">
	<div class="container">
		<h1>Grand Hotel</h1>
		<p>
			<fmt:message key="block.text" bundle="${rb}" />
		</p>
		<p>
			<img src=<c:url value="images/header.jpg"/> alt="hotel"
				class="img-thumbnail" />
		</p>
	</div>
</div>