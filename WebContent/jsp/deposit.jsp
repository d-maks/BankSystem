<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>Вклады</title>
<%@include file="header.jsp"%>
</head>
<body>
	<jsp:include page="header_page.jsp" />
	<div>
		<div class="col-ld-9 col-xs-9 col-sd-9 col-md-9">
			<form class="user_form">
				<table class="table table-bordered table-striped">
					<thead>
						<tr class="warning">
							<th>Номер вклада</th>
							<th>Сумма</th>
							<th>Дата внесения</th>
							<th>Срок</th>
							<th>Процент</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="deposit" items="${list}">
							<tr>
								<td><a
									href="controller?command=history&number=${deposit.depositNumber}">${deposit.depositNumber}</a></td>
								<td>${deposit.sum}</td>
								<td>${deposit.date}</td>
								<td>${deposit.term}</td>
								<td>${deposit.percent}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
		</div>
		<div class="col-ld-3 col-xs-3 col-md-3 col-sd-3">
			<form class="user_form">

				<table class="table">
					<tr>
						<td>${client.name}</td>
					</tr>
					<tr>

						<td>${client.surname}</td>
					</tr>
					<tr>
						<td>${client.mail}</td>
					</tr>
					<tr>
						<td>${client.phoneNumber}</td>
					</tr>
					<tr>
						<td>${client.address}</td>
					</tr>
				</table>

			</form>
		</div>
	</div>
</body>
</html>