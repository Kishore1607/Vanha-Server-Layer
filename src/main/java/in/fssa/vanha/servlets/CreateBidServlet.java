package in.fssa.vanha.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.fssa.vanha.exception.ServiceException;
import in.fssa.vanha.exception.ValidationException;
import in.fssa.vanha.service.BidHistoryService;

/**
 * Servlet implementation class CreateBidServlet
 */
@WebServlet("/home/productdetail/bid")
public class CreateBidServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String productId = request.getParameter("productId");
		System.out.println(productId);
		String userId = request.getParameter("userEmail");
		System.out.println(userId);
		BidHistoryService bidService = new BidHistoryService();

		int amount = Integer.parseInt(request.getParameter("amount"));

		try {
			bidService.create(amount, userId, productId);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (ValidationException e) {
			e.printStackTrace();
		}
		response.sendRedirect("./../productdetail?productId=" + productId);
	}

}
