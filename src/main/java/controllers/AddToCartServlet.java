package controllers;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cart;
import database.CartDAO;

@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    public AddToCartServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Read parameters from request
        int buyerId = Integer.parseInt(request.getParameter("buyer_id"));
        int productId = Integer.parseInt(request.getParameter("product_id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        
        // Create a Cart object
        Cart cartItem = new Cart();
        cartItem.setBuyer_id(buyerId);
        cartItem.setProduct_id(productId);
        cartItem.setQuantity(quantity);
        
        // Add item to cart using DAO
        CartDAO cartDAO = new CartDAO();
        
        if (cartDAO.isProductInCart(buyerId, productId)) {
            response.setContentType("text/plain");
            response.getWriter().write("Item is already in the cart.");
            
        } else {
            int success = cartDAO.addToCart(cartItem);
            if (success > 0) {
                request.setAttribute("message", "Item added to cart successfully.");
                RequestDispatcher rd = request.getRequestDispatcher("mycart.jsp");
    			rd.forward(request, response);
            } else {
                request.setAttribute("message", "Failed to add item to cart.");
            }
        
        }
    }
}

