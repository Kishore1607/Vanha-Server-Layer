package in.fssa.vanha.model;

import java.util.List;

public class ViewBidsDTO {

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public List<BidDTO> getBids() {
		return bids;
	}

	public void setBids(List<BidDTO> bids) {
		this.bids = bids;
	}

	private String productName;
	@Override
	public String toString() {
		return "ViewBidsDTO [productName=" + productName + ", productPrice=" + productPrice + ", productImage="
				+ productImage + ", bids=" + bids + "]";
	}

	private int productPrice;
	private String productImage;
	private List<BidDTO> bids;

}
