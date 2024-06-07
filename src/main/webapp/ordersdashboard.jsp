<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="model.Buyer"%>
<%@page import="database.BuyerDAO"%>
<%@page import="database.ProductDAO"%>
<%@page import="model.Order"%>
<%@page import="database.OrderDAO"%>
<%@page import="java.util.ArrayList"%>
    

<!DOCTYPE html>
<html>
<head>
	<title></title>
	  <style type="text/css">
    body
    {
      background : url('background.jpeg');
      background-size: cover;
    }
  </style>
	
	<!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body >
	<!-- Grey with black text -->
<nav class="navbar navbar-expand-sm py-3 border border-light" style="background-color: #092961">
  <div class="container-fluid fw-bolder fs-5">
    <ul class="navbar-nav">

<li>
        <a class="navbar-brand" href="#">
      <img src="./asserts/logo.png" alt="Logo" style="width:40px;" class="rounded-pill"> 
    </a>
        	    <a class="navbar-brand" href="#"style="color:#a9c4f2;">ELECTRONICS STORE</a>
        	    </li>

	 <li class="nav-item ">
        <a class="nav-link active text-light" href="admin_home.jsp">View Buyers</a>
      </li>
      <li class="nav-item">
        <a class="nav-link text-light" href="product.html">Add Product</a>
      </li>
      <li class="nav-item">
        <a class="nav-link text-light" href="view_products.jsp">View Products</a>
      </li>
      <li class="nav-item ">
        <a class="nav-link active text-light" href="ordersdashboard.jsp">View Orders</a>
      </li>
    </ul>

    <ul class="navbar-nav ms-auto ">

      <li class="nav-item">
        <a class="nav-link " style="color:white;" href="login.html">Logout</a>
      </li>
      
    </ul>
    
  </div>
</nav>

  		<div class="container w-50 p-5 my-5 text-dark">
		<h1 style="text-align: center;">Total Orders List</h1>

		<%
		OrderDAO dao = new OrderDAO();
			   	ArrayList<Order> olist = (ArrayList<Order>)dao.getOrders();
				if(olist.size()!=0){
				int count = 0;
		%>
		<table class="table table-danger table-striped table-bordered border-white border-5">
	
			<thead>
				<tr>
				<th>SI NO</th>
				<th>Buyer Id</th>
				<th>Order Id</th>
				<th>Product Id</th>
				<th>Product Quantity</th>
				</tr>
			</thead>
			<tbody>
			<%for (Order order:olist){
	  			count++;%>
    			<tr>
      <th scope="row"><%=count%></th>
      <td><%= order.getBuyer_id() %></td>
      <td><%= order.getOrder_id() %></td>
   	  <td><%= order.getProduct_id() %></td>
   	  <td><%= order.getQuantity() %></td>
   	  </tr> 
		<%}%>
			
			</tbody>
		</table>
		<%
		}else{ %><p style="text-align: center;">Ordered items will Available here</p><%} %>
    </div>
</div>
</body>
</html>