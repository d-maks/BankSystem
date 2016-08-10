<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>Welcome</title>
<%@ include file="header.jsp"%>
</head>
<body>
	<jsp:include page="header_page.jsp" />
	<div id="carousel" class="carousel slide">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#carousel" data-slide-to="0" class="active"></li>
			<li data-target="#carousel" data-slide-to="1"></li>
			<li data-target="#carousel" data-slide-to="2"></li>
		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner to_top">
			<div class="item active">
			<a href="http://www.mmbank.by/"> <img class="my_img"
					src="img/moskva.png" alt=""> </a>
				<div class="carousel-caption"></div>
			</div>
			
			<div class="item">
				<a href="http://www.vtb-bank.by/"> <img class="my_img"
					src="img/vtb.jpg" alt=""> </a >
					<div class="carousel-caption"></div>
			</div>
			<div class="item">
				<a href="http://www.bps-sberbank.by/bank/ru.index.html"> <img class="my_img"
					src="img/bps.jpg " alt=""> </a >
					<div class="carousel-caption"></div>
			</div>
		</div>

		<!-- Controls -->
		<a class="left carousel-control" href="#carousel" data-slide="prev">
			<span class="glyphicon glyphicon-chevron-left"></span>
		</a> <a class="right carousel-control" href="#carousel" data-slide="next">
			<span class="glyphicon glyphicon-chevron-right"></span>
		</a>
	</div>
</body>
</html>