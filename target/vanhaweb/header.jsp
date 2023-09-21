<%@page import="in.fssa.vanha.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="dns-prefetch" href="//fonts.googleapis.com">
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
body {
	font-family: "Source Sans Pro", "Helvetica Neue", Arial, sans-serif;
}

.header {
	display: flex;
	justify-content: space-between;
	background-color: #222f30;
	margin-bottom: 20px;
	flex-wrap: wrap;
}

.logo {
	width: 140px;
	height: 50px;
	margin: 5px;
	background-color: rgba(0, 0, 0, 0.26);
}

.web-name {
	font-size: 26px;
	color: #ffeba7;
	margin: 15px;
	flex: 1;
	text-align: center;
}

.account-btn {
	margin: 15px 10px 0px;
}

.profile-img {
	width: 55px;
	height: 55px;
	margin: 5px 5px 0px 0px;
	border-radius: 50%;
}

.button {
	padding: 10px 20px;
	border-radius: 5px;
	margin-right: 10px;
	border: none;
	outline: none;
	background-color: rgb(102, 160, 219);
	color: white;
}
</style>
<meta charset="ISO-8859-1">
<title>header</title>
</head>
<body>

	<%
	HttpSession detail = request.getSession();
	User user = (User) detail.getAttribute("user");
	%>
	<%
	if (user == null) {
	%>
	<header class="header">
		<a href="<%=request.getContextPath()%>/home"><img
			src="https://iili.io/J90Gtvs.png" alt="logo" class="logo"></a>
		<h2 class="web-name">V A N H A</h2>
		<div class="account-btn">
			<a href="<%=request.getContextPath()%>/home/user"><button
					class="button">sign in</button></a>
		</div>
	</header>
	<%
	} else {
	%>
	<header class="header">
		<a href="<%=request.getContextPath()%>/home"><img
			src="https://iili.io/J90Gtvs.png" alt="logo" class="logo"></a>
		<h2 class="web-name">V A N H A</h2>
		<a href="<%=request.getContextPath()%>/home/profile"><img
			src="<%=user.getImage()%>" class="profile-img"></a>
	</header>
	<%
	}
	%>

</body>
</html>