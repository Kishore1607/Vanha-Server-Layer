package in.fssa.vanha.servlets;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.fssa.vanha.exception.ServiceException;
import in.fssa.vanha.exception.ValidationException;
import in.fssa.vanha.model.Assets;
import in.fssa.vanha.model.Product;
import in.fssa.vanha.service.ProductService;

/**
 * Servlet implementation class UpdateProductServlet
 */
@WebServlet("/home/profile/productdetail/update")
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ProductService ps = new ProductService();
		Product p = new Product();
		String id = request.getParameter("product_id");
		System.out.println(id);
		p.setProductId(id);
		p.setName(request.getParameter("name"));
		System.out.print(request.getParameter("name"));
		p.setDescription(request.getParameter("description"));
		p.setUsedDuration(request.getParameter("duration"));
		String sprice = request.getParameter("price");
		String smin_price = request.getParameter("min_price");
		String speriod = request.getParameter("period");

		int price = Integer.parseInt(sprice);
		int min_price = Integer.parseInt(smin_price);
		int period = Integer.parseInt(speriod);
		p.setPrice(price);
		p.setMinPrice(min_price);
		p.setUsedPeriod(period);

		String[] asetIds = request.getParameterValues("asetId");
		String[] urls = request.getParameterValues("url");
		
		Set<Assets> assetsList = new HashSet<>();

		if (asetIds != null && urls != null && asetIds.length == urls.length) {

			for (int i = 0; i < asetIds.length; i++) {
				String asetId = asetIds[i];
				String url = urls[i];

				Assets asset = new Assets();
				asset.setId(Integer.parseInt(asetId));
				asset.setValue(url);

				assetsList.add(asset);
			}
		}

		try {
			ps.update(p, assetsList);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (ValidationException e) {
			e.printStackTrace();
		}

		String newURL = request.getContextPath() + "/home/profile/productdetail?productId=" + id;
		response.sendRedirect(newURL);
	}

}
