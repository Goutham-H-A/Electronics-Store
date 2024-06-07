<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="model.Product"%>
<%@page import="database.ProductDAO"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Details</title>
<style type="text/css">
    body
    {
      background : url('reg_background.png');
      background-size: cover;
    }
  </style>
	<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Mukta:wght@800&display=swap" rel="stylesheet">
	<!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body >
	<!-- Grey with black text -->
<nav class="navbar navbar-expand-sm " style="background-color: #092961 ">
  <div class="container-fluid fw-bolder fs-5">
    <ul class="navbar-nav">

    <a class="navbar-brand" href="#">
      <img src="./asserts/logo.png" alt="Logo" style="width:40px;" class="rounded-pill"> 
    </a>
        	    <a class="navbar-brand" href="#" style="color:#f59542;">ELECTRONICS STORE</a>

      
      <li class="nav-item">
        <a class="nav-link text-light" href="buyer_home.jsp">View Products</a>
      </li>
      
    </ul>

    <ul class="navbar-nav ms-auto ">

      <li class="nav-item">
        <a class="nav-link text-light" href="login.html">Logout</a>
      </li>
      
    </ul>
    
  </div>
</nav>

  		<div class="container w-50 p-5 my-5 text-dark">
		<p class="fs-1 text-center" style="color:#219FFF;font-family: 'Mukta', sans-serif;">Thanks for your order</p>


<%!
Product product = null; 
%>
<% 
		int id = Integer.parseInt(request.getParameter("id"));
		ProductDAO dao = new ProductDAO();

%>
		<table class="table">
		<thead>
		<tr>
			<th class="table-light">Name</th>
			<th class="table-light">Description</th>
			<th class="table-light">Price</th>
		</tr>
		</thead>
		
		<tr>
			<td><%=product.getName() %></td>
			<td><%=product.getDescription()%></td>
			<td><%=product.getCost()%></td>
		</tr>
		
	</table>
	</div>
</body>
</html>