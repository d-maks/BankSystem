<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>История</title>
<%@include file="header.jsp"%>
</head>
<body>
	<jsp:include page="header_page.jsp" />
	<div>
		<div class="col-lg-10 col-lg-offset-1">
			<form class="user_form">
				<p class="text-warning error col-lg-offset-4">Ваш текущий баланс
					составляет ${sum}</p>
			</form>
		</div>
		<div class="col-lg-10 col-lg-offset-1">
			<form class="user_form" name="saveForm" method="POST"
				action="controller?command=save&actionList=${number}">
				<input type="hidden" name="number" value="${number}" />
				<table class="table table-bordered table-striped">
					<tbody>
						<c:forEach var="history" items="${list}">
							<tr>
								<td class="center_table">${history.action}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<input type="submit" class="btn btn-primary save_button"
					value="Сохранить">
			</form>
		</div>
	</div>
</body>
</html>