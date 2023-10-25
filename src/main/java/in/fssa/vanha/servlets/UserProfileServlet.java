package in.fssa.vanha.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
import in.fssa.vanha.model.User;
import in.fssa.vanha.model.UserProductDTO;
import in.fssa.vanha.service.ProductService;
import in.fssa.vanha.service.UserService;

/**
 * Servlet implementation class UserProfileServlet
 */
@WebServlet("/home/profile")
public class UserProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * This method handles HTTP GET requests to fetch user product details by email.
	 *
	 * @param request  The HttpServletRequest object containing the request data.
	 * @param response The HttpServletResponse object for sending the response.
	 * @throws ServletException If there's a servlet-related exception.
	 * @throws IOException      If there's an I/O-related exception.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("userEmail");

		ProductService ps = new ProductService();

		List<ListProductDTO> products;
		User user;

		Gson gson = new Gson();
		try {
			products = ps.findAllProductsBySellerId(email);
			user = UserService.findUserByEmail(email);
			UserProductDTO dto = new UserProductDTO();
			dto.setProducts(products);
			dto.setUser(user);

			ResponseEntity res = new ResponseEntity();
			res.setStatusCode(200);
			res.setData(dto);
			res.setMessage("User product details fetched successfully");

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

	/**
	 * Handles HTTP POST requests for updating user image.
	 *
	 * This method expects a JSON request body containing user information and uses
	 * the UserService to update the user's image. It then constructs and sends a
	 * JSON response to the client.
	 *
	 * @param request  The HTTP servlet request object.
	 * @param response The HTTP servlet response object.
	 * @throws ServletException If a servlet-specific error occurs.
	 * @throws IOException      If an I/O error occurs.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		Gson gson = new Gson();

		try {

			BufferedReader reader = request.getReader();
			StringBuilder requestBody = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				requestBody.append(line);
			}

			User user = gson.fromJson(requestBody.toString(), User.class);

			UserService us = new UserService();

			us.updateImage(user);

			ResponseEntity res = new ResponseEntity();
			res.setStatusCode(200);
			res.setData(1);
			res.setMessage("Image changed successful");

			String responseJson = gson.toJson(res);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.write(responseJson);

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

		out.flush();

	}

}
