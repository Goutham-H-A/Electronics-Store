package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.OrderDAO;
import model.Order;

/**
 * Servlet implementation class PlaceOrderServlet
 */
@WebServlet("/PlaceOrderServlet")
public class PlaceOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlaceOrderServlet() {
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
		 int buyerId = Integer.parseInt(request.getParameter("buyer_id"));
	        
	        // Add item to order using DAO
	        OrderDAO orderDAO = new OrderDAO();
	        int success = orderDAO.placeOrder(buyerId);
	        
	        // Send response
	        if (success > 0) {
	        	request.setAttribute("message", "Order Placed successfully.");
                RequestDispatcher rd = request.getRequestDispatcher("myorders.jsp");
    			rd.forward(request, response);
	        } else {
	            response.getWriter().println("Failed to place Order");
	        }
	}

}
