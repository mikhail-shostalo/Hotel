<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="ctg" uri="customtags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="text" var="rb" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Hotel</title>



<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/moment-with-locales.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrap-datetimepicker.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css" />


<link href=<c:url value="css/signin.css"/> rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/offcanvas.css" rel="stylesheet">
</head>

<body>
	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href=<c:url value="/items"/>>GRAND HOTEL</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href=<c:url value="/items"/>> <fmt:message
								key="header.home" bundle="${rb}" /></a></li>
					<li><a href=<c:url value="/about"/>> <fmt:message
								key="header.about" bundle="${rb}" />
					</a></li>
					<li><a href=<c:url value="/contact"/>><fmt:message
								key="header.contact" bundle="${rb}" /></a></li>

				</ul>
				<ul class="nav navbar-nav navbar-right">
					<c:choose>
						<c:when test="${empty user}">
							<li><a href=<c:url value="/logIn"/>><fmt:message
										key="header.logIn" bundle="${rb}" /></a></li>
							<li><a href=<c:url value="/registration"/>><fmt:message
										key="header.signUp" bundle="${rb}" /></a></li>
						</c:when>
						<c:otherwise>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">${user.login } <b class="caret"></b></a>
								<ul class="dropdown-menu">
									<c:choose>
										<c:when test="${user.role == 'client' }">
											<li><a href=<c:url value="/bookNow"/>><fmt:message
														key="user.bookNow" bundle="${rb}" /></a></li>
											<li><a href=<c:url value="/message"/>><fmt:message
														key="user.message" bundle="${rb}" /> <span class="badge">${answersCount}</span></a></li>
											<li><a href=<c:url value="/request"/>><fmt:message
														key="user.request" bundle="${rb}" /></a></li>
											<li><a href=<c:url value="/account"/>><fmt:message
														key="user.bookedRooms" bundle="${rb}" /> <span
													class="badge">${reserveCount}</span></a></li>
											<li><a href=<c:url value="/myRooms"/>><fmt:message
														key="user.listOfRooms" bundle="${rb}" /></a></li>
										</c:when>
										<c:otherwise>
											<li><a href=<c:url value="/requestList"/>><fmt:message
														key="manager.list" bundle="${rb}" /> <span class="badge">${requestsCount }</span></a></li>
										</c:otherwise>
									</c:choose>
									<li class="divider"></li>
									<li><a href=<c:url value="/logOut"/>><fmt:message
												key="user.logOut" bundle="${rb}" /></a></li>
								</ul></li>
						</c:otherwise>
					</c:choose>
					<li><a href=<c:url value="/rus"/>><img src="images/ru.png"
							height="30" width="30" /></a></li>
					<li><a href=<c:url value="/english"/>><img
							src="images/en.ico" height="30" width="30" /></a></li>
				</ul>
			</div>
		</div>
	</div>

	<c:if test="${not empty block }">
		<c:import url="${block}" />
	</c:if>

	<div class="container">
		<jsp:include page="${page}" />
		<hr>
	</div>

	<div id="footer">
		<div class="container">
			<ctg:footer-tag />
		</div>
	</div>
</body>
</html>