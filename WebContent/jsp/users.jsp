<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>Пользователи</title>
<%@include file="header.jsp"%>
</head>
<body>
	<jsp:forward page="/jsp/login.jsp" />
	<jsp:include page="admin_header_page.jsp" />
	<div>
		<div class="col-lg-10 col-lg-offset-1">
			<form class="user_form">
				<table class="table table-bordered table-striped">
					<thead>
						<tr class="warning">
							<th>Логин</th>
							<th>Имя</th>
							<th>Фамилия</th>
							<th>Адрес</th>
							<th>Номер телефона</th>
							<th>E-Mail</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="client" items="${list}">
							<tr>
								<td><a
									href="controller?command=history&number=${client.login}">${client.login}</a></td>
								<td>${client.name}</td>
								<td>${client.surname}</td>
								<td>${client.address}</td>
								<td>${client.phoneNumber}</td>
								<td>${client.mail}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
		</div>
	</div>
</body>
</html>