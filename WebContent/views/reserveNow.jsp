<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="text" var="rb" />
<script type="text/javascript">
    function doPriceByDate() {
	$.ajax({
	    type : "POST",
	    url : '<c:url value="/dateEdit"/>',
	    data : {
		date1 : $("#dateArrive").val(),
		date2 : $("#depDate").val(),
		price : $("#p1").val()
	    },
	    success : function(msg) {
		$("#price").text(msg + ' UAH');
	    }
	})
    }

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
<form class="form-horizontal" method="post"
	action=<c:url value="/reserve"/> onsubmit="return Validator()">
	<div class='col-md-5'>
		<div class="form-group">
			<label class="control-label col-xs-5" for="dateArrive"><fmt:message
					key="request.arrivalDate" bundle="${rb}" />: </label>
			<div class='input-group date' id='datetimepicker8'
				onchange="doPriceByDate()">
				<input type="text" id="dateArrive" name="arrival"
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
			<div class='input-group date' id='datetimepicker9'
				onchange="doPriceByDate()">
				<input type="text" id="depDate" name="depature" class="form-control"
					readonly="readonly" required onchange="doPriceByDate()" /> <span
					class="input-group-addon"> <span
					class="glyphicon glyphicon-calendar"></span>
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
					<c:forEach var="item" begin="1"
						end="${room.classRoom.numberOfPlace }">
						<option value="${item }">${item }</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</div>
	<input type="hidden" value="${room.classRoom.price}" id="p1" /> <input
		type="hidden" value="${room.id}" name="idRoom" />
	<div class='col-md-5'>
		<div class="form-group" id="numberOfPeople">
			<label class="control-label col-xs-5" for="price"><fmt:message
					key="request.price" bundle="${rb}" />:</label>
			<div class="col-xs-5">
				<h3 id="price">${room.classRoom.price}UAH</h3>
			</div>
		</div>
	</div>
	<br />
	<div class="form-group">
		<div class="col-xs-offset-9 col-xs-3">
			<input type="submit" class="btn btn-primary"
				value=<fmt:message
					key="user.bookNow" bundle="${rb}" /> />
		</div>
	</div>
</form>