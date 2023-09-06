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
 * Servlet implementation class UpdateUserServlet
 */
@WebServlet("/home/update")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService us = new UserService();
		User user = new User();
		user.setName(request.getParameter("name"));
		user.setEmail(request.getParameter("email"));
		long number = Long.parseLong(request.getParameter("number"));
		user.setNumber(number);
		user.setLocation(request.getParameter("location"));
		user.setImage(request.getParameter("image"));

		try {
			us.update(user);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (ValidationException e) {
			e.printStackTrace();
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("user", user);

		String newURL = request.getContextPath() + "/home/profile";
		response.sendRedirect(newURL);
	}

}
