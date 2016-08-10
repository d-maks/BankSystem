<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>Добавление вклада</title>
<%@include file="header.jsp"%>
</head>
<body>
	<jsp:include page="header_page.jsp" />
	<form class="form-horizontal reg_form" name="addDeposit" method="POST"
		action="controller">
		<input type="hidden" name="command" value="add_deposit" />
		<fieldset>
			<legend class="reg_head ">Новый вклад</legend>
			<p class="text-danger reg_error">${errorMessage}</p>
			<div class="form-group">
				<label for="sum" class="col-lg-2 control-label">Сумма</label>
				<div class="col-lg-10">
					<input type="text" class="form-control" name="sum"
						placeholder="Введите сумму вклада" required="required" value = "${sum}">
				</div>
			</div>
			<div class="form-group">
				<label for="term" class="col-lg-2 control-label">Срок</label>
				<div class="col-lg-10">
					<input type="text" class="form-control" name="term"
						placeholder="Введите срок вклада" required="required" value = "${term}">
				</div>
			</div>
			<div class="form-group">
				<label for="percent" class="col-lg-2 control-label">Процент</label>
				<div class="col-lg-10">
					<input type="text" class="form-control" name="percent"
						placeholder="Введите процент вклада" required="required" value = "${percent}">
				</div>
			</div>
			<div class="form-group">
				<div class="col-lg-10 col-lg-offset-2">
					<input type="submit" class="btn btn-primary reg_button"
						value="Добавить">
				</div>
			</div>
		</fieldset>
	</form>
</body>
</html>