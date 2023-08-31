<%@page import="in.fssa.vanha.model.Assets"%>
<%@page import="in.fssa.vanha.model.ProductDTO"%>
<%@page import="in.fssa.vanha.service.ProductService"%>
<%@page import="in.fssa.vanha.model.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
.product-container {
  border: 1px solid #ccc;
  padding: 20px;
  margin: 20px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}

.product-details {
  display: flex;
  flex-direction: column;
  margin-bottom: 20px;
}

.product-id, .product-price, .product-category {
  font-weight: bold;
  margin-bottom: 5px;
}

.product-category {
  color: #666;
}

.asset-value {
  margin-left: 10px;
}

</style>
<meta charset="ISO-8859-1">
<title>Product detail</title>
</head>
<body>
	<div class="product-container">
		<div class="product-details">
			<%
			p

				ProductDTO product = (ProductDTO) request.getAttribute("productDetails");
			%>
			<div class="product-id">
				Product ID:
				<%=product.getProduct().getProductId()%>
			</div>
			<div>
				Seller ID:
				<%=product.getProduct().getSellerUnique()%>
			</div>
			<div>
				Name:
				<%=product.getProduct().getName()%>
			</div>
			<div class="product-price">
				Price:
				<%=product.getProduct().getPrice()%>
			</div>
			<div>
				Used Period:
				<%=product.getProduct().getUsedPeriod()%>
			</div>
			<div>
				Used Duration:
				<%=product.getProduct().getUsedDuration()%>
			</div>
			<div class="product-category">
				Category:
				<%=product.getProduct().getCategory()%>
			</div>
			<div>
				Created At:
				<%=product.getProduct().getCreatedAt()%>
			</div>
			<div>
				Modified At:
				<%=product.getProduct().getModifiedAt()%>
			</div>
		</div>
		<%
		for (Assets asset : product.getAssets()) {
		%>
		<div>
			Asset value:
			<%=asset.getValue()%>
		</div>
		<%
		}
		%>
		<button>Edit</button>
		<button>Delete</button>
	</div>
</body>

</html>