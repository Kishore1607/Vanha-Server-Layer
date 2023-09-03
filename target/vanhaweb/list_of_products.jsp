<%@page import="in.fssa.vanha.model.ProductDTO"%>
<%@page import="java.util.Set"%>
<%@page import="in.fssa.vanha.service.ProductService"%>
<%@page import="in.fssa.vanha.model.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #dddddd;
}

.b2 {
	display: block;
	width: 80%;
	padding: 10px;
	background-color: #007bff;
	color: white;
	border: none;
	border-radius: 3px;
	margin: 10px;
	cursor: pointer;
}

.b2:hover {
	background-color: #0056b3;
}

.b1 {
	display: block;
	width: 20%;
	padding: 10px;
	background-color: #fff;
	border: 1px solid #25D366;
	border: none;
	border-radius: 3px;
	cursor: pointer;
	border: 1px solid #25D366;
}

.b1:hover {
	background-color: #25D366;
	color: #fff
}

a {
	text-decoration: none;
	color: white;
}
</style>
<meta charset="ISO-8859-1">
<title>All products of category</title>
</head>
<body>
	<a href="products/new">
		<button class="b1">Create Product</button>
	</a>

	<%
	Set<ProductDTO> productList = (Set<ProductDTO>) request.getAttribute("products");
	%>

	<table>
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Price</th>
			<th>Image</th>
			<th>Seller</th>
			<th>Location</th>
		</tr>
		<%
		for (ProductDTO product : productList) {
		%>
		<tr>
			<td id="id"><%=product.getProduct().getId()%></td>
			<td id="name"><%=product.getProduct().getName()%></td>
			<td id="price"><%=product.getProduct().getPrice()%></td>
			<td id="id"><%=product.getUser().getName()%></td>
			<td id="id"><%=product.getUser().getLocation()%></td>
			<td id="id"><%=product.getAsset().getValue()%></td>
			
			<td><a
				href="products/productdetail?productId=<%=product.getProduct().getProductId()%>">
					<button class="b2">View Detail</button>
			</a></td>
		</tr>
		<%
		}
		%>
	</table>
</body>
</html>