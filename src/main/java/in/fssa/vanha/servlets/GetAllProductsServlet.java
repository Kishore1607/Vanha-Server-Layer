package in.fssa.vanha.servlets;

import java.io.IOException;
import java.util.Set;

// import javax.servlet.RequestDispatcher;
// import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class GetAllProductsByCategory
 */
@WebServlet("/home")
public class GetAllProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * This method handles HTTP GET requests and provides information about
	 * products.
	 *
	 * @param request  The HttpServletRequest object containing client request
	 *                 information.
	 * @param response The HttpServletResponse object for sending the response back
	 *                 to the client.
	 * @throws ServletException If there is an issue with the servlet operation.
	 * @throws IOException      If there is an I/O error while processing the
	 *                          request or response.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductService ps = new ProductService();

		String user = request.getHeader("Authorization");

		Gson gson = new Gson();

		Set<ListProductDTO> products = null;

		try {

			if (user == null) {
				products = ps.beforeLoginProducts();
			} else {
				String email = user.substring(7);
				if (email.startsWith("\"") && email.endsWith("\"")) {
					email = email.substring(1, email.length() - 1);
				}

				products = ps.findAllProducts(email);
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
