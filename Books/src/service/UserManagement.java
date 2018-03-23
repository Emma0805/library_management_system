package service;

import java.util.List;

import dao.UserDao;
import entity.User;

public class UserManagement {
	private UserDao userDao = new UserDao();

	public User findUser(int userId) {
		return userDao.findUser(userId);
	}

	public List<User> findUserbyNameAndPassword(String username, String password) {
		return userDao.findUserbyNameAndPassword(username, password);
	}

	public List<User> findUserAll() {
		return userDao.findUserAll();
	}
}
