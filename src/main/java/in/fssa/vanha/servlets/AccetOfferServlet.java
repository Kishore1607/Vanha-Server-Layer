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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bid = request.getParameter("bidId");
		int id = Integer.parseInt(bid);
		
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		BidHistoryService bh = new BidHistoryService();
		try {
			String ids = bh.findBuyerProductIDs(id);
			
			String[] parts = ids.split("/");
			int productId = Integer.parseInt(parts[0]);
			int buyerId = Integer.parseInt(parts[1]);
					
            User user = UserService.userdetail(buyerId);
			
			if(user != null) {
				ProductService ps = new ProductService();
				AssetsService as = new AssetsService();	
				
				Product product = ps.productDetail(productId);
				String produtImage = as.findAssetByProductId(productId);
				
				OfferDTO detail = new OfferDTO();
				detail.setBuyerId(buyerId);
				detail.setBuyerImage(user.getImage());
				detail.setBuyerLocation(user.getLocation());
				detail.setBuyerEmail(user.getEmail());
				detail.setBuyerName(user.getName());
				detail.setBuyerNumber(user.getNumber());
				detail.setProductId(productId);
				detail.setProductImage(produtImage);
				detail.setProductName(product.getName());
				detail.setProductPrice(product.getPrice());	
				
				
				ResponseEntity res = new ResponseEntity();
				res.setStatusCode(200);
				res.setData(detail);
				res.setMessage("fetched successful");

				String responseJson = gson.toJson(res);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.write(responseJson);
				
			}else {
				ResponseEntity res = new ResponseEntity();
				res.setStatusCode(400);
				res.setData(user);
				res.setMessage("Invalid credentials");

				String responseJson = gson.toJson(res);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.write(responseJson);
			}
			
			
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
