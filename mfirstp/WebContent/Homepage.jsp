<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
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

	<!-- NAVIGATION BAR -->
	<nav class="navbar navbar-default navbar-fixed-top navbar-inverse">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header page-scrollr">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand page-scroll" href="./Homepage.jsp">Payment
					Collecting</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li class="hidden"><a href="#page-top"></a></li>
					<li><a href="./Login.jsp">LOG IN</a></li>
					<li><a href="./Sighup.jsp">SIGH UP</a></li>
					<li><a class="page-scroll" href="#about-us">ABOUT US</a></li>
					<li><a class="page-scroll" href="#contact-us">CONTACT US</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container -->
	</nav>

	<!-- ABOUT US SECTION-->
	<section id="about-us">
		<div class="container">
			<div class="row col-lg-12 text-center" id="about-us-title">
				<h2>Services</h2>
				<h3>
					This is a Company Employee Management System. <br>Here are the
					three types of the accounts
				</h3>
			</div>
			<div class="row col-lg-12 text-center">
				<div class="col-md-4 text-center">
					<h4>The Super Administration</h4>
					<p>The privilege of the Super Administration.</p>
				</div>

				<div class="col-md-4 text-center">
					<h4>The manager</h4>
					<p>The privilege of the manager of each site.</p>
				</div>

				<div class="col-md-4 text-center">
					<h4>The Employee</h4>
					<p>The privilege of the Employee.</p>
				</div>
			</div>
		</div>
	</section>

	<!-- CONTACT US Section -->
	<section id="contact-us">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h2 class="section-heading">Contact Us</h2>
					<h3 class="section-subheading text-muted">This is our contact
						infor</h3>
				</div>
			</div>
		</div>
	</section>

	<!-- FOOTER -->
	<footer class="bg-darkest-gray">
		<div class="container">
			<div class="row">
				<div class="col-md-4">
					<ul class="list-inline">
						<li><a href="#">Copyright &copy; Your Website 2018</a></li>
					</ul>
				</div>
				<div class="col-md-4"></div>
				<div class="col-md-4">
					<ul class="list-inline">
						<li><a href="#">Privacy Policy</a></li>
						<li><a href="#">Terms of Use</a></li>
					</ul>
				</div>
			</div>
		</div>
	</footer>

</body>

</html>