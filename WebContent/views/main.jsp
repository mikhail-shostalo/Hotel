<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row row-offcanvas row-offcanvas-right">
	<c:forEach items="${requests}" var="item">
		<div class="col-xs-12 col-sm-9">
			<div class="thumbnail">
				<div class="caption">
					<form action=<c:url value="/roomManagment" /> method="get">
						<input type="hidden" value="${item.id }" name="RequestId" /> <input
							type="hidden" value="${item.login }" name="login" />
						<h3>Логин клиента: ${item.login}</h3>
						<p>Класс комнаты: ${item.classRoom.roomClass}</p>
						<p>Количество людей: ${item.numberOfPlace}</p>
						<p>Дата поселения: ${item.arrivalDate}</p>
						<p>Дата выселения: ${item.dateOfDeparture}</p>
						<input type="submit" class="btn btn-success" value="Выбрать номер" />
					</form>
				</div>
			</div>
		</div>
	</c:forEach>
</div>