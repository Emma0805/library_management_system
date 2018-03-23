package dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.hibernate.Session;
import org.hibernate.query.Query;

import entity.Parent;
import entity.Student;
import entity.StudentAndParent;
import hibernate.HibernateUtil;

public class StudentDao {
	public Student findStudent(int studentId) {
		Session session = null;
		session = HibernateUtil.getSession();
		session.beginTransaction();

		Student student = new Student();
		student = session.get(Student.class, studentId);
		session.close();
		return student;
	}

	public Student findStudentByUserId(int userId) {
		Session session = null;
		session = HibernateUtil.getSession();
		session.beginTransaction();

		String hql = new String("from Student where userId=:userId");
		Query<Student> query = session.createQuery(hql);
		query.setInteger("userId", userId);
		List<Student> list = query.list();
		session.close();
		return list.get(0);
	}

	public List<StudentAndParent> findStudentAndParentCheckedOut() {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();

			Date beginDate = new Date();
			Calendar date = Calendar.getInstance();
			date.setTime(beginDate);
			date.set(Calendar.DATE, date.get(Calendar.DATE) - 20);

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
			String time = formatter.format(date.getTime());

			String hql = "select distinct s.studentId, s.firstName, s.lastName, p.firstName, p.lastName from Student as s, Book as b, Parent as p where s.studentId=b.studentId and s.studentId=p.studentId and b.dateCheckout > :c_date";
			Query<Student> query = session.createQuery(hql);
			query.setString("c_date", time);
			Iterator itr = query.iterate();
			List<StudentAndParent> list = new ArrayList<StudentAndParent>();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				Student student = new Student();
				Parent parent = new Parent();
				student.setStudentId(Integer.parseInt(String.valueOf(obj[0])));
				student.setFirstName(String.valueOf(obj[1]));
				student.setLastName(String.valueOf(obj[2]));
				parent.setFirstName(String.valueOf(obj[3]));
				parent.setLastName(String.valueOf(obj[4]));
				StudentAndParent studentAndParent = new StudentAndParent();
				studentAndParent.setStudent(student);
				studentAndParent.setParent(parent);
				list.add(studentAndParent);
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

	public long countClassStudentRegister(int studentId) {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();

			String hql = "select count(*) from Register where studentId = :studentId";
			Query query = session.createQuery(hql);
			query.setInteger("studentId", studentId);
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

	public long countBookStudentCheckout(int studentId) {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();

			String hql = "select count(*) from Book where studentId = :studentId";
			Query query = session.createQuery(hql);
			query.setInteger("studentId", studentId);
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
}
