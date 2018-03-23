package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import entity.Book;
import entity.BookAndClass;
import entity.Paging;
import hibernate.HibernateUtil;

public class BookDao {
	public Book findBook(int bookId) {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();

			Book book = new Book();
			book = session.get(Book.class, bookId);
			HibernateUtil.closeSession(session);
			return book;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	public List<Book> findBookAll() {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();

			String hql = "from Book";
			Query<Book> query = session.createQuery(hql);
			query.setFirstResult(0);
			query.setMaxResults(10);
			List<Book> list = query.list();
			HibernateUtil.closeSession(session);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	public Paging findBookAllByPaging(int pageSize, int pageNumber) {
		Session session = null;
		Paging pa = new Paging();
		try {
			long count = findBookAllNumber();
			session = HibernateUtil.getSession();
			session.beginTransaction();
			int start = (pageNumber - 1) * pageSize;
			String hql = "from Book limit " + start + "," + pageSize;
			Query<Book> query = session.createQuery(hql);
			List<Book> list = query.list();
			if (!list.isEmpty()) {
				pa.setRows(list);
				pa.setRecords(count);
				pa.setPage(pageNumber);
				int pageTotal = (int) (count / pageSize);
				pa.setTotal(pageTotal);
			}
			HibernateUtil.closeSession(session);
			return pa;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	public long findBookAllNumber() {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();

			String hql = "select count(*) from Book";
			Query query = session.createQuery(hql);
			long number = (long) query.uniqueResult();
			HibernateUtil.closeSession(session);
			return number;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return -1;
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	public long findBookAvilibleNumber() {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();

			String hql = "select count(*) from Book where studentID is null";
			Query query = session.createQuery(hql);
			long number = (long) query.uniqueResult();
			HibernateUtil.closeSession(session);
			return number;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return -1;
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	public List<Book> findBookByConditions(String bookName, long ISBN, String author) {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();

			StringBuffer hql = new StringBuffer("from Book where ");
			if (bookName != null && !("").equals(bookName)) {
				hql.append("name like \'%" + bookName + "%\' and ");
			}
			if (ISBN != 0) {
				hql.append("ISBN = " + ISBN + " and ");
			}
			if (author != null && !("").equals(author)) {
				hql.append("author like \'%" + author + "%\' and ");
			}

			hql.setLength(hql.length() - 5);
			Query<Book> query = session.createQuery(hql.toString());
			List<Book> list = query.list();
			HibernateUtil.closeSession(session);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	public int deleteBookById(int bookId) {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();

			Book book = session.get(Book.class, bookId);
			if (book.getStudentId() != null) {
				return -1;
			}
			session.delete(book);
			session.getTransaction().commit();
			HibernateUtil.closeSession(session);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return -1;
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	public int updateBook(Book book) {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();

			session.update(book);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return -1;
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	public int addBook(Book book) {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();

			session.save(book);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return -1;
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	public int checkoutBook(int bookID, int studentId) {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			Book book = (Book) session.get(Book.class, bookID);
			book.setDateCheckout(new java.sql.Date(System.currentTimeMillis()));
			book.setStudentId(studentId);
			session.update(book);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return -1;
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	public List<BookAndClass> findBookCheckedoutByCondition(String condition) {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();

			String hql = "select b.bookId, b.name, c.classId from Book as b, Class as c where b.bookId = c.bookId and b.studentId != null order by "
					+ condition;
			Query<Object> query = session.createQuery(hql);
			Iterator itr = query.iterate();
			List<BookAndClass> list = new ArrayList<BookAndClass>();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				Book book = new Book();
				book.setBookId(Integer.parseInt(String.valueOf(obj[0])));
				book.setName(String.valueOf(obj[1]));
				BookAndClass bookAndClass = new BookAndClass();
				bookAndClass.setBook(book);
				bookAndClass.setClassId(Integer.parseInt(String.valueOf(obj[2])));
				list.add(bookAndClass);
			}
			HibernateUtil.closeSession(session);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
}