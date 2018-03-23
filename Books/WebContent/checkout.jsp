<%@page import="org.eclipse.jdt.internal.compiler.ast.ForeachStatement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" language="java" import="entity.*" errorPage=""%>
<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.util.*" errorPage=""%>
<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.text.SimpleDateFormat" errorPage=""%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Home</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Easy Admin Panel Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
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
 <body class="sticky-header left-side-collapsed"  onload="initMap()">
    <section>
    <!-- left side start-->
		<div class="left-side sticky-left-side">

			<!--logo and iconic logo end-->
			<jsp:include flush="true" page="LeftSlide.jsp"></jsp:include> 
		</div>
		<!-- left side end-->
    
		<!-- main content start-->
		<div class="main-content">
			<!-- header-starts -->
			<div class="header-section">
			 
			<!--toggle button start-->
			<a class="toggle-btn  menu-collapsed"><i class="fa fa-bars"></i></a>
			<!--toggle button end-->

			<!--notification menu start -->
			<div class="menu-right">
				<div class="user-panel-top">  	
					<div class="profile_details">		
						<ul>
							<li class="dropdown profile_details_drop">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
									<div class="profile_img">	
										 <div class="user-name">
										 <%String username = "";
										 String[] level = {"Student","Teacher/Staff","Admin"};
										 String character = "";
										 User user = (User)request.getSession().getAttribute("user");
										 if(user != null){
											 username = user.getUsername();
											 character = level[user.getLevel()-1];
										 }
										 %>
											<p><%=username %>
											<span><%=character%></span></p>
										 </div>
										 <i class="lnr lnr-chevron-down"></i>
										 <i class="lnr lnr-chevron-up"></i>
										<div class="clearfix"></div>	
									</div>	
								</a>
								<ul class="dropdown-menu drp-mnu">
									<li> <a href="login.jsp"><i class="fa fa-sign-out"></i> Logout</a> </li>
								</ul>
							</li>
							<div class="clearfix"> </div>
						</ul>
					</div>		
					<div class="clearfix"></div>
				</div>
			  </div>
			<!--notification menu end -->
			</div>
		<!-- //header-ends -->

		<!-- data content start -->
	<div class="panel panel-warning" data-widget="{&quot;draggable&quot;: &quot;false&quot;}" data-widget-static="">
							<%String message = "";
							if((message=(String)request.getAttribute("message"))!="" && request.getAttribute("message")!= null){%>
								<div class="alert alert-danger" role="alert">
								<%=message %>
							   	</div>
								<div class="clearfix"> </div>
							<%}%>
			
		<div class="panel-body no-padding">
								<table class="table table-striped">
									<thead>
										<tr class="warning">
											<th>#</th>
											<th>studentId</th>
											<th>bookId</th>
											<th>bookName</th>
											<th>checkDate</th>
											<th>dueDate</th>
										</tr>
									</thead>
									<tbody>
									<%List<Book> list = (List<Book>)request.getAttribute("books"); 
									if(list != null && list.size()>0){
										int number = 1;
										for(Book book:list){
											if (book.getStudentId() != null && book.getDateCheckout() != null) {
									%>
											<tr>
												<td><%=number %></td>
												<td><%=book.getStudentId() %></td>
												<td><%=book.getBookId() %></td>
												<td><%=book.getName() %></td>
												<td><%=book.getDateCheckout()%></td>
												<%Date beginDate = book.getDateCheckout();
												Calendar date = Calendar.getInstance();
												date.setTime(beginDate);
												date.set(Calendar.DATE, date.get(Calendar.DATE) + 30);
												SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
												String time = formatter.format(date.getTime());
												%>
												<td><%=time %></td>
											</tr>
									<% 		number ++;
											}
										}
									}%>
									</tbody>
								</table>
							</div>
						</div>
	<!-- data content end -->
		</div>
	
      <!-- main content end-->
   </section>
  
<script src="js/jquery.nicescroll.js"></script>
<script src="js/scripts.js"></script>
<!-- Bootstrap Core JavaScript -->
   <script src="js/bootstrap.min.js"></script>
</body>
</html>