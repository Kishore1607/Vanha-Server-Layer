package in.fssa.vanha.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.fssa.vanha.exception.ServiceException;
import in.fssa.vanha.exception.ValidationException;
import in.fssa.vanha.model.User;
import in.fssa.vanha.service.UserService;

/**
 * Servlet implementation class CreateUserServlet
 */
@WebServlet("/home/user/create")
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		UserService us = new UserService();
		User u = new User();
		u.setName(request.getParameter("name"));
		u.setEmail(email);
		u.setPassword(request.getParameter("password"));
		u.setLocation(request.getParameter("location"));
		u.setImage(request.getParameter("image"));
		long number = Long.parseLong(request.getParameter("number"));
		u.setNumber(number);

		try {
			us.create(u);

			HttpSession session = request.getSession();
			session.setAttribute("user", u);

			response.sendRedirect("./../profile");

		} catch (ValidationException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}

	}

}
