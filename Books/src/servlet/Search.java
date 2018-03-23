package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Book;
import service.BookManagement;

public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookManagement bookManagement = new BookManagement();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookName = (String) request.getParameter("bookName");
		String author = (String) request.getParameter("authorName");
		String ISBN = (String) request.getParameter("ISBN");
		// int pageNumber =
		// Integer.parseInt(request.getParameter("pageNumber"));
		List<Book> list = bookManagement.findBookByConditions(bookName, ISBN, author);
		request.setAttribute("bookList", list);
		// Paging page = bookManagement.findBookByConditionsByPaging(bookName,
		// ISBN, author, 5, pageNumber);
		request.getRequestDispatcher("search.jsp").forward(request, response);
	}
}
