package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.OrderDAO;

/**
 * Servlet implementation class BuyNowServlet
 */
@WebServlet("/BuyNowServlet")
public class BuyNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyNowServlet() {
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
        int productId = Integer.parseInt(request.getParameter("product_id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double amount = Double.parseDouble(request.getParameter("product_cost"));

        // Add item to order using DAO
        OrderDAO orderDAO = new OrderDAO();
        int success = orderDAO.BuyNow(buyerId, productId, quantity, amount);
        
        // Send response
        if (success > 0) {
            request.setAttribute("message", "Order placed successfully.");
            RequestDispatcher rd = request.getRequestDispatcher("myorders.jsp");
            rd.forward(request, response);
        } else {
            response.getWriter().println("Failed to place order");
        }
    }
    }
	


