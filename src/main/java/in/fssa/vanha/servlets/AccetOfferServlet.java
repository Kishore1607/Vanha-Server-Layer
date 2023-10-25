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
import in.fssa.vanha.model.OfferDTO;
import in.fssa.vanha.model.Product;
import in.fssa.vanha.model.ResponseEntity;
import in.fssa.vanha.model.User;
import in.fssa.vanha.service.AssetsService;
import in.fssa.vanha.service.BidHistoryService;
import in.fssa.vanha.service.ProductService;
import in.fssa.vanha.service.UserService;

/**
 * Servlet implementation class AccetOfferServlet
 */
@WebServlet("/home/profile/allbids/buyer")
public class AccetOfferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Handles HTTP GET requests and responds with JSON data related to a bid.
	 *
	 * This method extracts the "bidId" parameter from the request, retrieves
	 * information about the buyer and the associated product, and returns it as a
	 * JSON response.
	 *
	 * @param request  An HttpServletRequest object containing the client's request.
	 * @param response An HttpServletResponse object used to send the server's
	 *                 response.
	 *
	 * @throws ServletException If there is an issue with servlet handling.
	 * @throws IOException      If there is an issue with input or output
	 *                          operations.
	 *
	 * @apiNote This method expects a "bidId" parameter in the request and returns
	 *          JSON data in the following format: { "statusCode": 200, // HTTP
	 *          status code "message": "Fetched successfully", // Response message
	 *          "data": { "buyerId": 123, // ID of the buyer "buyerImage":
	 *          "buyer_image_url", // URL to buyer's image "buyerLocation":
	 *          "buyer_location", // Location of the buyer "buyerEmail":
	 *          "buyer_email", // Email of the buyer "buyerName": "buyer_name", //
	 *          Name of the buyer "buyerNumber": "buyer_phone_number", // Phone
	 *          number of the buyer "productId": 456, // ID of the product
	 *          "productImage": "product_image_url", // URL to product image
	 *          "productName": "product_name", // Name of the product
	 *          "productPrice": 789.99 // Price of the product } }
	 *
	 * @apiNote In case of errors, the response will have an appropriate
	 *          "statusCode" and an error message. Possible status codes are: - 200:
	 *          Successful response. - 400: Invalid credentials or bad request. -
	 *          500: Internal server error.
	 *
	 * @apiNote This method uses a Gson library to convert objects to JSON.
	 *
	 * @apiNote Exception handling: - ServiceException: When a service error occurs,
	 *          it returns a 500 status with an error message. -
	 *          ValidationException: When a validation error occurs, it returns a
	 *          400 status with an error message.
	 *
	 * @see HttpServletRequest
	 * @see HttpServletResponse
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Extract the bidId parameter from the request.
		String bid = request.getParameter("bidId");
		int id = Integer.parseInt(bid);

		// Create a PrintWriter for sending the response.
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		BidHistoryService bh = new BidHistoryService();

		try {
			// Retrieve the buyer's and product's IDs from the bid.
			String ids = bh.findBuyerProductIDs(id);

			// Split the IDs into individual parts.
			String[] parts = ids.split("/");
			int productId = Integer.parseInt(parts[0]);
			int buyerId = Integer.parseInt(parts[1]);

			// Retrieve the user (buyer) details.
			User user = UserService.userdetail(buyerId);

			if (user != null) {
				// Retrieve product, product image, and create an OfferDTO.
				ProductService ps = new ProductService();
				AssetsService as = new AssetsService();

				Product product = ps.productDetail(productId);
				String productImage = as.findAssetByProductId(productId);

				OfferDTO detail = new OfferDTO();
				detail.setBuyerId(buyerId);
				detail.setBuyerImage(user.getImage());
				detail.setBuyerLocation(user.getLocation());
				detail.setBuyerEmail(user.getEmail());
				detail.setBuyerName(user.getName());
				detail.setBuyerNumber(user.getNumber());
				detail.setProductId(productId);
				detail.setProductImage(productImage);
				detail.setProductName(product.getName());
				detail.setProductPrice(product.getPrice());

				ResponseEntity res = new ResponseEntity();
				res.setStatusCode(200);
				res.setData(detail);
				res.setMessage("Fetched successfully");

				// Convert the response to JSON and send it.
				String responseJson = gson.toJson(res);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.write(responseJson);

			} else {
				// Handle the case when the user is not found.
				ResponseEntity res = new ResponseEntity();
				res.setStatusCode(400);
				res.setData(user);
				res.setMessage("Invalid credentials");

				// Convert the response to JSON and send it.
				String responseJson = gson.toJson(res);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.write(responseJson);
			}

		} catch (ServiceException e) {
			// Handle exceptions related to service errors.
			String errorMessage = e.getMessage();
			ResponseEntity res = new ResponseEntity();
			res.setStatusCode(500); // Internal Server Error
			res.setMessage(errorMessage);

			// Convert the response to JSON and send it.
			String responseJson = gson.toJson(res);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(responseJson);

		} catch (ValidationException e) {
			// Handle exceptions related to validation errors.
			String errorMessage = e.getMessage();
			ResponseEntity res = new ResponseEntity();
			res.setStatusCode(400); // Bad Request
			res.setMessage(errorMessage);

			// Convert the response to JSON and send it.
			String responseJson = gson.toJson(res);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(responseJson);
		}
	}

}
