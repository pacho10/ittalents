<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ page errorPage="error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Efficient Project</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />


<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css" />
<link href="bootstrap/css/simple-sidebar.css" rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="font-awesome/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="customCSS/styles.css">

<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>


<script type="text/javascript">
	/* 	function SidebarChangeConent() {
	 $("#content").empty();
	 $.ajax({
	 url : './dashboard',
	 type : 'GET',
	 success : function(response) {
	 $("#content").append(response);
	 }

	 });

	 }  */

 	$(document).ready(function() {
	 $("a.noReLoad").click(function() {
	 var myhref = $(this).attr('href');
	 $("#content").empty();
	 $('#content').load(myhref);
	 return false;
	 });
	 });
</script>



</head>

<body>
	<c:if
		test="${ sessionScope.user == null && not sessionScope.user.admin}">
		<c:redirect url="/LogIn"></c:redirect>
	</c:if>

	<jsp:include page="navBarAdmin.jsp"></jsp:include>

	<div id="wrapper" class="toggled">

		<jsp:include page="sidebarProject.jsp"></jsp:include>

		<div id="page-content-wrapper">
			<div class="container-fluid">
				<h1 class="text-center text-info">${project.name}</h1>
				<hr>

				<div id="content">
					<div>
						<span>${project.name}</span> <span>${project.deadline}</span>
					</div>
					<div>
						<h3>Epics</h3>
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>