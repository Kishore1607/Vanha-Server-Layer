package in.fssa.vanha.servlets;

import java.io.IOException;

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
import in.fssa.vanha.model.ProductDetailDTO;
import in.fssa.vanha.model.ResponseEntity;
import in.fssa.vanha.service.AssetsService;
import in.fssa.vanha.service.ProductService;

/**
 * Servlet implementation class SellerProductDetailServlet
 */
@WebServlet("/home/profile/productdetail")
public class SellerProductDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Handles HTTP GET requests to fetch product details by product ID.
	 *
	 * @param request  The HTTPServletRequest containing the "productId" parameter.
	 * @param response The HTTPServletResponse where the JSON response is written.
	 * @throws ServletException If a servlet-specific error occurs.
	 * @throws IOException      If an I/O error occurs while processing the request.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String productId = request.getParameter("productId");
		Gson gson = new Gson();

		try {
			ProductService productService = new ProductService();
			ProductDetailDTO product = productService.productdetail(productId);
			
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
	 * Handles HTTP POST requests for updating assets and associated product
	 * information.
	 *
	 * This method expects a JSON payload in the request body, which should include
	 * the following fields: - "imgSrc": The URL of the asset to be updated. - "id":
	 * The unique identifier for the asset to be updated. - "productId": The
	 * identifier of the associated product for the asset.
	 *
	 * Upon receiving the request, the method attempts to update the asset and its
	 * association with the specified product using the provided information. It
	 * also handles exceptions and provides appropriate JSON responses with status
	 * codes and error messages in case of any issues.
	 *
	 * @param request  The HttpServletRequest object containing the request data.
	 * @param response The HttpServletResponse object for sending responses.
	 * @throws ServletException If there is a servlet-related error.
	 * @throws IOException      If there is an error reading or writing data.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Gson gson = new Gson();

		StringBuilder stringBuilder = new StringBuilder();
		String line;
		try {
			while ((line = request.getReader().readLine()) != null) {
				stringBuilder.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		JSONObject jsonObject = new JSONObject(stringBuilder.toString());

		String imgSrc = jsonObject.getString("imgSrc");
		int id = jsonObject.getInt("id");
		String productId = jsonObject.getString("productId");

		AssetsService as = new AssetsService();

		Assets asset = new Assets();
		asset.setId(id);
		asset.setValue(imgSrc);

		int productid = -1;
		try {
			productid = ProductService.ifExistsOrNot(productId).getId();
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
		try {
			as.updateAssets(asset, productid);

			ResponseEntity res = new ResponseEntity();
			res.setStatusCode(200);
			res.setData(1);
			res.setMessage("Updated user details successfully");

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
