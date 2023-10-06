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
import in.fssa.vanha.model.ResponseEntity;
import in.fssa.vanha.model.YourListDTO;
import in.fssa.vanha.service.BidHistoryService;

/**
 * Servlet implementation class ListCardServlet
 */
@WebServlet("/home/listproduct")
public class ListCardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("userEmail");
		
		Gson gson = new Gson();
		try {

			BidHistoryService bh = new BidHistoryService();
			List<YourListDTO> product = bh.listProductCards(email);
			System.out.println(product.toString());
			ResponseEntity res = new ResponseEntity();
			res.setStatusCode(200);
			res.setData(product);
			res.setMessage("product details fetched successfully");

			String responseJson = gson.toJson(res);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(responseJson);

		}  catch (ServiceException e) {
			String errorMessage = e.getMessage();
			ResponseEntity res = new ResponseEntity();
			res.setStatusCode(500);
			res.setMessage(errorMessage);

			String responseJson = gson.toJson(res);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(responseJson);

		} catch (ValidationException e) {
			String errorMessage = e.getMessage();
			ResponseEntity res = new ResponseEntity();
			res.setStatusCode(400);
			res.setMessage(errorMessage);

			String responseJson = gson.toJson(res);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(responseJson);
		}
	}

}
