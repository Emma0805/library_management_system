<%@ page contentType="text/html; charset=UTF-8" language="java" import="entity.*" errorPage=""%>
			<!--logo and iconic logo start-->
			<div class="logo">
				<h1><a href="Index">Library<span>App</span></a></h1>
			</div>
			<div class="logo-icon text-center">
				<a href="Index"><i class="lnr lnr-home"></i> </a>
			</div>
<div class="left-side-inner">
				<!--sidebar nav start-->
					<ul class="nav nav-pills nav-stacked custom-nav">
						<li class="menu-list"><a href="#"><i class="lnr lnr-book"></i>  <span>Books</span></a> 
							<ul class="sub-menu-list">
								<li><a href="Search">Search Books</a> </li>
								<%if(((User)request.getSession().getAttribute("user")).getLevel()==3){ %>
								<li><a href="addBook.jsp">Add Books</a></li>
								<%} %>
								<%if(((User)request.getSession().getAttribute("user")).getLevel()>1){ %>
								<li><a href="BooksCheckoutByClass?condition=c.classId">list all books checked out (in class)</a> </li>
								<%} %>
							</ul>
						</li>
						<%if(((User)request.getSession().getAttribute("user")).getLevel()==3){ %>
						<li class="menu-list">
							<a href="#"><i class="lnr lnr-cog"></i>
								<span>Users</span></a>
								<ul class="sub-menu-list">
									<li><a href="ListAllUser">List All Users</a> </li>
								</ul>
						</li>
						<%} %>
						<%if(((User)request.getSession().getAttribute("user")).getLevel()>1){ %>
						<li class="menu-list">
							<a href="#"><i class="lnr lnr-spell-check"></i>
								<span>Students</span></a>
								<ul class="sub-menu-list">
									
									<li><a href="Checkout">Students with books checked out</a> </li>
									<li><a href="ListStuAndParents">Students and parents with books due in 3 days</a> </li>
									
								</ul>
						</li>
						<%} %>
					</ul>
				<!--sidebar nav end-->
			</div>