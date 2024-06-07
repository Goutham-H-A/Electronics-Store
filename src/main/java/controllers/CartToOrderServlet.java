package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import database.OrderDAO;
/**
 * Servlet implementation class CartToOrderServlet
 */
@WebServlet("/CartToOrderServlet")
public class CartToOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartToOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
		int buyerId = (int) session.getAttribute("buyerId");
	        
	        OrderDAO orderDAO = new OrderDAO();
	        int rowsAffected = orderDAO.placeOrder(buyerId);
	        
	        if (rowsAffected > 0) {
	            // Order placed successfully
	            session.removeAttribute("cartItems"); // Clear cart items from session
	            response.sendRedirect("myorders.jsp");
	        } else {
	            // Failed to place order
	            response.sendRedirect("error.jsp");
	        }
	}

}
