package IMSProject.Database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerTable {

	ResultSet rs = null;
	String q;
	Connection connection = new dbConnection().getConnection(); // open connection to mySql database

	public List<ProductInfo> getAllOrders() throws Exception {
		List<ProductInfo> list = new ArrayList<>();
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {
			q = ("select * from orders");
			statement = connection.prepareStatement(q);
			rs = statement.executeQuery(q);

			while (rs.next()) {
				ProductInfo tempProduct = convertRowToProduct(rs);
				list.add(tempProduct);
			}

			statement.close();
		} catch (SQLException e) {
			System.out.println("Oops, error!");
			e.printStackTrace();
		}
		return list;
	}

	private ProductInfo convertRowToProduct(ResultSet rs) throws SQLException {
		// String userName = rs.getString("userName");
		String email = rs.getString("cust_email");
		String supplierId = rs.getString("supplier_Id");
		double location = rs.getDouble("location");
		double salePrice = rs.getDouble("salePrice");
		double wholesale = rs.getDouble("wholesale");
		String productId = rs.getString("product_id");
		String productQuantity = rs.getString("product_quantity");

		// Assuming location should be assigned to wholesale, and productQuantity to
		// quantity
		ProductInfo tempProduct = new ProductInfo(productId, email, salePrice, wholesale, supplierId, location);

		return tempProduct;
	};
}