package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BookManagement;

public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookManagement bookManagement = new BookManagement();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long bookAllNumber = bookManagement.findBookAllNumber();
		long bookAvilibleNumber = bookManagement.findBookAvilibleNumber();
		long bookCheckoutNumber = bookAllNumber - bookAvilibleNumber;

		request.setAttribute("bookAllNumber", bookAllNumber);
		request.setAttribute("bookAvilibleNumber", bookAvilibleNumber);
		request.setAttribute("bookCheckoutNumber", bookCheckoutNumber);

		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
}
