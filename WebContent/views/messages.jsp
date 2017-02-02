<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="text" var="rb" />
<div class="row row-offcanvas row-offcanvas-right">
	<c:forEach items="${answers}" var="item">
		<div class="col-xs-12 col-sm-9">
			<div class="thumbnail">
				<div class="caption">
					<form action=<c:url value="/reserve" /> method="post">
						<h3>
							<fmt:message key="message.header" bundle="${rb}" />
						</h3>
						<p>
							<fmt:message key="request.roomClass" bundle="${rb}" />
							: ${item.room.classRoom.roomClass}
						</p>
						<p>
							<fmt:message key="request.price" bundle="${rb}" />
							: ${item.price} UAH
						</p>
						<input type="hidden" name="idAnswer" value="${item.id}" /> <input
							type="hidden" name="arrival" value="${item.arrivalDate}" /> <input
							type="hidden" name="depature" value="${item.dateOfDeparture}" />
						<input type="hidden" name="idRoom" value="${item.room.id}" /> <input
							type="submit" class="btn btn-success"
							value=<fmt:message key="message.confirm" bundle="${rb}" /> />
					</form>
					<br />
					<form action=<c:url value="/refuse" /> method="post">
						<input type="submit" class="btn btn-danger"
							value=<fmt:message key="user.refuse" bundle="${rb}" /> /> <input
							type="hidden" value="${item.id}" name="idAnswer" />
					</form>
				</div>
			</div>
		</div>
	</c:forEach>
</div>