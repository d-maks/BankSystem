<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="rus">
<%@include file="header.jsp"%>
<body>
	<nav class="navbar navbar-default ">
		<div class="container-fluid">


			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="controller?command=home"><span
							class="glyphicon glyphicon-home icon"></span></a></li>
					<li><a href="controller?command=deposit">Мои вклады</a></li>
					<li class="dropdown"><a href="" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Управление
							вкладами <span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="controller?command=new_deposit">Новый вклад</a></li>
							<li><a href="controller?command=plus_deposit">Пополнить
									счет</a></li>
							<li><a href="controller?command=reduce_deposit">Снять со
									счета</a></li>
						</ul></li>
					<!--  <li><a href="controller?command=graphics">Графики</a></li> -->
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="controller?command=logout">Выйти</a></li>
				</ul>
			</div>
		</div>
	</nav>

</body>
</html>
