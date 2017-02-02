<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:forEach var="item" items="${classes }">
	<div class="row featurette">
		<div class="col-md-7 col-md-push-5">
			<h2 class="featurette-heading">${item.roomClass}</h2>
			<p class="lead">${item.description}</p>
		</div>
		<div class="col-md-5 col-md-pull-7">
			<img class="featurette-image img-responsive center-block"
				src=<c:url value="images/${item.id }.jpg"/>
				alt="Generic placeholder image">
		</div>
	</div>

	<hr class="featurette-divider">
</c:forEach>