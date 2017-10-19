<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Efficient Project</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<!-- TODO  ????disable the browser cache????? -->
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT">

<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="font-awesome/css/font-awesome.min.css" />

<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

	<%-- 	<c:if test="${ sessionScope.user == null }">
			<c:redirect url="login.jsp"></c:redirect>
		</c:if> --%>
		
<jsp:include page="navBar.jsp"></jsp:include>
<div class="span2">
      <img src=${ sessionScope.user.avatarPath }  alt="" class="img-rounded">
    </div>
    <div class="span4">
      <blockquote>
        <p>${ sessionScope.user.firstName }</p>
        <small><cite title="Source Title">Gotham, United Kingdom  <i class="icon-map-marker"></i></cite></small>
      </blockquote>
      <p>
        <i class="fa-envelope"></i>${ sessionScope.user.email } <br>
        <i class="icon-globe"></i> www.bootsnipp.com <br>
        <i class="icon-gift"></i> January 30, 1974
      </p>
    </div>
</body>
</html>