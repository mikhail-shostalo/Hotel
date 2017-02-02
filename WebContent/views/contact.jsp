<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>\
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="text" var="rb" />
<div class="inner cover">
	<h1 class="cover-heading">
		<fmt:message key="contact.h1" bundle="${rb}" />
	</h1>
	<p class="lead">+38 (057) 705 -37 -75</p>
	<hr>
	<h1 class="cover-heading">
		<fmt:message key="contact.h2" bundle="${rb}" />
	</h1>
	<p class="lead">+38 (057) 705 -36 -36, +38 (057) 705 -37 -71</p>
	<hr>
	<h1 class="cover-heading">Email</h1>
	<p class="lead">reception@grandHotel.com</p>
	<hr>
</div>