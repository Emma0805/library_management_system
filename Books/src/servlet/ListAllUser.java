package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.User;
import service.UserManagement;

public class ListAllUser extends HttpServlet {
	private UserManagement userManagement = new UserManagement();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> list = userManagement.findUserAll();

		request.setAttribute("userList", list);

		request.getRequestDispatcher("listUser.jsp").forward(request, response);
	}
}
