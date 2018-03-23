<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sign-in</title>
<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel='stylesheet' type='text/css' />
<!-- Custom CSS -->
<link href="css/style.css" rel='stylesheet' type='text/css' />
<!-- Graph CSS -->
<link href="css/font-awesome.css" rel="stylesheet"> 
<!-- jQuery -->
<!-- lined-icons -->
<link rel="stylesheet" href="css/icon-font.min.css" type='text/css' />
<!-- //lined-icons -->
<!-- chart -->
<script src="js/Chart.js"></script>
<!-- //chart -->
<!--animate-->
<link href="css/animate.css" rel="stylesheet" type="text/css" media="all">
<script src="js/wow.min.js"></script>
	<script>
		 new WOW().init();
	</script>
<!--//end-animate-->
 <!-- Meters graphs -->
<script src="js/jquery-1.10.2.min.js"></script>
<!-- Placed js at the end of the document so the pages load faster -->
</head>
<body class="sign-in-up">
<%HttpSession s = request.getSession();
	s.removeAttribute("user");%>
    <section>
			<div id="page-wrapper" class="sign-in-wrapper">
				<div class="graphs">
					<div class="sign-in-form">
						<div class="sign-in-form-top">
							<p><span>Sign In to Admin</span></p>
						</div>
						<div class="signin">
							<div class="signin-rit">
								<span class="checkbox1">
									 <a href="#">Forgot Password ?</a>
								</span>
							</div>
						<form action="Login" method="post">
							<div class="log-input">
								<div class="log-input-left">
								   <input name="username" type="text" class="user"/>
								</div>
								<div class="clearfix"> </div>
							</div>
							<div class="log-input">
								<div class="log-input-left">
								   <input name="password" type="password" class="lock"/>
								</div>
								<div class="clearfix"> </div>
							</div>
							<%String message = "";
							if((message=(String)request.getAttribute("message"))!="" && request.getAttribute("message")!= null){%>
								<div class="alert alert-danger" role="alert">
								<%=message %>
							   	</div>
								<div class="clearfix"> </div>
							<%}%>
							
							<input type="submit" value="Login to your account">
						</form>	 
						</div>
					</div>
				</div>
			</div>
	</section>
	
<script src="js/jquery.nicescroll.js"></script>
<script src="js/scripts.js"></script>
<!-- Bootstrap Core JavaScript -->
   <script src="js/bootstrap.min.js"></script>
</body>
</html>