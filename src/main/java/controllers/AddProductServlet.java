package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.BuyerDAO;
import database.ProductDAO;
import model.Buyer;
import model.Product;

/**
 * Servlet implementation class AddProductServelet
 */
@WebServlet("/AddProductServlet")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductServlet() {
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
		//Read the inputs
				String name = request.getParameter("name");
				String description = request.getParameter("description");
				double cost = Double.parseDouble(request.getParameter("cost"));
		
	   //Create object of Model Class-Buyer
				Product product = new Product();
				product.setName(name);
				product.setDescription(description);
				product.setCost(cost);
				
				ProductDAO dao = new ProductDAO();
				int success = dao.addProduct(product);
				if(success>0) {
					System.out.println("Product added is successful");
					RequestDispatcher rd = request.getRequestDispatcher("view_products.jsp");
					rd.forward(request, response);
				}
				else {
					System.out.println("Product added is failed");
				}
	}

}
