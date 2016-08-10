<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>Снятие средств</title>
<%@include file="header.jsp"%>
</head>
<body>
	<jsp:include page="header_page.jsp" />
	<form class="form-horizontal reg_form" name="addSum" method="POST"
		action="controller">
		<input type="hidden" name="command" value="reduce_sum" />
		<fieldset>
			<legend class="red_head ">Снятие средств</legend>
			<p class="text-danger reg_error">${errorMessage}</p>
			<div class="form-group">
				<label for="number" class="col-lg-2 control-label correct">Номер
					вклада:</label>
				<div class="col-lg-10">
					<select class="form-control " id="firm" name="number">
						<c:forEach var="deposit" items="${list}">
							<option>${deposit}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="sum" class="col-lg-2 control-label">Сумма</label>
				<div class="col-lg-10">
					<input type="text" class="form-control" name="sum"
						placeholder="Введите сумму вклада" required="required" value = "${sum}">
				</div>
			</div>
			<div class="form-group">
				<div class="col-lg-10 col-lg-offset-2">
					<input type="submit" class="btn btn-primary red_button"
						value="Снять">
				</div>
			</div>
		</fieldset>
	</form>
</body>
</html>