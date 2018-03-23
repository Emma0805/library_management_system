package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.StudentAndParent;
import service.StudentManagement;

public class ListStuAndParents extends HttpServlet {
	private StudentManagement StudentManagement = new StudentManagement();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<StudentAndParent> list = StudentManagement.findStudentAndParentCheckedOut();

		request.setAttribute("StuAndParents", list);

		request.getRequestDispatcher("ListStuAndParents.jsp").forward(request, response);
	}
}
