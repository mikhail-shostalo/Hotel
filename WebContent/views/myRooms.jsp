<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="text" var="rb" />
<h2></h2>
<table class="table table-striped">
	<tr>
		<th><fmt:message key="request.roomClass" bundle="${rb}" /></th>
		<th><fmt:message key="request.peopleNumber" bundle="${rb}" /></th>
		<th><fmt:message key="reserve.arrivalDate" bundle="${rb}" /></th>
		<th><fmt:message key="reserve.depDate" bundle="${rb}" /></th>
		<th><fmt:message key="request.price" bundle="${rb}" /></th>
	</tr>
	<c:forEach items="${reserves}" var="item">
		<tr>
			<td>${item.room.classRoom.roomClass}</td>
			<td>${item.room.classRoom.numberOfPlace}</td>
			<td>${item.arrivalDate}</td>
			<td>${item.dateOfDepartures}</td>
			<td>${item.price}</td>
		</tr>
	</c:forEach>
</table>