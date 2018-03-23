package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.BookAndClass;
import service.BookManagement;

public class BooksCheckoutByClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookManagement bookManagement = new BookManagement();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String condition = request.getParameter("condition");

		List<BookAndClass> book = bookManagement.findBookCheckedoutByCondition(condition);

		request.setAttribute("books", book);

		request.getRequestDispatcher("listBooksByCondition.jsp").forward(request, response);
	}
}
