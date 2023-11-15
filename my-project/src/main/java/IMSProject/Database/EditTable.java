package IMSProject.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import java.sql.ResultSet;
import java.sql.DatabaseMetaData;

public class EditTable {
	static Scanner in = new Scanner(System.in);
	PreparedStatement statement = null;
	ResultSet rs = null;
	int num;
	String productID;
	double Quantity;
	double Wholesale;
	double Saleprice;
	String SupplierID;
	String name;
	String q;

	Connection connection = new dbConnection().getConnection();// open connection to mySql database
	String authorization;

	public static void main(String[] args) throws ClassNotFoundException {
		int number;
		EditTable call = new EditTable();

		do {
			do {
				String menu = "\n Welcome to Team 2 Inventory Program!"
						+ "\n1.) Choose 1 to create a username and password."
						+ "\n2.) Choose 2 to check inventory information."
						+ "\n3.) Choose 3 to print out full inventory."
						+ "\n4.) Choose 4 to print out select inventory."
						+ "\n5.) Choose 5 to add to inventory."
						+ "\n6.) Choose 6 to delete product information inventory."
						+ "\n7.) Choose 7 to delete specific data from inventory."
						+ "\n8.) Choose 8 to edit specific data from inventory."
						+ "\n9.) Choose 9 to edit user authoirzation."
						+ "\n9.) Choose 9 to EXIT. ";
				System.out.println(menu);
				number = in.nextInt();

			} while (number < 0 || number > 10);

			switch (number) {
				case 1:// Add user info
					call.UserInfo();

					break;

				case 2:// searh single entry and print
					call.SearchInventory();
					break;

				case 3: // printout entire inventory
					call.PrintInventory();
					break;

				case 4:// print only product id and one columns of inventory table ( ex product ID +
						// Quantity of product)
					call.PrintSelectColumns();

					break;

				case 5: // this add new entry into the inventory
					call.UpdateInventory();
					break;

				case 6: // delete entire row (entry) from inventory database
					call.Delete();

					break;

				case 7: // delete select data from a column of the inventory database(quantity/sale
						// price/whole sale/supplier id)
					call.DeleteSelect(call.NameColumn());
					break;

				case 8:// to edit select data from a column of the inventory database(quantity/sale
						// price/whole sale/supplier id)
					String name = call.NameColumn();

					call.EditColumn(name);
					break;

				case 9:
					call.EditAuthorization();
					System.out.println("testing 9.");
					break;

				default:
					System.out.println("Goodbye.");
					break;
			}

		} while (number != 10);

	}

	public void UserInfo() {// calls to database table to enter data into table for user login and password

		try {
			String sql = "INSERT INTO userInfo (userName, pass) VALUES (?,?)";
			statement = connection.prepareStatement(sql); // reading database?
			String userName; // declaring strings
			String pass;
			System.out.println("Enter your username: ");
			userName = in.next(); // scanner input for username
			System.out.println("Enter your password: ");
			pass = in.next(); // scanner input for pass
			authorization = "1"; // customers will have 1 and employee will have 0, shows the difference between
									// customer and employee logging in

			statement.setString(1, userName); // row position 1 and userName column
			statement.setString(2, pass); // row position 2 and pass column
			statement.setString(3, authorization); // Automatically set account as customer account

			int rows = statement.executeUpdate(); // checking if a row was inserted
			if (rows > 0) {
				System.out.println("A row has been inserted.");
				statement.close();
			}
		}

		catch (SQLException e) { // to check if the db connection was successful or not
			System.out.println("Oops, error!");
			e.printStackTrace();
		}
	}

	public void EditAuthorization() {

		// Authorization newAuth = new Authorization();
		// newAuth.newAuthorization();

	}

	public void SearchInventory() {// function to search and print for single entry in table of productinfo on
									// mysql database
		try {
			String sql2 = "select ProductID, Quantity, Wholesale, Saleprice, SupplierID from productInfo where ProductID = ?";
			statement = connection.prepareStatement(sql2);
			System.out.println("Enter a productId: ");
			String productId = in.next();
			statement.setString(1, productId);
			rs = statement.executeQuery();
			while (rs.next()) {
				String productID = rs.getString("ProductID");
				double Quantity = rs.getDouble("Quantity");
				double Wholesale = rs.getDouble("Wholesale");
				double Saleprice = rs.getDouble("Saleprice");
				String SupplierID = rs.getString("SupplierId");
				System.out.println("ProductID: " + productID + ", Quantity: " + Quantity + ", Wholesale: " + Wholesale
						+ ", Saleprice: " + Saleprice + ", SupplierID: " + SupplierID);

				statement.close();
			}
		}

		catch (SQLException e) { // to check if the db connection was successful or not
			System.out.println("Oops, error!");
			e.printStackTrace();
		}

	}

	public int selectChocie() {

		// Scanner in = new Scanner(System.in);

		String menu = "\n1.) Choose 1 to  add/delete/print  id of product."
				+ "\n2.) Choose 2 to add/delete/print quantity of product."
				+ "\n3.) Choose 3 to  add/delete/print  wholesale of product."
				+ "\n4.) Choose 4 to  add/delete/print  saleprice of product."
				+ "\n5.) Choose 5 to  add/delete/print  supplier ID of product."
				+ "\n6.) Choose 6 to EXIT. ";
		System.out.println(menu);
		int choice = in.nextInt();

		return choice;
	}

	public void UpdateInventory() {

		System.out.println("Enter product ID.");
		productID = in.next();

		System.out.println("Enter product quantity.");
		Quantity = in.nextInt();

		System.out.println("Enter wholesale.");
		Wholesale = in.nextInt();

		System.out.println("Enter sale price ID.");
		Saleprice = in.nextInt();

		System.out.println("Enter supplier ID.");
		SupplierID = in.next();

		try {

			String sql5 = "INSERT INTO productInfo (ProductID, Quantity, Wholesale, Saleprice, SupplierID )" // state to
																												// call
																												// to
																												// table
																												// and
																												// columns
																												// in
																												// mySql
					+ "VALUES ('" + productID + "', '" + Quantity + "',  '" + Wholesale + "', '" + Saleprice + "', '"
					+ SupplierID + "')";// values from data obtain from user input*/

			// String sql5 = ("Update productInfo" + "set productid = ?, quantity = ?,
			// wholesale =?, saleprice = ?, supllier=?" + "where productid=");
			statement = connection.prepareStatement(sql5);
			statement.executeUpdate();// to update information in database with new entry

			statement.close();
		} catch (SQLException e) { // to check if the db connection was successful or not
			System.out.println("Oops, error!");
			e.printStackTrace();
		}
	}

	public void PrintInventory() {
		try {
			String sql3 = "select * from productInfo"; // read the entire table's data on database
			statement = connection.prepareStatement(sql3);
			rs = statement.executeQuery(sql3);

			System.out.println("ProductID\t\tQuantity\tWholesale\tSaleprice\tSupplierId");// add row for name of each
																							// column

			while (rs.next()) {// gets data from database
				productID = rs.getString("ProductID");
				Quantity = rs.getDouble("Quantity");
				Wholesale = rs.getDouble("Wholesale");
				Saleprice = rs.getDouble("Saleprice");
				SupplierID = rs.getString("SupplierId");

				System.out.println(
						productID + "\t\t" + Quantity + "\t\t" + Wholesale + "\t\t" + Saleprice + "\t\t" + SupplierID);// write
																														// one
																														// row
																														// of
																														// data
																														// from
																														// database
			}

			System.out.println("ProductID\t\tQuantity\tWholesale\tSaleprice\tSupplierId");

			statement.close();
		}

		catch (SQLException e) { // to check if the db connection was successful or not
			System.out.println("Oops, error!");
			e.printStackTrace();
		}
	}

	public void PrintSelectColumns() {
		int choice;
		EditTable select = new EditTable();
		String sql4 = "select * from productInfo";
		try {
			statement = connection.prepareStatement(sql4);
			rs = statement.executeQuery();
			choice = select.selectChocie();

			switch (choice) {
				case 1:
					System.out.println("ProductID\t\tQuantity");
					while (rs.next()) {
						String productID = rs.getString("ProductID");
						double Quantity = rs.getDouble("Quantity");
						System.out.println(productID + "\t\t" + Quantity);
						System.out.println("ProductID\t\tQuantity");
					}
					break;

				case 2:
					System.out.println("ProductID\t\tWholesale");
					while (rs.next()) {
						String productID = rs.getString("ProductID");
						double Wholesale = rs.getDouble("Wholesale");
						System.out.println(productID + "\t\t" + Wholesale);
					}
					break;

				case 3:
					System.out.println("ProductID\t\tSaleprice");
					while (rs.next()) {
						String productID = rs.getString("ProductID");
						double Saleprice = rs.getDouble("Saleprice");
						System.out.println(productID + "\t\t" + Saleprice);
					}
					break;

				case 4:
					System.out.println("ProductID\t\tSupplierId");
					while (rs.next()) {
						String productID = rs.getString("ProductID");
						String SupplierID = rs.getString("SupplierId");
						System.out.println(productID + "\t\t" + SupplierID);
					}
					break;
				default:
					break;

			}

			statement.close();
		} catch (SQLException e) { // to check if the db connection was successful or not
			System.out.println("Oops, error!");
			e.printStackTrace();
		}

	}

	public void Delete() {// deletes entire row containing all information on product

		// to delete from table the entire row containing information on product using
		// product ID
		System.out.println("Enter Product ID to remove'\n'");
		productID = in.next();

		String q = "DELETE FROM productInfo WHERE ProductID = '" + productID + "'";
		try {
			statement = connection.prepareStatement(q);
			statement.executeUpdate();

			statement.close();
		}

		catch (SQLException e) { // to check if the db connection was successful or not
			System.out.println("Oops, error!");
			e.printStackTrace();

		}
	}

	public void EditColumn(String name) {// edit spelect data from information on product

		System.out.println("Enter Product ID to edit product information from inventory'\n'");
		productID = in.next();

		System.out.println("Enter new value for " + name + "");
		if (name == "ProductID" || name == "SupplierId")

		{

			String column = in.next();
			q = "UPDATE productInfo SET " + name + " = '" + column + "' WHERE ProductID = '" + productID + "'";
		} else {
			double column2 = in.nextInt();

			q = "UPDATE productInfo SET " + name + " = '" + column2 + "' WHERE ProductID = '" + productID + "'";
		}

		try {
			statement = connection.prepareStatement(q);
			statement.executeUpdate();

			statement.close();
		}

		catch (SQLException e) { // to check if the db connection was successful or not
			System.out.println("Oops, error!");
			e.printStackTrace();
		}
	}

	public void DeleteSelect(String name) {

		System.out.println("Enter Product ID to remove '" + name + "'");

		productID = in.next();

		// q = "UPDATE productInfo SET '"+name+"' = '"+null+"' WHERE ProductID =
		// '"+productID+"'";
		q = "UPDATE productInfo SET " + name + " =  null WHERE ProductID = '" + productID + "'";
		try {
			statement = connection.prepareStatement(q);
			statement.executeUpdate();

			statement.close();
		}

		catch (SQLException e) { // to check if the db connection was successful or not
			System.out.println("Oops, error!");
			e.printStackTrace();
		}

	}

	public String NameColumn() {
		String choose =

				"\n1.) Choose 1 to  add/delete/print  id of product."
						+ "\n2.) Choose 2 to add/delete/print quantity of product."
						+ "\n3.) Choose 3 to  add/delete/print  wholesale of product."
						+ "\n4.) Choose 4 to  add/delete/print  saleprice of product."
						+ "\n5.) Choose 5 to  add/delete/print  supplier ID of product.";
		System.out.println(choose);

		num = in.nextInt();
		switch (num) {
			case 1:
				name = "ProductID";
				break;

			case 2:
				name = "Quantity";

				break;

			case 3:
				name = "Wholesale";
				break;

			case 4:
				name = "Saleprice";
				break;

			case 5:
				name = "SupplierId";
				break;
		}
		return name;
	}

}