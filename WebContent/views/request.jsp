<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="text" var="rb" />
<script type="text/javascript">
    $(function() {
	$("#datetimepicker8").datetimepicker({
	    pickTime : false,
	    minDate : new Date(),
	    format : 'YYYY-MM-DD'
	});
	$("#datetimepicker9").datetimepicker({
	    pickTime : false,
	    format : 'YYYY-MM-DD'
	});
	$("#datetimepicker8").on("dp.change", function(e) {
	    $("#datetimepicker9").data("DateTimePicker").setMinDate(e.date);
	    $("#datetimepicker9").data("DateTimePicker").setValue(e.date);
	});
    });
</script>
<script>
    function Validator() {
	var flag = true;
	var date1 = document.getElementById("dateArrive").value;
	var date2 = document.getElementById("depDate").value;
	if (date1 == "" || date2 == "") {
	    flag = false;
	}
	return flag;
    }
</script>
<h2>
	<fmt:message key="user.request" bundle="${rb}" />
</h2>
<c:if test="${not empty param.status }">
	<h3 class="text-success">
		<fmt:message key="request.ok" bundle="${rb}" />
	</h3>
</c:if>
<form class="form-horizontal" method="post"
	action=<c:url value="/request"/> onsubmit="return Validator()">
	<div class='col-md-5'>
		<div class="form-group">
			<label class="control-label col-xs-5" for="dateArrive"><fmt:message
					key="request.arrivalDate" bundle="${rb}" />: </label>
			<div class='input-group date' id='datetimepicker8'>
				<input type="text" id="dateArrive" name="dateArrive"
					class="form-control" readonly="readonly" required /> <span
					class="input-group-addon"> <span
					class="glyphicon glyphicon-calendar"></span>
				</span>
			</div>
		</div>
	</div>
	<div class='col-md-5'>
		<div class="form-group">
			<label class="control-label col-xs-5" for="depDate"><fmt:message
					key="request.depDate" bundle="${rb}" />: </label>
			<div class='input-group date' id='datetimepicker9'>
				<input type="text" id="depDate" name="depDate" class="form-control"
					readonly="readonly" required /> <span class="input-group-addon">
					<span class="glyphicon glyphicon-calendar"></span>
				</span>
			</div>
		</div>
	</div>
	<div class='col-md-5'>
		<div class="form-group" id="numberOfPeople">
			<label class="control-label col-xs-5" for="numberOfPeople"><fmt:message
					key="request.peopleNumber" bundle="${rb}" />:</label>
			<div class="col-xs-3">
				<select class="form-control" id="numberOfPeople"
					name="numberOfPeople">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
				</select>
			</div>
		</div>
	</div>
	<div class="form-group" id="roomClass">
		<div class='col-md-5'>
			<label class="control-label col-xs-5" for="roomClass"><fmt:message
					key="request.roomClass" bundle="${rb}" />:</label>
			<div class="col-xs-5">
				<select class="form-control" id="roomClass" name="roomClass">
					<c:forEach var="item" items="${classes }">
						<option value="${item.id }">${item.roomClass}</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</div>
	<br />
	<div class="form-group">
		<div class="col-xs-offset-9 col-xs-3">
			<input type="submit" class="btn btn-primary"
				value=<fmt:message
					key="request.sendReq" bundle="${rb}" /> />
		</div>
	</div>
</form>