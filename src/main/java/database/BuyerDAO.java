package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Buyer;

public class BuyerDAO {

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	public int registerBuyer(Buyer buyer) 
	{
		String sql = "insert into buyers(NAME,USERNAME,MAIL,MOBILE,PASSWORD,ADDRESS) values ('"+buyer.getName()+"','"+buyer.getUsername()+"','"+buyer.getMail()+"','"+buyer.getMobile()+"','"+buyer.getPassword()+"','"+buyer.getAddress()+"')";
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
	public Buyer verifyBuyer(String username,String pwd) throws SQLException
	{
	String sql = "select * from buyers where USERNAME='"+username+"' and PASSWORD='"+pwd+"'";
	Buyer buyer = null;
	try {
	conn = DBConnector.getConnection();
	ps = conn.prepareStatement(sql);
	rs = ps.executeQuery();
	if(rs.next()) {
	buyer = new Buyer();
	buyer.setId(rs.getInt("ID"));
	buyer.setName(rs.getString("NAME"));
	buyer.setMail(rs.getString("MAIL"));
	buyer.setMobile(rs.getString("MOBILE"));
	buyer.setAddress(rs.getString("ADDRESS"));
	buyer.setUsername(username);
	buyer.setPassword(pwd);
	
	}
	}catch(SQLException e) {
	e.printStackTrace();
	}finally {
	try {
	rs.close();
	} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	DBConnector.closeStatement(ps);
	DBConnector.closeConnection(conn);
	}
	return buyer;
	}
	public ArrayList<Buyer> getBuyers() throws SQLException
	{
	String query = "select * from buyers";
	Connection con = DBConnector.getConnection();
	PreparedStatement statement = con.prepareStatement(query);
	ResultSet rs = statement.executeQuery();
	ArrayList<Buyer> buyerList = new ArrayList<Buyer>();
	while (rs.next())
	{
	Buyer buyer = new Buyer();
	buyer.setName(rs.getString("name"));
	buyer.setMail(rs.getString("mail"));
	buyer.setMobile(rs.getString("mobile"));
	buyer.setAddress(rs.getString("address"));
	buyer.setId(rs.getInt("id"));
	buyerList.add(buyer);
	}
	statement.close();
	DBConnector.closeConnection(con);
	return buyerList;
	}

}
