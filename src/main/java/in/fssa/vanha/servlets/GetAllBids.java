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
import in.fssa.vanha.model.BidDTO;
import in.fssa.vanha.model.Product;
import in.fssa.vanha.model.ResponseEntity;
import in.fssa.vanha.service.AssetsService;
import in.fssa.vanha.service.BidHistoryService;
import in.fssa.vanha.service.ProductService;
import in.fssa.vanha.model.ViewBidsDTO;

/**
 * Servlet implementation class GetAllBids
 */
@WebServlet("/home/profile/allbids")
public class GetAllBids extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String productId = request.getParameter("productId");

		ViewBidsDTO viewBids = new ViewBidsDTO();
		
		Gson gson = new Gson();

		try {
			Product product = ProductService.ifExistsOrNot(productId);

			viewBids.setProductName(product.getName());
			viewBids.setProductPrice(product.getPrice());

			int id = product.getId();

			String image = AssetsService.findAssetByProductId(id);

			viewBids.setProductImage(image);

			BidHistoryService bidService = new BidHistoryService();

			List<BidDTO> bids = bidService.findAllBidsByProductId(id);

			viewBids.setBids(bids);

			ResponseEntity res = new ResponseEntity();
			res.setStatusCode(200);
			res.setData(viewBids);
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

}
