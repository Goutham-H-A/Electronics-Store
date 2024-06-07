package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Buyer;
import model.Order;
import model.Product;

public class OrderDAO {
	public ArrayList<Order> getOrders() throws SQLException
	{
	String query = "select * from orders";
	Connection con = DBConnector.getConnection();
	PreparedStatement statement = con.prepareStatement(query);
	ResultSet rs = statement.executeQuery();
	ArrayList<Order> orders = new ArrayList<Order>();
	while (rs.next())
	{
	Order order = new Order();
	
    order.setOrder_id(rs.getInt("Order_id"));
    order.setProduct_id(rs.getInt("product_id"));
    order.setBuyer_id(rs.getInt("buyer_id"));
    order.setQuantity(rs.getInt("quantity"));
    order.setProduct_cost(rs.getInt("amount"));
    // Add more setters as needed to populate the Cart object
    orders.add(order);
	}
	statement.close();
	DBConnector.closeConnection(con);
	return orders;
	}
    public ArrayList<Order> getOrderedItemsByBuyerId(int buyerId) {
        ArrayList<Order> OrderedItems = new ArrayList<>();
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM orders WHERE buyer_id = ?");
        ) {
            ps.setInt(1, buyerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Order OrderedItem = new Order();
                    OrderedItem.setOrder_id(rs.getInt("Order_id"));
                    OrderedItem.setProduct_id(rs.getInt("product_id"));
                    OrderedItem.setQuantity(rs.getInt("quantity"));
                    OrderedItem.setProduct_cost(rs.getInt("amount"));
                    // Add more setters as needed to populate the Cart object
                    OrderedItems.add(OrderedItem);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception as needed
        }
        return OrderedItems;
    }
    public int CancelOrderByOrderId(int orderId) {
        try {
        	Connection conn = DBConnector.getConnection();
            String query = "DELETE FROM orders WHERE order_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, orderId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    // Add more methods for other cart-related operations (e.g., retrieveCartItems, updateCartItem, removeCartItem, etc.)
}
    public int placeOrder(int buyerId) {
        int rowsAffected = 0;
        String selectSql = "SELECT * FROM cart WHERE buyer_id = ?";
        String insertSql = "INSERT INTO orders (buyer_id, product_id, quantity, amount) VALUES (?, ?, ?,?)";
        String deleteSql = "DELETE FROM cart WHERE buyer_id = ?";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement selectPs = conn.prepareStatement(selectSql);
             PreparedStatement insertPs = conn.prepareStatement(insertSql);
             PreparedStatement deletePs = conn.prepareStatement(deleteSql)) {

            // Begin transaction
            conn.setAutoCommit(false);

            // Select items from cart
            selectPs.setInt(1, buyerId);
            ResultSet rs = selectPs.executeQuery();

            // Insert each cart item into orders
            while (rs.next()) {
                int productId = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                double amount = rs.getDouble("amount");
                insertPs.setInt(1, buyerId);
                insertPs.setInt(2, productId);
                insertPs.setInt(3, quantity);
                insertPs.setDouble(4, amount);
                rowsAffected += insertPs.executeUpdate();
            }

            // If rows were inserted into orders, delete them from cart
            if (rowsAffected > 0) {
                deletePs.setInt(1, buyerId);
                deletePs.executeUpdate();
            }

            // Commit transaction
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            // Rollback transaction if any error occurs
            try (Connection conn = DBConnector.getConnection()) {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            rowsAffected = 0; // Indicate failure
        }

        return rowsAffected;
    }
    public int BuyNow(int buyerId, int productId, int quantity, double amount) {
        String sql = "INSERT INTO orders (buyer_id, product_id, quantity, amount) VALUES (?, ?, ?, ?)";
        int result = 0;

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, buyerId);
            statement.setInt(2, productId);
            statement.setInt(3, quantity);
            statement.setDouble(4, amount);

            result = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    
}
