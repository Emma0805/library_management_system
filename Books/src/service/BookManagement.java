package service;

import java.util.List;

import dao.BookDao;
import dao.StudentDao;
import entity.Book;
import entity.BookAndClass;
import entity.Paging;
import entity.Student;

public class BookManagement {
	private BookDao bookDao = new BookDao();

	public Book findBook(int bookId) {
		return bookDao.findBook(bookId);
	}

	public long findBookAllNumber() {
		return bookDao.findBookAllNumber();
	}

	public long findBookAvilibleNumber() {
		return bookDao.findBookAvilibleNumber();
	}

	public List<Book> findBookByConditions(String bookName, String ISBN, String author) {
		if ((bookName == null && ISBN == null && author == null)
				|| (("").equals(bookName) && ("").equals(ISBN) && ("").equals(author))) {
			return bookDao.findBookAll();
		} else {
			if (("").equals(ISBN) || ISBN == null) {
				return bookDao.findBookByConditions(bookName, 0, author);
			}
			Long ISBNN = Long.parseLong(ISBN);
			return bookDao.findBookByConditions(bookName, ISBNN, author);
		}
	}

	public Paging findBookByConditionsByPaging(String bookName, String ISBN, String author, int pageSize,
			int pageNumber) {
		if ((bookName == null && ISBN == null && author == null)
				|| (("").equals(bookName) && ("").equals(ISBN) && ("").equals(author))) {
			return bookDao.findBookAllByPaging(pageSize, pageNumber);
		}
		return null;
	}

	public String deleteBookById(int bookId) {
		int result = bookDao.deleteBookById(bookId);
		if (result == 1) {
			return "Delete succeed";
		} else if (result == -1) {
			return "The book has been borrowed, can't be deleted!";
		}
		return "system error!";
	}

	public String updateBook(Book book) {
		int result = bookDao.updateBook(book);
		if (result == 1) {
			return "Update succeed";
		}
		return "system error!";
	}

	public String addBook(Book book) {
		int result = bookDao.addBook(book);
		if (result == 1) {
			return "Add succeed";
		}
		return "system error!";
	}

	public String checkoutBook(int bookId, int userId) {
		Student student = new Student();
		StudentDao dao = new StudentDao();
		student = dao.findStudentByUserId(userId);

		long numberOfClass = dao.countClassStudentRegister(student.getStudentId());
		long numberOfBook = dao.countBookStudentCheckout(student.getStudentId());
		if (numberOfClass <= numberOfBook) {
			return "You already checked out too much books! The number of books you checked out: " + numberOfBook;
		}
		int result = bookDao.checkoutBook(bookId, student.getStudentId());

		if (result == 1) {
			return "checkout succeed";
		}
		return "system error!";
	}

	public List<Book> findStudentCheckedOut() {
		return bookDao.findBookAll();
	}

	public List<BookAndClass> findBookCheckedoutByCondition(String condition) {
		return bookDao.findBookCheckedoutByCondition(condition);
	}

}
