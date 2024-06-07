package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.CartDAO;

@WebServlet("/DeleteCartProductServlet")
public class DeleteCartProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Read cartId parameter from request
        int cartId = Integer.parseInt(request.getParameter("cart_id"));
        
        // Delete item from cart using DAO
        CartDAO cartDAO = new CartDAO();
        int success = cartDAO.deleteCartItemByCartId(cartId);
        
        // Send response
        if (success > 0) {
            response.getWriter().println("Item deleted from cart successfully");
            RequestDispatcher rd = request.getRequestDispatcher("mycart.jsp");
			rd.forward(request, response);
        } else {
            response.getWriter().println("Failed to delete item from cart");
        }
    }
}
