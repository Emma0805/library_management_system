package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import entity.User;
import hibernate.HibernateUtil;

public class UserDao {
	public User findUser(int userId) {
		Session session = null;
		session = HibernateUtil.getSession();
		session.beginTransaction();

		User user = new User();
		user = session.get(User.class, userId);
		session.close();
		return user;
	}

	public List<User> findUserbyNameAndPassword(String username, String password) {
		Session session = null;
		session = HibernateUtil.getSession();
		session.beginTransaction();

		String hql = "from User where username = ? and password= ?";
		Query<User> query = session.createQuery(hql);
		query.setParameter(0, username);
		query.setParameter(1, password);
		List<User> list = query.list();

		session.close();
		return list;
	}

	public List<User> findUserAll() {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();

			String hql = "from User";
			Query<User> query = session.createQuery(hql);
			List<User> list = query.list();
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