<%@page import="in.fssa.vanha.model.ListProductDTO"%>
<%@page import="java.util.Set"%>
<%@page import="in.fssa.vanha.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon"
	href="../assets/img/illustration/v-removebg-preview.png">
<link rel="dns-prefetch" href="//fonts.googleapis.com">
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css"
	integrity="sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w=="
	crossorigin="anonymous" referrerpolicy="no-referrer">
<style>
body {
	text-align: center;
	font-family: "Source Sans Pro", "Helvetica Neue", Arial, sans-serif;
}
/* ----profile------ */
.profile-card {
	margin: 0% 0% 2% 35%;
	text-align: center;
	padding: 20px;
	margin-top: 50px;
	width: 350px;
	border-radius: 0% 100% 0% 100%/70% 27% 73% 30%;
	background-color: rgb(214, 214, 214);
}

.title {
	color: var(--fifth);
	font-size: 35px;
}

.user-img {
	width: 180px;
	border-radius: 50%;
	padding: 15px;
	background-color: rgb(118, 117, 117);
	box-shadow: rgba(68, 68, 68, 0.25) 0px 25px 50px -12px;
}

.caption {
	text-align: center;
	width: 150px;
}

.caption i {
	padding: 5px 0px 10px 10px;
}

.caption h4 {
	margin-top: 0px;
	padding: 0px 0px 10px 10px;
	border-style: solid;
	border-color: rgb(192, 192, 192);
	border-width: 0px 0px 2px 0px;
}

.button1 {
	width: 150px;
	padding: 10px 20px;
	border-radius: 5px;
	margin-top: 30px;
	margin-right: 10px;
	border: none;
	outline: none;
	-webkit-transition: .4s ease-in-out;
	transition: .4s ease-in-out;
	background-color: #252525;
	color: white;
	padding: 10px 20px;
}

.button1:hover {
	background-color: rgb(102, 160, 219);
	color: white;
}

.button2 {
	padding: 10px 20px;
	margin-right: 1200px;
	border-radius: 5px;
	border: none;
	outline: none;
	-webkit-transition: .4s ease-in-out;
	transition: .4s ease-in-out;
	background-color: #252525;
	color: white;
}

.button2:hover {
	background-color: rgb(93, 220, 93);
	color: white;
}

.details h4 {
	color: var(--fifth);
}
/* ----------product----------------------- */
.product_img {
	max-width: 100%;
	width: 270px;
	height: 250px;
	border-radius: 10px;
}

.card {
	width: 90%;
	height: auto;
	border-radius: 20px;
	background: #f5f5f5;
	padding: 10px;
	border: 2px solid #c3c6ce;
	transition: 0.5s ease-out;
}

.card-details {
	color: black;
	display: grid;
	place-content: center;
}

.text-body {
	display: flex;
}

.text-body span {
	padding-right: 5px;
}

.text-title {
	font-weight: bold;
}

.card:hover {
	border-color: var(--primary);
	background-color: rgb(233, 234, 255);
	box-shadow: 0 4px 18px 0 rgba(0, 0, 0, 0.25);
}

.grid-container {
	display: grid;
	grid-template-columns: repeat(3, 300px);
	justify-content: space-evenly;
	row-gap: 40px;
	margin: 50px 0px;
}

.algn {
	margin-top: 50px;
}
</style>
<title>Profile page</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<%
	User user = (User) request.getAttribute("user");
	Set<ListProductDTO> products = (Set<ListProductDTO>) request.getAttribute("products");
	%>
	<div class="profile-card">
		<img src="<%=user.getImage()%>" class="user-img"
			alt="<%=user.getName()%> Image">
		<div class="details">
			<h2 class="title" id="user_name"><%=user.getName()%></h2>
			<span class="caption"> <i class="fa fa-envelope"></i>
				<p><%=user.getEmail()%></p>
			</span> <span class="caption"> <i class="fa fa-phone"></i>
				<p><%=user.getNumber()%></p>
			</span> <span class="caption"> <i class="fa fa-location"></i>
				<p><%=user.getLocation()%></p>
			</span>
			<div class="btn">
				<a href="./edit">
					<button class="button1" id="form_on">Edit</button>
				</a>
			</div>
		</div>
	</div>
	<a href="./new">
		<button class="button2">Add new product</button>
	</a>
	<%
	if (products != null) {
	%>
	<div class="grid-container">
		<%
		for (ListProductDTO product : products) {
		%>
		<div class="card">
			<div class="card-details">
				<a
					href="profile/productdetail?productId=<%=product.getProductId()%>">
					<img src="<%=product.getAsset()%>"
					alt="<%=product.getProductName()%> Image" class="product_img">
				</a>
				<h3 class="text-title"><%=product.getProductName()%></h3>
			</div>
			<div class="text-body">
				<span> <b>Price:</b> <%=product.getPrice()%> (INR)
				</span>
			</div>

		</div>
		<%
		}
		%>
	</div>
	<%
	}
	%>
</body>
</html>