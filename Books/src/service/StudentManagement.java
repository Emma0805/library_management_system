package service;

import java.util.List;

import dao.StudentDao;
import entity.Student;
import entity.StudentAndParent;

public class StudentManagement {
	private StudentDao studentDao = new StudentDao();

	public Student findStudent(int studentId) {
		return studentDao.findStudent(studentId);
	}

	public List<StudentAndParent> findStudentAndParentCheckedOut() {
		return studentDao.findStudentAndParentCheckedOut();
	}
}
