package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Product;

public class ProductDAO {
	
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	public int addProduct(Product product) 
	{
		String sql = "insert into products(Product_name,Product_description,Product_cost) values ('"+product.getName()+"','"+product.getDescription()+"','"+product.getCost()+"')";
		int i = 0 ;
		try {
			conn = DBConnector.getConnection();
			ps = conn.prepareStatement(sql);
			i = ps.executeUpdate(); // Row number
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnector.closeStatement(ps);
			DBConnector.closeConnection(conn);
		}
		return i;
	}
	public ArrayList<Product> getProducts() throws SQLException
	{
	String query = "select * from products";
	Connection con = DBConnector.getConnection();
	PreparedStatement statement = con.prepareStatement(query);
	ResultSet rs = statement.executeQuery();
	ArrayList<Product> productList = new ArrayList<Product>();
	while (rs.next())
	{
	Product product = new Product();
	product.setName(rs.getString("Product_name"));
	product.setDescription(rs.getString("Product_description"));
	product.setCost(rs.getDouble("Product_cost"));
	product.setId(rs.getInt("Product_id"));
	productList.add(product);
	}
	statement.close();
	DBConnector.closeConnection(con);
	return productList;
	}
	public Product getProductDetailsById(int productId) throws SQLException {
	    String query = "SELECT * FROM products WHERE Product_id = ?";
	    Connection con = DBConnector.getConnection();
	    PreparedStatement statement = con.prepareStatement(query);
	    statement.setInt(1, productId);
	    ResultSet rs = statement.executeQuery();
	    Product product = null;
	    if (rs.next()) {
	        product = new Product();
	        product.setId(rs.getInt("Product_id"));
	        product.setName(rs.getString("Product_name"));
	        product.setDescription(rs.getString("Product_description"));
	        product.setCost(rs.getDouble("Product_cost"));
	        // Add any additional fields if present in your Product class
	    }
	    statement.close();
	    DBConnector.closeConnection(con);
	    return product;
	}
	 public int deleteProductById(int productId) {
	        try {
	        	Connection conn = DBConnector.getConnection();
	            String query = "DELETE FROM products WHERE Product_id = ?";
	            PreparedStatement ps = conn.prepareStatement(query);
	            ps.setInt(1, productId);
	            return ps.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return 0;
	        }


}
}