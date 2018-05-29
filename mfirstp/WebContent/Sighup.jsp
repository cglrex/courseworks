<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="description" content="">
<meta name="author" content="">

<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/mycustom.css" rel="stylesheet">

<!-- jQuery -->
<script src="http://code.jquery.com/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="js/mycustom.js"></script>

<title>Payment Collecting</title>

</head>

<body id="page-top" class="index">
	<!-- SIGHUP FORM -->
	<div class="container" id="login-form">
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-4">

				<%
					List<String> info = (List<String>) request.getAttribute("info");
				
					if (info != null) {
						Iterator<String> iter = info.iterator();
						while (iter.hasNext()) {
				%>
				<h4><%=iter.next()%></h4>
				<%
					}
					}
				%>
				<form class="form-signin" action="SighupServlet" method="post"
					onSubmit="return validata(this)">
					<h3>
						<a href="./Homepage.jsp"> <span
							class="glyphicon glyphicon-home" aria-hidden="true"></span>
						</a> Please Sigh Up
					</h3>
					<br /> <input type="email" id="inputEmail" name="email"
						class="form-control" placeholder="Email address" value="${email}" required>
					<br /> <input type="password" id="inputPassword1" name="password1"
						class="form-control" placeholder="Password" required> <br />
					<input type="password" id="inputPassword2" name="password2"
						class="form-control" placeholder="Confirm Password" required>
					<br />

					<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
						Up</button>
				</form>
			</div>
			<div class="col-md-4"></div>
		</div>
	</div>

</body>

</html>