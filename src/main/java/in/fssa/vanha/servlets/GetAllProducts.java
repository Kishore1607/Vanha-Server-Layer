package in.fssa.vanha.servlets;

import java.io.IOException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.fssa.vanha.exception.ServiceException;
import in.fssa.vanha.model.Product;
import in.fssa.vanha.service.ProductService;

/**
 * Servlet implementation class GetAllProductsByCategory
 */
@WebServlet("/products")
public class GetAllProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductService ps = new ProductService();
		try {
			Set<Product> product = ps.findAllProducts();

			request.setAttribute("products", product);
			RequestDispatcher dispatcher = request.getRequestDispatcher("list_of_products.jsp");
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

}
