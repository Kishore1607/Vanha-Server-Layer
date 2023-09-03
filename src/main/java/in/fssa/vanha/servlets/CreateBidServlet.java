package in.fssa.vanha.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.fssa.vanha.exception.ServiceException;
import in.fssa.vanha.exception.ValidationException;
import in.fssa.vanha.model.BidHistory;
import in.fssa.vanha.service.BidHistoryService;

/**
 * Servlet implementation class CreateBidServlet
 */
@WebServlet("/products/productdetail/bid")
public class CreateBidServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("productId");
		BidHistoryService bidService = new BidHistoryService();
		BidHistory bid = new BidHistory();
		bid.setBuyerUnique(request.getParameter("userEmail"));
		bid.setProductUnique(id);
		int amount = Integer.parseInt(request.getParameter("amount"));
		bid.setBidAmount(amount);
		
		response.sendRedirect("./../productdetail?productId="+ id);

		try {
			bidService.create(bid);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (ValidationException e) {
			e.printStackTrace();
		}
	}

}
