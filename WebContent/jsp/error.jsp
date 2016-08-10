<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="header.jsp"%>
<body>
	<jsp:include page="header_page.jsp" />
	<div id="main_unit">
		<div id="header_section"></div>
		<center>
			<br />
			<p> Произошла неизвестная ошибка</p>
			<br />
			<p>Код: ${pageContext.errorData.statusCode}</p>
			<br />
			<p>Ошибка: ${pageContext.exception}</p>
			<br />
		</center>
	</div>
</body>
</html>