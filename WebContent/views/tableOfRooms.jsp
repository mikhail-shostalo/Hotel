<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="text" var="rb" />
<h2></h2>
<form class="form-inline" role="form" action=<c:url value="/bookNow" />
	method="get">
	<div class="form-group col-xs-7">
		<label class="control-label" for="inputSuccess4"><fmt:message
				key="sort.header" bundle="${rb}" /></label> <select class="form-control"
			id="sort" name="sort">
			<option value="price"><fmt:message key="sort.byPrice"
					bundle="${rb}" /></option>
			<option value="places"><fmt:message key="sort.byPlaces"
					bundle="${rb}" /></option>
			<option value="class"><fmt:message key="sort.byClass"
					bundle="${rb}" /></option>
			<option value="status"><fmt:message key="sort.byStatus"
					bundle="${rb}" /></option>
		</select> <input type="submit"
			value=<fmt:message
				key="sort.apply" bundle="${rb}" />
			class="btn btn-default" />
	</div>
</form>
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
			<td>${item.classRoom.price}</td>
			<c:choose>
				<c:when
					test="${item.status == 'free' || item.status == 'свободный' }">
					<form action=<c:url value="/booking" /> method="get">
						<input type="hidden" value="${item.id }" name="idRoom" />
						<td><input type="submit" class="btn btn-success"
							value=<fmt:message key="user.bookNow" bundle="${rb}" /> /></td>
					</form>
				</c:when>
			</c:choose>
		</tr>
	</c:forEach>
</table>