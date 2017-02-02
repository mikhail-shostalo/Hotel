<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="text" var="rb" />
<script type="text/javascript">
    function doAjax(item) {
	$
		.ajax({
		    type : "POST",
		    url : '<c:url value="/loginExist"/>',
		    data : {
			login : item
		    },
		    success : function(msg) {
			if (msg == "Error") {
			    $("#isLogin")
				    .text(
					    "<fmt:message
			key='signUp.error' bundle='${rb}' />");
			    $("#log-in").attr("class", "form-group has-error");
			} else {
			    $("#isLogin").text("");
			    $("#log-in")
				    .attr("class", "form-group has-success");
			}
		    }
		})
    }
</script>
<script>
    function Validator() {
	var flag = true;
	var regName = new RegExp("^[А-ЯA-Z][а-яёa-z]+");
	var regPassword = new RegExp("[A-Za-z0-9_]{6,}");
	var regPhone = new RegExp("[0-9]{3}-[0-9]{3}-[0-9]{2}-[0-9]{2}");
	var lastName = document.getElementById("lastName").value;
	var name = document.getElementById("firstName").value;
	var password = document.getElementById("inputPassword").value;
	var passwordRetry = document.getElementById("confirmPassword").value;
	var phone = document.getElementById("mobilePhone").value;
	if (!regName.test(lastName)) {
	    document.getElementById("ln").className = "form-group has-error";
	    flag = false;
	} else {
	    document.getElementById("ln").className = "form-group";
	}
	if (!regName.test(name)) {
	    document.getElementById("n").className = "form-group has-error";
	    flag = false;
	} else {
	    document.getElementById("n").className = "form-group";
	}
	if (!regPhone.test(phone)) {
	    document.getElementById("phone").className = "form-group has-error";
	    flag = false;
	} else {
	    document.getElementById("phone").className = "form-group";
	}
	if (!regPassword.test(password)) {
	    document.getElementById("passw").className = "form-group has-error";
	    flag = false;
	} else {
	    document.getElementById("passw").className = "form-group";
	}
	if (password != passwordRetry) {
	    document.getElementById("passw1").className = "form-group has-error";
	    flag = false;
	} else {
	    document.getElementById("passw1").className = "form-group";
	}
	return flag;
    }
</script>

<h2>
	<fmt:message key="signUp.header" bundle="${rb}" />
</h2>
<form class="form-horizontal" method="post"
	action=<c:url value="/registration"/> onsubmit="return Validator()">
	<div class="form-group" id="ln">
		<label class="control-label col-xs-3" for="lastName"><fmt:message
				key="signUp.lastName" bundle="${rb}" />:</label>
		<div class="col-xs-9">
			<input type="text" class="form-control" id="lastName" name="lastName"
				placeholder=<fmt:message
				key="signUp.lastName" bundle="${rb}" />
				required>
		</div>
	</div>
	<div class="form-group" id="n">
		<label class="control-label col-xs-3" for="firstName"><fmt:message
				key="signUp.name" bundle="${rb}" />:</label>
		<div class="col-xs-9">
			<input type="text" class="form-control" id="firstName"
				name="firstName"
				placeholder=<fmt:message
				key="signUp.name" bundle="${rb}" />
				required>
		</div>
	</div>
	<div class="form-group" id="log-in">
		<label class="control-label col-xs-3" for="login"><fmt:message
				key="signUp.login" bundle="${rb}" />:</label>
		<div class="col-xs-9">
			<input type="text" class="form-control" id="login" name="login"
				placeholder=<fmt:message
				key="signUp.login" bundle="${rb}" />
				required onchange="doAjax(this.value)"> <b><span
				id="isLogin"></span></b>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-xs-3" for="inputEmail">Email:</label>
		<div class="col-xs-9">
			<input type="email" class="form-control" id="inputEmail"
				name="inputEmail" placeholder="Email" required>
		</div>
	</div>
	<div class="form-group" id="phone">
		<label class="control-label col-xs-3" for="mobilePhone"><fmt:message
				key="signUp.phone" bundle="${rb}" />:</label>
		<div class="col-xs-9">
			<input type="text" class="form-control" id="mobilePhone"
				name="mobilePhone"
				placeholder=<fmt:message
				key="signUp.phone" bundle="${rb}" />
				required> Example: 999-999-99-99
		</div>
	</div>
	<div class="form-group" id="passw">
		<label class="control-label col-xs-3" for="inputPassword"><fmt:message
				key="signUp.password" bundle="${rb}" />:</label>
		<div class="col-xs-9">
			<input type="password" class="form-control" id="inputPassword"
				name="inputPassword"
				placeholder=<fmt:message
				key="signUp.password" bundle="${rb}" />
				required>
		</div>
	</div>
	<div class="form-group" id="passw1">
		<label class="control-label col-xs-3" for="confirmPassword"><fmt:message
				key="signUp.tryPassword" bundle="${rb}" />:</label>
		<div class="col-xs-9">
			<input type="password" class="form-control" id="confirmPassword"
				name="confirmPassword"
				placeholder=<fmt:message
				key="signUp.tryPassword" bundle="${rb}" />
				required>
		</div>
	</div>
	<div class="form-group">
		<div class="col-xs-offset-3 col-xs-9">
			<label class="checkbox-inline"> <input type="checkbox"
				value="agree" required> <fmt:message key="signUp.rule"
					bundle="${rb}" /> <a href="#myModal" data-toggle="modal"> <fmt:message
						key="signUp.conditions" bundle="${rb}" /></a> <fmt:message
					key="signUp.footer" bundle="${rb}" />.
			</label>
		</div>
	</div>
	<br />
	<div class="form-group">
		<div class="col-xs-offset-3 col-xs-9">
			<input type="submit" class="btn btn-primary"
				value=<fmt:message
					key="signUp.header" bundle="${rb}" /> />
		</div>
	</div>
</form>


<div id="myModal" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">×</button>
				<h4 class="modal-title">
					<fmt:message key="rule.header" bundle="${rb}" />
				</h4>
			</div>
			<div class="modal-body">
				<h4>
					<fmt:message key="rule.h1" bundle="${rb}" />
				</h4>
				<p>
					<fmt:message key="rule.h1Text" bundle="${rb}" />
				</p>
				<h4>
					<fmt:message key="rule.h2" bundle="${rb}" />
				</h4>
				<p>
					<fmt:message key="rule.h2Text" bundle="${rb}" />
				</p>
				<h4>
					<fmt:message key="rule.h3" bundle="${rb}" />
				</h4>
				<p>
					<fmt:message key="rule.h3Text" bundle="${rb}" />
				</p>
				<h4>
					<fmt:message key="rule.h4" bundle="${rb}" />
				</h4>
				<p>
					<fmt:message key="rule.h4Text" bundle="${rb}" />
				</p>
			</div>
		</div>
	</div>
</div>