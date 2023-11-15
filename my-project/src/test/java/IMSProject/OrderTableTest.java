package IMSProject;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import IMSProject.Database.OrderTable;
import IMSProject.Database.dbConnection;

class OrderTableTest {
	PreparedStatement statement = null;
	Connection connection = new dbConnection().getConnection();
	ResultSet rs = null;
	String q;

	@Test
	public void testAddOrder() throws Exception {
		System.out.println("ADD data to test Add Method");
		OrderTable test = new OrderTable();
		String dInStr = "2011-09-01";
		LocalDate d1 = LocalDate.parse(dInStr);
		String email = "adding@gmail.com";
		String productId = "tacos Locos";
		double location = 80039;
		double quantity = 100;

		// Mock order to compare

		test.addOrder(d1, email, location, productId, quantity);
		// run into our method to enter into db
		System.out.println("Date Order: " + d1.toString() + "\nemail " +
				email.toString() + "\nlocation " + location
				+ "\nProductID " + productId.toString()
				+ "\nQuantity " + quantity);

		double orderId = test.getOrderId();

		// collects order id generated on new order

		try {

			q = ("select * from orders WHERE order_id =  ?");

			statement = connection.prepareStatement(q);
			statement.setDouble(1, orderId);
			ResultSet rs = statement.executeQuery(q);
			{
				assertTrue(rs.next());
				// productId = rs.getString("product_id");
				assertEquals(d1, rs.getDate("date"));
				assertEquals(email, rs.getString("cust_email"));
				assertEquals(productId, rs.getString("product_id"));
				assertEquals(location, rs.getDouble("cust_location"));
				assertEquals(quantity, rs.getDouble("product_quantity"));
				assertFalse(rs.next());

				statement.close();
			}
		} catch (SQLException e) { // to check if the db connection was successful or not
			System.out.println("Oops, error!");
			e.printStackTrace();
		}

		System.out.println("Compared test data with data added using add method");
		System.out.println("Date Order: " + d1.toString() + "\nemail " +
				email.toString() + "\nlocation " + location
				+ "\nProductID " + productId.toString()
				+ "\nQuantity " + quantity);

	}

	@Test
	public void testUpdateOrder() throws Exception {
		System.out.println("ADD data to test Update Method");
		OrderTable test = new OrderTable();
		String dInStr = "2011-09-01";
		LocalDate d1 = LocalDate.parse(dInStr);
		String email = "hola@gmail.com";
		String productId = "tacos";
		double location = 80039;
		double quantity = 10;

		// Mock order to compare

		test.addOrder(d1, email, location, productId, quantity);
		System.out.println("Date Order: " + d1.toString() + "\nemail " +
				email.toString() + "\nlocation " + location + "\nProductID " + productId.toString()
				+ "\nQuantity " + quantity);

		// run into our method to enter into db
		double orderId = test.getOrderId();

		// collects order id generated on new order

		String Email = "changed@gmail";
		double custLocation = 90493;
		double Quantity = 3;

		try {

			q = ("UPDATE orders SET cust_email=?, cust_location=?, product_quantity =  ? WHERE order_id =  ?");
			statement = connection.prepareStatement(q);
			statement.setString(1, Email);
			statement.setDouble(2, custLocation);
			statement.setDouble(3, Quantity);
			statement.setDouble(4, orderId);
			ResultSet rs = statement.executeQuery();
			{
				assertTrue(rs.next());
				// productId = rs.getString("product_id");

				assertEquals(d1, rs.getDate("date"));
				assertEquals(Email, rs.getString("cust_email"));
				assertEquals(custLocation, rs.getDouble("cust_location"));
				assertEquals(Quantity, rs.getDouble("product_quantity"));
				assertFalse(rs.next());
			}
		} catch (SQLException e) { // to check if the db connection was successful or not
			System.out.println("Oops, error!");
			e.printStackTrace();
		}

		System.out.println("Tested Data with its modifications");
		System.out.println("Date Order: " + d1.toString() + "\nemail " +
				Email.toString() + "\nlocation " + custLocation + "\nProductID " +
				productId.toString() +
				"\nQuantity " + Quantity);

	}

}
