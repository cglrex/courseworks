package indi.mfirstp.servlet;

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
 * Servlet implementation class SighupServlet
 */
@WebServlet("/SighupServlet")
public class SighupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SighupServlet() {
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
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		ArrayList<String> info = new ArrayList<String>();
		if (email == null || "".equals(email)) {
			info.add("Please enter an email");
		}
		if (password1 == null || "".equals(password1)) {
			info.add("Please enter an password");
		}
		if (password2 == null || "".equals(password2)) {
			info.add("Please confirm your password");
		}
		if (info.size() == 0) {
			User user = new User();
			user.setEmail(email);
			try {
				UserDAOProxy check = new UserDAOProxy();
				if (check.checkUsername(user)) {
					info.add("User email already exist");
				} else {
					UserDAOProxy sighup = new UserDAOProxy();
					if (password1.equals(password2)) {
						user.setPassword(password2);
						sighup.sighupUser(user);
					} else {
						info.add("Passwords are not the same");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("info", info);
		request.setAttribute("email", email);
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
