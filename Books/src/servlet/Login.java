package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.User;
import service.UserManagement;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserManagement userManagement = new UserManagement();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");
		if (username == null || ("").equals(username) || password == null || ("").equals(password)) {
			request.setAttribute("message", "Username or password can't be null!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		HttpSession session = request.getSession();
		List<User> list = userManagement.findUserbyNameAndPassword(username, password);
		if (!list.isEmpty()) {
			User user = list.get(0);
			session.setAttribute("user", user);
			request.getRequestDispatcher("Index").forward(request, response);
			return;
		}
		request.setAttribute("message", "Username or password is wrong!");
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
}
