package in.fssa.vanha.servlets;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import in.fssa.vanha.exception.ServiceException;
import in.fssa.vanha.exception.ValidationException;
import in.fssa.vanha.model.ListProductDTO;
import in.fssa.vanha.model.ResponseEntity;
import in.fssa.vanha.service.ProductService;

/**
 * Servlet implementation class CategoryListServlet
 */
@WebServlet("/home/categroyproduct")
public class CategoryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Handles HTTP GET requests to retrieve product information by category and
	 * email.
	 *
	 * This method is responsible for processing incoming HTTP GET requests and
	 * returning a JSON response containing product details based on the specified
	 * category and optionally the user's email.
	 *
	 * @param request  The HttpServletRequest object representing the incoming HTTP
	 *                 request.
	 * @param response The HttpServletResponse object for sending the HTTP response.
	 *
	 * @throws ServletException If there is an issue with the servlet handling.
	 * @throws IOException      If there is an issue with input or output.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String cate = request.getParameter("Category");

		String email = request.getParameter("email");

		ProductService ps = new ProductService();

		Set<ListProductDTO> products = null;
		Gson gson = new Gson();

		try {
			if (email == null) {
				products = ps.findAllProductsByCategoryWithoutEmail(cate);
			} else {
				if (email.startsWith("\"") && email.endsWith("\"")) {
					email = email.substring(1, email.length() - 1);
				}
				products = ps.findAllProductsByCategory(cate, email);
			}

			ResponseEntity res = new ResponseEntity();
			res.setStatusCode(200);
			res.setData(products);
			res.setMessage("product details fetched successfully");

			String responseJson = gson.toJson(res);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(responseJson);

		} catch (ServiceException e) {
			String errorMessage = e.getMessage();
			ResponseEntity res = new ResponseEntity();
			res.setStatusCode(500); // Internal Server Error
			res.setMessage(errorMessage);

			String responseJson = gson.toJson(res);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(responseJson);
		} catch (ValidationException e) {
			String errorMessage = e.getMessage();
			ResponseEntity res = new ResponseEntity();
			res.setStatusCode(400); // Bad Request
			res.setMessage(errorMessage);

			String responseJson = gson.toJson(res);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(responseJson);
		}
	}

}
