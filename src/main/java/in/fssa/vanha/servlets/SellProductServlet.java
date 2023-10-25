package in.fssa.vanha.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import in.fssa.vanha.exception.ServiceException;
import in.fssa.vanha.exception.ValidationException;
import in.fssa.vanha.model.ResponseEntity;
import in.fssa.vanha.service.ProductService;

/**
 * Servlet implementation class SellProductServlet
 */
@WebServlet("/home/profile/allbids/sell")
public class SellProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Handles a GET request to sell a product based on the provided parameters.
	 *
	 * This method extracts the product and bid identifiers from the 'refer'
	 * parameter in the request and attempts to sell the product. It then generates
	 * a JSON response with information about the result of the sale operation.
	 *
	 * @param request  The HttpServletRequest object representing the incoming HTTP
	 *                 request.
	 * @param response The HttpServletResponse object for sending the HTTP response.
	 *
	 * @throws ServletException If there is a servlet-related error.
	 * @throws IOException      If there is an I/O error while handling the request.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ids = request.getParameter("refer");

		String[] parts = ids.split("/");
		int productId = Integer.parseInt(parts[0]);
		int bidId = Integer.parseInt(parts[1]);

		Gson gson = new Gson();

		ProductService ps = new ProductService();
		try {
			ps.sellProduct(productId, bidId);
			ResponseEntity res = new ResponseEntity();
			res.setStatusCode(200);
			res.setData(1);
			res.setMessage("Sold successfully");

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
