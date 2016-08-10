<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>Пользователи</title>
<%@include file="header.jsp"%>
</head>
<body>
	<jsp:include page="admin_header_page.jsp" />
	<div>
		<p class="text-danger reg_error">${errorMessage}</p>
		<div class="col-lg-10 col-lg-offset-1">
			<form class="user_form">
				<table class="table table-bordered table-striped">
					<thead>
						<tr class="warning">
							<th>Действие</th>
							<th>Роль</th>
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
								<td class="center_table"><div class="btn-group">
										<a title="Сделать админом"
											href="controller?command=set_admin&login=${client.login}"
											class="btn btn-xs btn-primary"><span
											class="glyphicon glyphicon-user"></span></a> <a title="Удалить"
											href="controller?command=delete_user&login=${client.login}"
											class="btn btn-xs btn-danger"><span
											class="glyphicon glyphicon-trash"></span></a>
											<a title="Сделать юзером"
											href="controller?command=set_user&login=${client.login}"
											class="btn btn-xs btn-warning"><span
											class="glyphicon glyphicon-remove"></span></a>
									</div></td>
								<td>${client.role}</td>
								<td>${client.login}</td>
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