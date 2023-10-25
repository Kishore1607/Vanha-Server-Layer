package in.fssa.vanha.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import in.fssa.vanha.exception.ServiceException;
import in.fssa.vanha.exception.ValidationException;
import in.fssa.vanha.model.ResponseEntity;
import in.fssa.vanha.model.YourListDTO;
import in.fssa.vanha.service.BidHistoryService;

/**
 * Servlet implementation class ListCardServlet
 */
@WebServlet("/home/listproduct")
public class ListCardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Handles a GET request and retrieves product card details based on a user's email.
	 *
	 * This method processes incoming HTTP GET requests and returns a JSON response
	 * containing a list of product cards associated with the provided user's email.
	 *
	 * @param request  The HttpServletRequest object containing request parameters.
	 * @param response The HttpServletResponse object for sending the response.
	 *
	 * @throws ServletException  If there is an issue with the servlet or request handling.
	 * @throws IOException      If there is an issue with input/output operations.
	 *
	 * This method performs the following actions:
	 * 1. Extracts the "userEmail" parameter from the request.
	 * 2. Retrieves a list of product cards using the BidHistoryService.
	 * 3. Constructs a JSON response with status code, data, and message.
	 * 4. Writes the JSON response to the HttpServletResponse.
	 *
	 * Exception Handling:
	 * - ServiceException: If there is a service-related error, a 500 status response is generated.
	 * - ValidationException: If there is a validation error, a 400 status response is generated.
	 *
	 * The response format:
	 * - Success (HTTP 200):
	 *   {
	 *     "statusCode": 200,
	 *     "message": "product details fetched successfully",
	 *     "data": [List of product card data]
	 *   }
	 * - Service Error (HTTP 500):
	 *   {
	 *     "statusCode": 500,
	 *     "message": "Error message describing the issue"
	 *   }
	 * - Validation Error (HTTP 400):
	 *   {
	 *     "statusCode": 400,
	 *     "message": "Validation error message"
	 *   }
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("userEmail");

		Gson gson = new Gson();
		try {

			BidHistoryService bh = new BidHistoryService();
			List<YourListDTO> product = bh.listProductCards(email);
			ResponseEntity res = new ResponseEntity();
			res.setStatusCode(200);
			res.setData(product);
			res.setMessage("product details fetched successfully");

			String responseJson = gson.toJson(res);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(responseJson);

		} catch (ServiceException e) {
			String errorMessage = e.getMessage();
			ResponseEntity res = new ResponseEntity();
			res.setStatusCode(500);
			res.setMessage(errorMessage);

			String responseJson = gson.toJson(res);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(responseJson);

		} catch (ValidationException e) {
			String errorMessage = e.getMessage();
			ResponseEntity res = new ResponseEntity();
			res.setStatusCode(400);
			res.setMessage(errorMessage);

			String responseJson = gson.toJson(res);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(responseJson);
		}
	}

}
