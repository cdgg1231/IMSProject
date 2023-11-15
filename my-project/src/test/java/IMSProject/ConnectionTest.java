package IMSProject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.ResultSet;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import IMSProject.Database.OrderInfo;
import IMSProject.Database.OrderTable;
import IMSProject.Database.UserLogin;
import IMSProject.Database.dbConnection;

class ConnectionTest {

	@DisplayName("JDBC Successful")
	@Test
	public void testOpenConnection() throws Exception {

		System.out.println("open Connection");
		dbConnection test = new dbConnection();
		test.getConnection();
		assertEquals(test != null, true); // The result will equal not null then junit test pass
	}

	@DisplayName("Authorization type")
	@Test
	public void testAuthorization() throws Exception {

		System.out.println("User authorization pass");

		UserLogin levelAccess = new UserLogin();
		// assertEquals(0, levelAccess.searcAuthroization(name)); // The result will
		// equal not null then junit test pass

		assertEquals(1, levelAccess.searchAuthorization("tram"));
		assertEquals(0, levelAccess.searchAuthorization("tony"));
		assertEquals(1, levelAccess.searchAuthorization("collin"));
		assertEquals(0, levelAccess.searchAuthorization("david"));

	}

	@Test
	public void testOrderTable() throws Exception {

		System.out.println("OrderTable");
		String orderTable = "Order table orders\n "
				+ "DATE DATE NOT NULL,\n"
				+ "CUSTEMAIL TEXT NOT NULL,\n"
				+ "CUSTLOCATION DOUBLE NOT NULL,\n"
				+ "PRODUCTID TEXT NOT NULL,\n"
				+ "PRODUCTQUANTI DOUBLE NOT NULL,\n"
				+ "):";
		try {

			OrderTable rs = new OrderTable();
			rs.statement.executeQuery(orderTable);
			List<OrderInfo> rsmd = rs.getAllOrderInfo();

			OrderInfo columnName1 = rsmd.get(1);
			OrderInfo columnName2 = rsmd.get(2);
			OrderInfo columnName3 = rsmd.get(3);
			OrderInfo columnName4 = rsmd.get(4);
			OrderInfo columnName5 = rsmd.get(5);

			assertEquals("DATE", columnName1);
			assertEquals("CUSTEMAIL", columnName2);
			assertEquals("CUTLOCATION", columnName3);
			assertEquals("PRODUCTID", columnName4);
			assertEquals("PRODUCTQUANTI", columnName5);

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

}
//
// @Test
//
// public void testInsertOrderTable() throws Exception {
//
// System.out.println("InsertOrderInfo");
// String insertOrderData = "INSERT INTO orders\n"
// + "(DATE, cust_email, cust_location, product_id, product_quantity)\n";
// try {
// OrderTable rs = new OrderTable();
// String cust_email = rs.getString(cust_email);
//
// }
// }
// }
