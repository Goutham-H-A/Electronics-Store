<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="model.Product"%>
<%@page import="database.ProductDAO"%>
<%@page import="java.util.ArrayList"%>
    

<!DOCTYPE html>
<html>
<head>
	<title></title>
	  <style type="text/css">
    body
    {
      background : url('reg_background.png');
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
<nav class="navbar navbar-expand-sm " style="background-color: #092961 ">
  <div class="container-fluid fw-bolder fs-5">
    <ul class="navbar-nav">

				<a class="navbar-brand" href="#"> <img src="./asserts/logo.png"
					alt="Logo" style="width: 50px;" class="rounded-pill">
				</a>
				<a class="navbar-brand" href="#" style="color: #f59542;">ELECTRONICS
					STORE</a>


				<li class="nav-item ">
        <a class="nav-link active text-light" href="admin_home.jsp">View Buyers</a>
      </li>
      <li class="nav-item">
        <a class="nav-link text-light" href="product.html">Add Product</a>
      </li>
      <li class="nav-item">
        <a class="nav-link text-light" href="view_products.jsp">View Products</a>
      </li>
    </ul>
    
  </div>
</nav>
  		<div class="container w-50 p-5 my-5 text-dark">
		<h1 style="text-align: center;">Product List</h1>

		<% 
      	ProductDAO dao = new ProductDAO();
	   	ArrayList<Product> plist = (ArrayList<Product>)dao.getProducts();
		if(plist.size()!=0){
		%>
		<table class="table table-danger table-striped table-bordered border-white border-5" style="aligm :center;">
	
			<thead>
				<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Description</th>
				<th>Price</th>
				<th colspan="2">Action</th>
				</tr>
			</thead>
			<tbody>
			<%for (Product product:plist){
	  			%>
				
				
				<tr>
					<td><%=product.getId()%></td>
					<td><%=product.getName()%></td>
					<td><%=product.getDescription()%></td>
					<td><%=product.getCost()%></td>
			 <td>
              <form method="post" action="DeleteProductServlet">
              <input type="hidden" name="product_id" value="<%= product.getId() %>" />
               <button type="submit" class="btn btn-danger btn-sm">Remove Product</button>
              </form>
            </td>

				</tr>
				
   	  </form>
		<%
   	  		}
   	  		%>
		
			</tbody>
		</table>
		<%
		} else {
		%><p style="text-align: center;">Admin has not added any products.</p><%} %>
    </div>




</body>
</html>