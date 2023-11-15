package IMSProject;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import IMSProject.Database.OrderTable;
import IMSProject.Database.dbConnection;

class OrderTableDeleteTest {

	PreparedStatement statement = null;
	Connection connection = new dbConnection().getConnection();
	ResultSet rs = null;
	String q;

	@Test
	public void DeleteTest() throws Exception {
		System.out.println("Add test data");
		OrderTable test = new OrderTable();
		String dInStr = "2011-09-01";
		LocalDate d1 = LocalDate.parse(dInStr);
		String email = "adding@gmail.com";
		String productId = "tacos Locos";
		double location = 80039;
		double quantity = 100;

		// Mock order to compare

		try {

			test.addOrder(d1, email, location, productId, quantity);

			// run into our method to enter into db
			System.out.println("Date Order: " + d1.toString() + "\nemail " +
					email.toString() + "\nlocation " + location
					+ "\nProductID " + productId.toString()
					+ "\nQuantity " + quantity);

			double orderId = test.getOrderId();

			test.Delete(orderId);

			q = ("select * from orders WHERE order_id =  ?");

			statement = connection.prepareStatement(q);
			statement.setDouble(1, orderId);
			ResultSet rs = statement.executeQuery(q);
			{

				assertTrue(rs.next());
				assertEquals(rs, null); // if its null it will pass
				assertEquals(rs.getString("cust_email"), null);
				assertEquals(rs.getString("product_id"), null);
				assertEquals(rs.getDouble("cust_location"), null);
				assertEquals(rs.getDouble("product_quantity"), null);
				assertFalse(rs.next());

			}
		} catch (SQLException e) { // to check if the db connection was successful or not
			System.out.println("Oops, error!");
			e.printStackTrace();

		}
	}
}
