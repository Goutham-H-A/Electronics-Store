<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.Buyer"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Checkout</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            padding-top: 4rem;
        }
        .container {
            max-width: 800px;
        }
        .checkout-form {
            background-color: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.1);
        }
        .form-control:focus {
            border-color: #f59542;
            box-shadow: none;
        }
        .btn-checkout {
            background-color: #f59542;
            border: none;
            color: #fff;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .btn-checkout:hover {
            background-color: #e68a37;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="checkout-form">
            <h2 class="mb-4">Checkout</h2>
             <% 
          // Fetch ordered items by buyer ID from the database
          Buyer buyer = (Buyer)session.getAttribute("buyer");  
          int buyerId = buyer.getId();
          %>
                <div class="mb-3">
                    <label for="fullName" class="form-label">Full Name</label>
                    <input type="text" class="form-control" id="fullName" name="fullName"  value="<%= buyer.getName() %>" readonly required>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="email" value="<%= buyer.getMail() %>" readonly required>
                </div>
                <div class="mb-3">
                    <label for="address" class="form-label">Address</label>
                    <input type="email" class="form-control" id="email" name="email" value="<%= buyer.getAddress() %>" readonly required>
                </div>
                <div class="mb-3">
                    <label for="paymentMethod" class="form-label">Payment Method</label>
                    <select class="form-select" id="paymentMethod" name="paymentMethod" required>
                        <option value="">Select Payment Method</option>
                        <option value="paypal">Cash On Delivery</option>
                    </select>
                </div>
                <form action="PlaceOrderServlet" method="post">
                <input type="hidden" name="buyer_id" value="<%= buyerId %>" />
                <button type="submit" class="btn btn-checkout">Place Order</button>
            	</form>
            	
        </div>
    </div>
</body>
</html>
