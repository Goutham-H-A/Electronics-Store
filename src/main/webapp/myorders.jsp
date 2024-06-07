<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.Order"%>
<%@page import="model.Buyer"%>
<%@page import="model.Product"%>
<%@page import="database.OrderDAO"%>
<%@page import="database.ProductDAO"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Orders Page</title>
<!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<!-- Grey with black text -->
<nav class="navbar navbar-expand-sm " style="background-color:  #092961">
  <div class="container-fluid fw-bolder fs-5">
    <ul class="navbar-nav">
      <a class="navbar-brand">
        <img src="./asserts/logo.png" alt="Logo" style="width:50px;" class="rounded-pill"> 
      </a>
      <a class="navbar-brand" style="color:#f59542;">ELECTRONICS STORE</a>
      <li class="nav-item">
        <a class="nav-link text-light" href="buyer_home.jsp">View Products</a>
      </li>
      <li class="nav-item">
        <a class="nav-link text-light" href="profile.jsp">My Profile</a>
      </li>   
      <li class="nav-item">
        <a class="nav-link text-light" href="mycart.jsp">My Cart</a>
      </li>  
      <li class="nav-item">
        <a class="nav-link text-light" href="myorders.jsp">My Orders</a>
      </li>  
    </ul>
    <ul class="navbar-nav ms-auto ">
      <li class="nav-item">
        <a class="nav-link text-light " href="index.html">Logout</a>
      </li>
    </ul>
  </div>
</nav>

<div class="container w-75 p-5 my-5 text-dark">
  <h1 class="text-center mb-4">My Orders</h1>

  <div class="table-responsive">
    <table class="table table-striped table-bordered border-2 border-white">
      <thead class="table-danger">
        <tr>
          <th scope="col">My User ID</th>
          <th scope="col">Order Id</th>
          <th scope="col">Product Name</th>
          <th scope="col">Description</th>
          <th scope="col">Cost</th>
          <th scope="col">Address</th>
          <th scope="col">Action</th>
        </tr>
      </thead>
      <tbody>
        <% 
          // Fetch ordered items by buyer ID from the database
          Buyer buyer = (Buyer)session.getAttribute("buyer");  
          int buyerId = buyer.getId();
          OrderDAO orderDAO = new OrderDAO();
          ArrayList<Order> orderedItems = orderDAO.getOrderedItemsByBuyerId(buyerId);
		  double totalCost = 0.0;
          // Display ordered items in the table
          for (Order order : orderedItems) {
            int productIdOrders = order.getProduct_id();
            ProductDAO productDAO = new ProductDAO();
            Product product = productDAO.getProductDetailsById(productIdOrders);
            double subTotal = product.getCost();
            totalCost += subTotal;
        %>
        <tr>
          <td><%= buyerId %></td>
          <td><%= order.getOrder_id() %></td>
          <td><%= product.getName() %></td>
          <td><%= product.getDescription() %></td>
          <td>₹<%= product.getCost() %></td>
          <td><%= buyer.getAddress() %>
          <td>
            <form method="post" action="CancelOrderServlet">
              <input type="hidden" name="order_id" value="<%= order.getOrder_id()%>" />
              <button type="submit" class="btn btn-danger btn-sm">Cancel Order</button>
            </form>
          </td>
        </tr>
        <% } %>
      </tbody>
      <tfoot>
          <tr>
            <td colspan="4" class="text-end fw-bold">Total Amount To Pay</td>
            <td colspan="3" class="fw-bold">₹<%= totalCost %></td>
          </tr>
        </tfoot>
      
    </table>
  </div>

  <%-- Add a message if orders are empty --%>
  <% if (orderedItems.isEmpty()) { %>
  <div class="alert alert-info text-center" role="alert">
    Your orders will appear here.
  </div>
  <% } %>
</div>
<!-- Display the message -->
<div class="alert alert-warning text-center" role="alert">
  <%= request.getAttribute("message") %>
</div>


</body>
</html>
