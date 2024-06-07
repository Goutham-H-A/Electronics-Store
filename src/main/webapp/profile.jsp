<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


	
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="database.DBConnector"%>
<%@page import="model.Buyer"%>
	
<!DOCTYPE html>
<html>
<head>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>

	<nav class="navbar navbar-expand-sm " style="background-color: #092961 ">
  <div class="container-fluid fw-bolder fs-5">
    <ul class="navbar-nav">

    <a class="navbar-brand" href="#">
      <img src="./asserts/logo.png" alt="Logo" style="width:50px;" class="rounded-pill"> 
    </a>
        	    <a class="navbar-brand" href="#" style="color:#f59542;">ELECTRONICS STORE</a>

      
      <li class="nav-item">
        <a class="nav-link text-light" href="buyer_home.jsp">View Products</a>
      </li>
   
     <li class="nav-item">
        <a class="nav-link text-light" href="profile.jsp">My Profile</a>
      </li>   
    </ul>

    <ul class="navbar-nav ms-auto ">

      <li class="nav-item">
        <a class="nav-link text-light" href="login.html">Logout</a>
      </li>
      
    </ul>
    
  </div>
</nav>
	
	<div class="container w-50 p-5 my-5 text-dark"
		style="border-radius: 20px;">

		<h1 style="text-align: center;">Profile Details</h1>
		<%
        String name = null;
		String mail = null;
		String mobile = null;
		String username = null;
		String password = null;
		String address = null;

        try
        {
        	
        	Buyer buyer=(Buyer)session.getAttribute("buyer");  
			int id = buyer.getId();
            String sql="SELECT * FROM Buyers WHERE ID='"+id+"'";

			Connection conn = DBConnector.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
          ResultSet rs=ps.executeQuery(sql);
          if(rs.next())
          {
        	  id=rs.getInt("id");
      		name =rs.getString("name");
      		mail=rs.getString("mail");
      		mobile=rs.getString("mobile");
      		username=rs.getString("username");
      		password=rs.getString("password");
      		address=rs.getString("address");
          }
        
      %>

		<table
			class="table table-success table-striped table-bordered border-white border-5">
			<tr>
				<th>ID</th>
				<td><%=id %></td>
			</tr>

			<tr>
				<th>Name</th>
				<td><%=name %></td>
			</tr>
			<tr>
				<th>Mail</th>
				<td><%=mail %></td>
			</tr>
			<tr>
				<th>Mobile</th>
				<td><%=mobile %></td>
			</tr>
			<tr>
				<th>Username</th>
				<td><%=username %></td>
			</tr>
			<tr>
				<th>Password</th>
				<td><%=password %></td>
			</tr>
			<tr>
				<th>Address</th>
				<td><%=address %></td>
			</tr>
		</table>
		<%
            
            DBConnector.closeStatement(ps);
        DBConnector.closeConnection(conn);

               
          } catch(Exception e)
                {
                  out.println(e.getMessage());
                }
      %>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

