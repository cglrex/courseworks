package indi.mfirstp.servlet;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import indi.mfirstp.dao.UserDAOProxy;
import indi.mfirstp.vo.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		ArrayList<String> info = new ArrayList<String>();
		if (email == null || "".equals(email)) {
			info.add("Please enter an email");
		}
		if (password == null || "".equals(password)) {
			info.add("Please enter an password");
		}
		if (info.size() == 0) {
			User user = new User();
			user.setEmail(email);
			user.setPassword(password);
			UserDAOProxy userDAOProxy = new UserDAOProxy();
			try {
				if (userDAOProxy.findLogin(user)) {
					info.add("Welcome " + user.getEmail() + " !");
					request.setAttribute("info", info);
					request.getRequestDispatcher("Login_success.jsp").forward(request, response);
				} else {
					info.add("Faild to login");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("info", info);
		request.getRequestDispatcher("Login.jsp").forward(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
