<!DOCTYPE>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>Регистрация</title>
<%@include file="header.jsp"%>
</head>
<body>
	<form class="form-horizontal reg_form" name="regForm" method="POST"
		action="controller">
		<input type="hidden" name="command" value="reg_client" />
		<fieldset>
			<legend class="reg_head ">Регистрация</legend>
			<p class="text-danger reg_error">${errorMessage}</p>
			<div class="form-group">
				<label for="lastName" class="col-lg-2 control-label">Фамилия</label>
				<div class="col-lg-10">
					<input type="text" class="form-control confirm" name="lastName"
						placeholder="Введите фамилию" required="required" value = "${lastName}">
				</div>
			</div>
			<div class="form-group">
				<label for="firstName" class="col-lg-2 control-label">Имя</label>
				<div class="col-lg-10">
					<input type="text" class="form-control confirm" name="firstName"
						placeholder="Введите имя" required="required" value = "${firstName}">
				</div>
			</div>
			<div class="form-group">
				<label for="inputEmail" class="col-lg-2 control-label">Email</label>
				<div class="col-lg-10">
					<input type="email" class="form-control confirm" name="inputEmail"
						placeholder="Email" required="required" value = "${inputEmail}">
				</div>
			</div>
			<div class="form-group">
				<label for="login" class="col-lg-2 control-label">Логин</label>
				<div class="col-lg-10">
					<input type="text" class="form-control confirm" name="inputLogin"
						placeholder="Введите логин" required="required" value = "${inputLogin}">
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword" class="col-lg-2 control-label">Пароль:</label>
				<div class="col-lg-10">
					<input type="password" class="form-control confirm" name="inputPassword"
						placeholder="Введите пароль" required="required">
				</div>
			</div>
			<div class="form-group">
				<label for="confirmPassword" class="col-lg-2 control-label correct_pass">Подтвердите
					пароль:</label>
				<div class="col-lg-10">
					<input type="password" class="form-control confirm" name="confirmPassword"
						placeholder="Введите пароль ещё раз" required="required">
				</div>
			</div>
			<div class="form-group">
				<label for="phoneNumber" class="col-lg-2 control-label">Телефон:</label>
				<div class="col-lg-10">
					<input type="tel" class="form-control confirm" name="phoneNumber"
						placeholder="Введите номер телефона" required="required" value = "${phoneNumber}">
				</div>
			</div>
			<div class="form-group">
				<label for="postalAddress" class="col-lg-2 control-label">Адрес:</label>
				<div class="col-lg-10">
					<textarea rows="3" class="form-control confirm" name="postalAddress"
						placeholder="Введите адрес" required="required">${postalAddress}</textarea>
				</div>
			</div>
			<div class="form-group">
				<div class="col-lg-10 col-lg-offset-2">
					<input type="submit" class="btn btn-primary reg_button"
						value="Регистрация" required="required">
				</div>
			</div>
		</fieldset>
	</form>
</body>
</html>