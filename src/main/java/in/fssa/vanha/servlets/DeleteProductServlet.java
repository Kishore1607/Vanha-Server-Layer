package in.fssa.vanha.servlets;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class DeleteProductServlet
 */
@WebServlet("/home/profile/productdetail/delete")
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Handles HTTP GET requests for deleting a product based on the provided
	 * productId.
	 * 
	 * @param request  The HttpServletRequest object containing request information.
	 * @param response The HttpServletResponse object for sending the response.
	 * @throws ServletException If an error occurs during servlet processing.
	 * @throws IOException      If an I/O error occurs during response handling.
	 * 
	 *                          This method performs the following tasks: 1.
	 *                          Retrieves the "productId" parameter from the
	 *                          request. 2. Calls the ProductService to delete the
	 *                          product identified by the productId. 3. If the
	 *                          deletion is successful, it sends a JSON response
	 *                          with a success message. - Status Code: 200 (OK) -
	 *                          Response Data: 1 - Response Message: "Product
	 *                          deleted successfully" 4. If a ServiceException is
	 *                          caught, it sends a JSON response with an error
	 *                          message indicating an internal server error. -
	 *                          Status Code: 500 (Internal Server Error) - Response
	 *                          Message: Error message from the ServiceException. 5.
	 *                          If a ValidationException is caught, it sends a JSON
	 *                          response with an error message indicating a bad
	 *                          request. - Status Code: 400 (Bad Request) - Response
	 *                          Message: Error message from the ValidationException.
	 * 
	 *                          The response is formatted as JSON using the Gson
	 *                          library. The response content type is set to
	 *                          "application/json". Character encoding is set to
	 *                          UTF-8.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("productId");

		ProductService ps = new ProductService();

		PrintWriter out = response.getWriter();
		try {
			ps.delete(id);
			ResponseEntity res = new ResponseEntity();
			res.setStatusCode(200);
			res.setData(1);
			res.setMessage("Product deleted successfully");

			Gson gson = new Gson();
			String responseJson = gson.toJson(res);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.write(responseJson);

		} catch (ServiceException e) {
			String errorMessage = e.getMessage();
			ResponseEntity res = new ResponseEntity();
			res.setStatusCode(500); // Internal Server Error
			res.setMessage(errorMessage);

			Gson gson = new Gson();
			String responseJson = gson.toJson(res);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(responseJson);

		} catch (ValidationException e) {
			String errorMessage = e.getMessage();
			ResponseEntity res = new ResponseEntity();
			res.setStatusCode(400); // Bad Request
			res.setMessage(errorMessage);

			Gson gson = new Gson();
			String responseJson = gson.toJson(res);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(responseJson);
		}

	}
}
