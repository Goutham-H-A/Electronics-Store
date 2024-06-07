package database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Cart;
import java.util.ArrayList;
import java.sql.ResultSet;

public class CartDAO {
    // Method to add an item to the cart
    public int addToCart(Cart cartItem) {
        int rowsAffected = 0;
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO cart (buyer_id, product_id, quantity) VALUES (?, ?, ?)")) {
            ps.setInt(1, cartItem.getBuyer_id());
            ps.setInt(2, cartItem.getProduct_id());
            ps.setInt(3, cartItem.getQuantity());
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Log the error or handle it as appropriate
        }
        return rowsAffected;
    }
    public ArrayList<Cart> getCartItemsByBuyerId(int buyerId) {
        ArrayList<Cart> cartItems = new ArrayList<>();
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM cart WHERE buyer_id = ?");
        ) {
            ps.setInt(1, buyerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Cart cartItem = new Cart();
                    cartItem.setCart_id(rs.getInt("cart_id"));
                    cartItem.setProduct_id(rs.getInt("product_id"));
                    cartItem.setQuantity(rs.getInt("quantity"));
                    // Add more setters as needed to populate the Cart object
                    cartItems.add(cartItem);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception as needed
        }
        return cartItems;
    }
    public int deleteCartItemByCartId(int cartId) {
        try {
        	Connection conn = DBConnector.getConnection();
            String query = "DELETE FROM cart WHERE cart_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, cartId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    // Add more methods for other cart-related operations (e.g., retrieveCartItems, updateCartItem, removeCartItem, etc.)
}
    public boolean isProductInCart(int buyerId, int productId) {
        String query = "SELECT COUNT(*) FROM cart WHERE buyer_id = ? AND product_id = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, buyerId);
            ps.setInt(2, productId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
