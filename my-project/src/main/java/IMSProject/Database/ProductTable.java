package IMSProject.Database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import com.mysql.cj.xdevapi.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProductTable {
	PreparedStatement statement = null;
	// java.sql.Statement state = null;
	ResultSet rs = null;
	String q;
	Connection connection = new dbConnection().getConnection();// open connection to mySql database

	public List<ProductInfo> getAllProudctInfo() throws Exception {
		List<ProductInfo> list = new ArrayList<>();

		PreparedStatement statement = null;
		ResultSet rs = null;

		try {
			q = ("select * from productInfo");
			statement = connection.prepareStatement(q); // connection to database
			rs = statement.executeQuery(q);

			while (rs.next()) {// gets data from database
				ProductInfo tempProduct = convertRowToProduct(rs);

				list.add(tempProduct);

			}

			statement.close();
		} catch (SQLException e) { // to check if the db connection was successful or not
			System.out.println("Oops, error!");
			e.printStackTrace();
		}
		return list;

	}

	// method to search for product in inventory database using product id
	public List<ProductInfo> searchAllProudctInfo(String productID) throws Exception {
		List<ProductInfo> list = new ArrayList<>();

		try {
			productID += "%";
			q = ("select * from productInfo where productID like ?");

			statement = connection.prepareStatement(q);
			statement.setString(1, productID);// calling to first column to search
			rs = statement.executeQuery();

			while (rs.next()) {// gets data from database
				ProductInfo tempProduct = convertRowToProduct(rs);
				list.add(tempProduct);

			}

			statement.close();
			rs.close();

		} catch (SQLException e) { // to check if the db connection was successful or not
			System.out.println("Oops, error!");
			e.printStackTrace();
		}
		return list;
	}

	// method to add new entry for new product into inventory database

	public void addProduct(String productID, double Quantity, double Wholesale,
			double Saleprice, String SupplierID) {
		PreparedStatement statement = null;

		try {
			String q = "INSERT INTO productInfo (ProductID, Quantity, Wholesale, Saleprice, SupplierID )" // state to
																											// call to
																											// table and
																											// columns
																											// in mySql
					+ "VALUES ('" + productID + "', '" + Quantity + "',  '" + Wholesale + "', '" + Saleprice + "', '"
					+ SupplierID + "')";// values from data obtain from user input

			statement = connection.prepareStatement(q);

			statement.executeUpdate(q);// to update information in database with new entry

			statement.close();
		} catch (SQLException e) { // to check if the db connection was successful or not
			System.out.println("Oops, error!");
			e.printStackTrace();
		}
	}

	// to delete from table the entire row containing information on product using
	// product ID
	public void Delete(String input) {// deletes entire row containing all information on product

		String q = "DELETE FROM productInfo WHERE ProductID = '" + input + "'";
		try {
			statement = connection.prepareStatement(q);
			statement.executeUpdate(q);

			statement.close();
		}

		catch (SQLException e) { // to check if the db connection was successful or not
			System.out.println("Oops, error!");
			e.printStackTrace();

		}
	}

	public List<ProductInfo> getFromMysql() {
		List<ProductInfo> list = new ArrayList<>();
		try {
			q = ("select * from productInfo");
			statement = connection.prepareStatement(q); // connection to database
			rs = statement.executeQuery(q);

			while (rs.next()) {// gets data from database

				ProductInfo tempProduct = convertRowToProduct(rs);
				list.add(tempProduct);

			}

			statement.close();
		} catch (SQLException e) { // to check if the db connection was successful or not
			System.out.println("Oops, error!");
			e.printStackTrace();
		}

		return list;

	}

	public void EditInventory(String productID, double Quantity, double Wholesale,
			double Saleprice, String SupplierID) {
		PreparedStatement statement = null;

		try {
			String q = "UPDATE productInfo SET  Quantity = ?,Wholesale =?, Saleprice = ? WHERE ProductID=  ?";
			statement = connection.prepareStatement(q);
			statement.setDouble(1, Quantity);
			statement.setDouble(2, Wholesale);
			statement.setDouble(3, Saleprice);
			statement.setString(4, productID);

			statement.executeUpdate();// to update information in database with new entry

			statement.close();
		} catch (SQLException e) { // to check if the db connection was successful or not
			System.out.println("Oops, error!");
			e.printStackTrace();
		}
	}

	public boolean checkInventory(String productID, double Quantity) {
		boolean check = false;
		try {
			productID += "%";
			q = ("select * from productInfo where productID like ?");

			statement = connection.prepareStatement(q);
			statement.setString(1, productID);// calling to first column to search
			rs = statement.executeQuery();

			rs.next(); // gets data from database
			double quantity2 = rs.getDouble("quantity");

			statement.close();
			rs.close();

			if (Quantity <= quantity2) {
				check = true;
			}

		} catch (SQLException e) { // to check if the db connection was successful or not
			System.out.println("Oops, error!");
			e.printStackTrace();
		}

		System.out.println("inventory quantity before order = " + check);
		return check;

	}

	public double getQuantity(String productID) {
		double quantity2 = 0;
		q = ("select quantity from productInfo where productID like ?");
		try {
			statement = connection.prepareStatement(q);
			statement.setString(1, productID);// calling to first column to search
			rs = statement.executeQuery();

			rs.next(); // gets data from database
			quantity2 = rs.getDouble("quantity");

			statement.close();
			rs.close();
		}

		catch (SQLException e) { // to check if the db connection was successful or not
			System.out.println("Oops, error!");
			e.printStackTrace();
		}

		System.out.println("inventory quantity before order = " + quantity2);
		return quantity2;

	}

	private ProductInfo convertRowToProduct(ResultSet rs) throws SQLException {
		String productId = rs.getString("ProductID");
		double quantity = rs.getDouble("Quantity");
		double wholesale = rs.getDouble("Wholesale");
		double salePrice = rs.getDouble("Saleprice");
		String supplierId = rs.getString("SupplierID");

		// Assuming 'cust_email' is an email address
		String email = ""; // Replace this with the correct column name from your orders table
		// Assuming 'cust_location' is a double
		double location = 0.0; // Replace this with the correct column name from your orders table

		ProductInfo tempProduct = new ProductInfo(productId, email, salePrice, wholesale, supplierId, location);

		return tempProduct;
	}

	public static void main(String[] args) throws Exception {
		ProductTable check = new ProductTable();
		System.out.println(check.searchAllProudctInfo("test"));
		System.out.println(check.getAllProudctInfo());

	}
}
