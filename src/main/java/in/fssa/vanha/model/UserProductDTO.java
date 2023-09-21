package in.fssa.vanha.model;

import java.util.List;

public class UserProductDTO {
	private User user;
	private List<ListProductDTO> products;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<ListProductDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ListProductDTO> products2) {
		this.products = products2;
	}
}
