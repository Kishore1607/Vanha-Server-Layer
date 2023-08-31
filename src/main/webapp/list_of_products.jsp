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
	Set<Product> productList = (Set<Product>) request.getAttribute("products");
	%>

	<table>
		<tr>
			<th>ID</th>
			<th>Product Id</th>
			<th>Seller Id</th>
			<th>Name</th>
			<th>Price</th>
			<th>Description</th>
			<th>Used Period</th>
			<th>Used Duration</th>
			<th>Category</th>
			<th>Created At</th>
			<th>Modified At</th>
		</tr>
		<%
		for (Product product : productList) {
		%>
		<tr>
			<td id="id"><%=product.getId()%></td>
			<td id="id"><%=product.getProductId()%></td>
			<td id="id"><%=product.getSellerId()%></td>
			<td id="name"><%=product.getName()%></td>
			<td id="price"><%=product.getPrice()%></td>
			<td id="description"><%=product.getDescription()%></td>
			<td id="period"><%=product.getUsedPeriod()%></td>
			<td id="duration"><%=product.getUsedDuration()%></td>
			<td id="id"><%=product.getCategory()%></td>
			<td id="id"><%=product.getCreatedAt()%></td>
			<td id="id"><%=product.getModifiedAt()%></td>
			<td><a
				href="products/productdetail?productId=<%=product.getProductId()%>">
					<button class="b2">View Detail</button>
			</a></td>
		</tr>
		<%
		}
		%>
	</table>
</body>
</html>