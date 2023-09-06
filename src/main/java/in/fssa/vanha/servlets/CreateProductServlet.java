package in.fssa.vanha.servlets;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.fssa.vanha.exception.ServiceException;
import in.fssa.vanha.exception.ValidationException;
import in.fssa.vanha.model.Assets;
import in.fssa.vanha.model.Product;
import in.fssa.vanha.model.User;
import in.fssa.vanha.service.ProductService;

/**
 * Servlet implementation class CreateProduct
 */
@WebServlet("/home/create")
public class CreateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String userId = user.getEmail();

		ProductService ps = new ProductService();
		Product p = new Product();
		String id = request.getParameter("product_id");
		p.setProductId(id);
		p.setName(request.getParameter("name"));
		p.setDescription(request.getParameter("description"));
		String sprice = request.getParameter("price");
		String smin_price = request.getParameter("min_price");
		String speriod = request.getParameter("period");

		int price = Integer.parseInt(sprice);
		int min_price = Integer.parseInt(smin_price);
		int period = Integer.parseInt(speriod);
		p.setPrice(price);
		p.setMinPrice(min_price);
		p.setUsedPeriod(period);
		p.setUsedDuration(request.getParameter("duration"));
		p.setCategory(request.getParameter("category"));

		Set<Assets> assets = new HashSet<>();
		for (int i = 1; i <= 4; i++) {
			Assets a = new Assets();
			a.setValue(request.getParameter("asset" + i));
			System.out.println(request.getParameter("asset" + i));
			assets.add(a);
		}
		try {
			ps.create(p, assets, userId);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (ValidationException e) {
			e.printStackTrace();
		}

		response.sendRedirect("./profile");
	}

}
