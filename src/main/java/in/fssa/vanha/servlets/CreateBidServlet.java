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
import in.fssa.vanha.service.BidHistoryService;

/**
 * Servlet implementation class CreateBidServlet
 */
@WebServlet("/home/productdetail/bid")
public class CreateBidServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Handles the HTTP POST request for creating a bid in a bid history system.
	 *
	 * This method is responsible for processing incoming POST requests and creating
	 * bid records in the bid history system. It expects the following parameters in
	 * the request:
	 *
	 * - 'buyer' (String): The name or identifier of the buyer placing the bid. -
	 * 'amount' (int): The bid amount (in the currency of your application). -
	 * 'productid' (String): The unique identifier of the product for which the bid
	 * is being placed.
	 *
	 * The method performs the following actions:
	 *
	 * 1. Parses the incoming request parameters. 2. Creates a new bid record in the
	 * bid history using the BidHistoryService. 3. Handles different exceptions that
	 * might occur during the bid creation process. - If the bid is created
	 * successfully, it returns a JSON response with a 200 (OK) status code and a
	 * success message. - If a ServiceException occurs, it returns a JSON response
	 * with a 500 (Internal Server Error) status code and an error message. - If a
	 * ValidationException occurs, it returns a JSON response with a 400 (Bad
	 * Request) status code and a validation error message.
	 *
	 * @param request  The HttpServletRequest object representing the incoming HTTP
	 *                 request.
	 * @param response The HttpServletResponse object for sending the HTTP response.
	 *
	 * @throws ServletException If a servlet-related exception occurs.
	 * @throws IOException      If an IO-related exception occurs while writing the
	 *                          response.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String buyer = request.getParameter("buyer");
		int amount = Integer.parseInt(request.getParameter("amount"));
		String productId = request.getParameter("productid");

		BidHistoryService bidService = new BidHistoryService();

		Gson gson = new Gson();

		try {
			bidService.create(amount, buyer, productId);

			ResponseEntity res = new ResponseEntity();
			res.setStatusCode(200);
			res.setData(1);
			res.setMessage("Bided successful");

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
