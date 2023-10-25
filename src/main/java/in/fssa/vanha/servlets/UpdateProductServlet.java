package in.fssa.vanha.servlets;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import in.fssa.vanha.exception.ServiceException;
import in.fssa.vanha.exception.ValidationException;
import in.fssa.vanha.model.Product;
import in.fssa.vanha.model.ResponseEntity;
import in.fssa.vanha.service.ProductService;

/**
 * Servlet implementation class UpdateProductServlet
 */
@WebServlet("/home/profile/productdetail/update")
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * This method handles HTTP POST requests to update product details.
	 *
	 * @param request  The HttpServletRequest object containing the request data.
	 * @param response The HttpServletResponse object for sending the response.
	 * @throws ServletException If there's a servlet-related exception.
	 * @throws IOException      If there's an I/O-related exception.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BufferedReader reader = request.getReader();
		StringBuilder requestBody = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			requestBody.append(line);
		}

		Gson gson = new Gson();
		Product productRequest = gson.fromJson(requestBody.toString(), Product.class);

		ProductService ps = new ProductService();
		Product p = new Product();
		String id = productRequest.getProductId();
		p.setProductId(id);
		p.setName(productRequest.getName());
		p.setDescription(productRequest.getDescription());
		p.setUsedDuration(productRequest.getUsedDuration());
		p.setPrice(productRequest.getPrice());
		p.setMinPrice(productRequest.getMinPrice());
		p.setUsedPeriod(productRequest.getUsedPeriod());

		try {
			ps.update(p);
			ResponseEntity res = new ResponseEntity();
			res.setStatusCode(200);
			res.setData(1);
			res.setMessage("Updated product details successfully");

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
