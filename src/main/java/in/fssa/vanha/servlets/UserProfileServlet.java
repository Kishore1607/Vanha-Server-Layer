package in.fssa.vanha.servlets;

import java.io.IOException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.fssa.vanha.exception.ServiceException;
import in.fssa.vanha.exception.ValidationException;
import in.fssa.vanha.model.ListProductDTO;
import in.fssa.vanha.model.User;
import in.fssa.vanha.service.ProductService;

/**
 * Servlet implementation class UserProfileServlet
 */
@WebServlet("/home/profile")
public class UserProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		ProductService ps = new ProductService();

		Set<ListProductDTO> products;
		try {
			products = ps.findAllProductsBySellerId(user.getEmail());
			request.setAttribute("user", user);
			request.setAttribute("products", products);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/profile.jsp");
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (ValidationException e) {
			e.printStackTrace();
		}

	}

}
