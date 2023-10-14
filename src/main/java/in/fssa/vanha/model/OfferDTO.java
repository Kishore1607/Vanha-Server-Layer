package in.fssa.vanha.model;

public class OfferDTO {

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public String getBuyerImage() {
		return buyerImage;
	}

	public void setBuyerImage(String buyerImage) {
		this.buyerImage = buyerImage;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public long getBuyerNumber() {
		return buyerNumber;
	}

	public void setBuyerNumber(long buyerNumber) {
		this.buyerNumber = buyerNumber;
	}

	public String getBuyerLocation() {
		return buyerLocation;
	}

	public void setBuyerLocation(String buyerLocation) {
		this.buyerLocation = buyerLocation;
	}
	
	public String getBuyerEmail() {
		return buyerEmail;
	}

	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}

	public int getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}



	@Override
	public String toString() {
		return "OfferDTO [productName=" + productName + ", productImage=" + productImage + ", productPrice="
				+ productPrice + ", productId=" + productId + ", buyerImage=" + buyerImage + ", buyerName=" + buyerName
				+ ", buyerNumber=" + buyerNumber + ", buyerLocation=" + buyerLocation + ", buyerEmail=" + buyerEmail
				+ ", buyerId=" + buyerId + "]";
	}



	private String productName;
	private String productImage;
	private int productPrice;
	private int productId;
	private String buyerImage;
	private String buyerName;
	private long buyerNumber;
	private String buyerLocation;
	private String buyerEmail;
	private int buyerId;
}
