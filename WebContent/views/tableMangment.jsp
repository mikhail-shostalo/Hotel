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
		<th><fmt:message key="request.status" bundle="${rb}" /></th>
		<th><fmt:message key="request.price" bundle="${rb}" /></th>
	</tr>
	<c:forEach items="${rooms}" var="item">
		<tr>
			<td>${item.classRoom.roomClass}</td>
			<td>${item.classRoom.numberOfPlace}</td>
			<td>${item.status}</td>
			<td>${item.classRoom.price*days}</td>
			<c:choose>
				<c:when
					test="${item.status == 'free' || item.status == 'свободный' }">
					<form action=<c:url value="/roomManagment" /> method="post">
						<input type="hidden" name="idRoom" value="${item.id}" /> <input
							type="hidden" name="priceRoom"
							value="${item.classRoom.price*days}" /> <input type="hidden"
							name="login" value="${login}" /> <input type="hidden"
							name="arrival" value="${arrivalDate}" /> <input type="hidden"
							name="depature" value="${depatureDate}" />
						<td><input type="submit" class="btn btn-warning"
							value=<fmt:message key="request.sendReq" bundle="${rb}" /> /></td>
					</form>
				</c:when>
			</c:choose>
		</tr>
	</c:forEach>
</table>