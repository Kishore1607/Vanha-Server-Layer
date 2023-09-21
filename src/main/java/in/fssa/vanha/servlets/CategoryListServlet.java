package in.fssa.vanha.servlets;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import in.fssa.vanha.exception.ServiceException;
import in.fssa.vanha.exception.ValidationException;
import in.fssa.vanha.model.ListProductDTO;
import in.fssa.vanha.model.ResponseEntity;
import in.fssa.vanha.model.User;
import in.fssa.vanha.service.ProductService;

/**
 * Servlet implementation class CategoryListServlet
 */
@WebServlet("/home/categroyproduct")
public class CategoryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String cate = request.getParameter("Category");

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		Set<ListProductDTO> products = null;

		if (user == null) {
			try {
				ProductService ps = new ProductService();
				products = ps.findAllProductsByCategory(cate);
				ResponseEntity res = new ResponseEntity();
				res.setStatusCode(200);
				res.setData(products);
				res.setMessage("product details fetched successfully");

				Gson gson = new Gson();
				String responseJson = gson.toJson(res);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(responseJson);

			} catch (ServiceException e) {
				e.printStackTrace();
			} catch (ValidationException e) {
				e.printStackTrace();
			}
		} else {
			try {
				ProductService ps = new ProductService();
				products = ps.findAllProductsByCategory(cate, user.getEmail());
				ResponseEntity res = new ResponseEntity();
				res.setStatusCode(200);
				res.setData(products);
				res.setMessage("product details fetched successfully");

				Gson gson = new Gson();
				String responseJson = gson.toJson(res);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(responseJson);

			} catch (ServiceException e) {
				e.printStackTrace();
			} catch (ValidationException e) {
				e.printStackTrace();
			}
		}
	}
}
