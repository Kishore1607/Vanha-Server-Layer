package in.fssa.vanha.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.Gson;

import in.fssa.vanha.exception.ServiceException;
import in.fssa.vanha.exception.ValidationException;
import in.fssa.vanha.model.Assets;
import in.fssa.vanha.model.Product;
import in.fssa.vanha.model.ResponseEntity;
import in.fssa.vanha.service.ProductService;

/**
 * Servlet implementation class CreateProduct
 */
@WebServlet("/home/create")
public class CreateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Handles HTTP POST requests for creating a new product.
	 *
	 * This method processes incoming JSON data, extracts product information, and
	 * creates a new product in the system. It expects a JSON payload containing the
	 * following fields: - "unique" (String): The unique identifier for the product.
	 * - "name" (String): The name of the product. - "description" (String): A
	 * description of the product. - "price" (int): The price of the product. -
	 * "minimumPrice" (int): The minimum price for the product. - "date" (int): The
	 * period for which the product has been used. - "duration" (String): The
	 * duration of usage. - "category" (String): The category to which the product
	 * belongs. - "user_id" (String): The user identifier associated with the
	 * product.
	 * 
	 * It also allows for up to 4 optional asset fields, "asset1" to "asset4," which
	 * should be included in the JSON payload as needed.
	 * 
	 * Upon successful creation of the product, it returns a JSON response with a
	 * 200 status code and a success message. If there are any errors during the
	 * process, it provides appropriate error responses with status codes: - 500
	 * (Internal Server Error) for ServiceException. - 400 (Bad Request) for
	 * ValidationException.
	 * 
	 * @param request  HttpServletRequest object representing the incoming HTTP
	 *                 request.
	 * @param response HttpServletResponse object for sending the HTTP response.
	 *
	 * @throws ServletException if a servlet-specific error occurs.
	 * @throws IOException      if an I/O error occurs while processing the request
	 *                          or response.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		StringBuilder stringBuilder = new StringBuilder();
		String line;

		Gson gson = new Gson();

		while ((line = request.getReader().readLine()) != null) {
			stringBuilder.append(line);
		}

		JSONObject jsonObject = new JSONObject(stringBuilder.toString());

		String productId = jsonObject.getString("unique");
		String name = jsonObject.getString("name");
		String description = jsonObject.getString("description");
		int price = jsonObject.getInt("price");
		int minPrice = jsonObject.getInt("minimumPrice");
		int period = jsonObject.getInt("date");
		String duration = jsonObject.getString("duration");
		String category = jsonObject.getString("category");
		String email = jsonObject.getString("user_id");

		if (email.startsWith("\"") && email.endsWith("\"")) {
			email = email.substring(1, email.length() - 1);
		}

		List<Assets> assetArray = new ArrayList<>();

		for (int i = 1; i <= 4; i++) {
			String assetKey = "asset" + i;
			if (jsonObject.has(assetKey) && !jsonObject.isNull(assetKey)) {
				Assets asset = new Assets();
				asset.setValue(jsonObject.getString(assetKey));
				assetArray.add(asset);
			}
		}

		ProductService ps = new ProductService();
		Product p = new Product();
		p.setProductId(productId);
		p.setName(name);
		p.setDescription(description);
		p.setPrice(price);
		p.setMinPrice(minPrice);
		p.setUsedPeriod(period);
		p.setUsedDuration(duration);
		p.setCategory(category);

		try {
			ps.create(p, email);

			ResponseEntity res = new ResponseEntity();
			res.setStatusCode(200);
			res.setData(1);
			res.setMessage("Product deleted successfully");

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
