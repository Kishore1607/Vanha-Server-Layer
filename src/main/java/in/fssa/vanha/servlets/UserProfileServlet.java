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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getHeader("Authorization");
		String email = id.substring(7);
		if (email.startsWith("\"") && email.endsWith("\"")) {
			email = email.substring(1, email.length() - 1);
		}

		ProductService ps = new ProductService();

		List<ListProductDTO> products;
		User user;
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

			// Convert the DTO to JSON
			Gson gson = new Gson();
			String responseJson = gson.toJson(dto);

			// Set the response content type and character encoding
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			// Write the JSON response to the client
			response.getWriter().write(responseJson);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (ValidationException e) {
			e.printStackTrace();
		}

	}

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

		} catch (ValidationException e) {
			e.printStackTrace();
			// Handle validation errors and send an appropriate JSON response
		} catch (ServiceException e) {
			e.printStackTrace();
			// Handle service errors and send an appropriate JSON response
		}

		out.flush();

	}

}
