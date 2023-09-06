package in.fssa.vanha.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.fssa.vanha.exception.ServiceException;
import in.fssa.vanha.exception.ValidationException;
import in.fssa.vanha.model.ProductDetailDTO;
import in.fssa.vanha.service.ProductService;

/**
 * Servlet implementation class EditProductServlet
 */
@WebServlet("/home/profile/productdetail/edit")
public class EditProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String productId = request.getParameter("productId");

		try {
			ProductDetailDTO product = ProductService.productdetail(productId);
			request.setAttribute("detail", product);
			RequestDispatcher rd = request.getRequestDispatcher("/update_product.jsp");
			rd.forward(request, response);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (ValidationException e) {
			e.printStackTrace();
		}

	}

}
