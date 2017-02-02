<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="text" var="rb" />
<div class="row row-offcanvas row-offcanvas-right">
	<c:forEach items="${reserves}" var="item">
		<div class="col-xs-12 col-sm-9">
			<div class="thumbnail">
				<div class="caption">
					<form action=<c:url value="/account" /> method="post">
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
						<input type="hidden" name="idReserve" value="${item.id}" /> <input
							type="hidden" name="arrival" value="${item.arrivalDate}" /> <input
							type="hidden" name="depature" value="${item.dateOfDepartures}" />
						<input type="hidden" name="idRoom" value="${item.room.id}" /> <input
							type="submit" class="btn btn-success"
							value=<fmt:message key="reserve.pay" bundle="${rb}" /> />
					</form>
					<br />
				</div>
			</div>
		</div>
	</c:forEach>
</div>