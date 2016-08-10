<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>Добро пожаловать!</title>
<%@include file="header.jsp"%>

<link
	href="/wp-content/themes/clear-theme/styles/bootstrap-responsive.css"
	rel="stylesheet">
</head>
<body>
	<form class="form-signin" name="loginForm" method="POST"
		action="controller">

		<h2 class="form-signin-heading">Вход</h2>
		<input type="hidden" name="command" value="login" /> <input
			type="text" name="login" class="input-block-level"
			placeholder="Логин"> <br> <input type="password"
			name="password" class="input-block-level" placeholder="Пароль">
			<p class="text-danger error">${errorLoginPassMessage} <br /> ${wrongAction} <br /> ${nullPage}</p>
		<button type="submit" class="btn btn-primary">Войти</button>
		<a class="reg" href="controller?command=reg">Зарегистроваться</a>
	</form>
</body>
</html>