package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Book;
import service.BookManagement;

public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookManagement bookManagement = new BookManagement();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = (String) request.getParameter("action");
		String id = (String) request.getParameter("id");
		if (action.equals("delete") && id != null) {
			String message = bookManagement.deleteBookById(Integer.parseInt(id));
			request.setAttribute("message", message);
			request.getRequestDispatcher("Search").forward(request, response);
		} else if (action.equals("update") && id != null) {
			Book book = bookManagement.findBook(Integer.parseInt(id));
			request.setAttribute("book", book);
			request.getRequestDispatcher("updateInfo.jsp").forward(request, response);
		} else if (action.equals("updateInfo")) {
			Book book = new Book();
			book.setBookId(Integer.parseInt(request.getParameter("id")));
			book.setName(request.getParameter("name"));
			book.setAuthor(request.getParameter("author"));
			book.setISBN(Long.parseLong(request.getParameter("ISBNV")));
			String returned = request.getParameter("return");
			String studentId = request.getParameter("studentId");
			if (returned == null && !("null").equals(studentId)) {
				book.setStudentId(Integer.parseInt(request.getParameter("studentId")));
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
				Date d;
				try {
					d = formatter.parse(request.getParameter("checkoutDate"));
					java.sql.Date date = new java.sql.Date(d.getTime());
					book.setDateCheckout(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			String message = bookManagement.updateBook(book);
			request.setAttribute("message", message);
			request.getRequestDispatcher("Search").forward(request, response);
		} else if (action.equals("add")) {
			Book book = new Book();
			book.setName(request.getParameter("name"));
			book.setAuthor(request.getParameter("author"));
			book.setISBN(Long.parseLong(request.getParameter("ISBNV")));
			String message = bookManagement.addBook(book);
			request.setAttribute("message", message);
			request.getRequestDispatcher("Search").forward(request, response);
		} else if (action.equals("checkout")) {
			String sid = (String) request.getParameter("sid");

			String message = bookManagement.checkoutBook(Integer.parseInt(id), Integer.parseInt(sid));
			request.setAttribute("message", message);
			request.getRequestDispatcher("Search").forward(request, response);
		}
	}
}
