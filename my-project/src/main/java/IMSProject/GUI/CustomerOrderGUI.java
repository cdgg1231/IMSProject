package IMSProject.GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import IMSProject.Database.OrderInfo;
import IMSProject.Database.OrderTable;
import IMSProject.Database.OrderTableModel;
import IMSProject.Database.ProductInfo;
import IMSProject.Database.ProductTable;
import IMSProject.Database.ProductTableModel;
import IMSProject.Database.UserLogin;
import IMSProject.Database.dbConnection;
import IMSProject.Database.dbPage;

import javax.swing.JTextArea;

public class CustomerOrderGUI {
	
	
	
	private JFrame frmClass;
	private JTable table;
	private ProductTable productTable;
	private OrderTable orderTable;
	private UserLogin userLogin; 
	/**
	 * Launch the application.
	 */

	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerOrderGUI window = new CustomerOrderGUI();
					window.frmClass.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connect = null;
	private JButton searchBtn;
	private JButton showBtn;
	private JButton addBtn;
	private JLabel prodIdLabel;
	private JTextField ProductIDtextField ;
	private JTextField quantitytextField;
	private JLabel quantitiyLabel;
	private JLabel custEmailLabel;
	private JTextField custEmailtextfield;
	private JLabel custLocationLabel;
	private JTextField 	custLocationtextField;
	private JLabel dateLabel;
	private JTextField datetextField;
	private JButton exitDbBtn;
	String authorization;
	private JButton deleteBtn;
	private JLabel lblNewLabel;
	private JTextField OrderIdtextField;
	private double oriQuantity;
	private final JButton btnBack = new JButton("Back");
	
	/**
	 * Create the application.
	 */
	public CustomerOrderGUI() {
		try {
			productTable = new ProductTable();
			orderTable = new OrderTable();
			userLogin = new UserLogin();
		}
		catch (Exception exc) {       //to check if the db connection was successful or not
	        System.out.println("Oops, error!");
	      //  JOptionPane.showMessageDialog(this, "Error:" + exc, "Error", JOptionPane.ERROR_MESSAGE);
	     }
		
	     initialize();
		
	}


	/**
	 * Initialize the contents of the frame.
	 */
	
	
	
	private void initialize() {
		//JPanel panel = new JPanel();
		frmClass = new JFrame();
		frmClass.setTitle("Customer Order");
		frmClass.setBounds(100, 100, 800, 700);
		frmClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmClass.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(184, 82, 600, 600);
		frmClass.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public  void mouseClicked(MouseEvent arg0) {
				PreparedStatement statement = null; 
				//java.sql.Statement state = null; 
			    ResultSet rs = null;
			    String q;
			    Connection connection = new dbConnection().getConnection();//open connection to mySql database
				
				
			    try {
		    		int row = table.getSelectedRow();
		    		String email = (table.getModel().getValueAt(row, 1)).toString();
		        	q = ("select * from orders where cust_email = '"+email+"' ");
		    		 statement = connection.prepareStatement (q);    //connection to database
		        	rs = statement.executeQuery(q);
		    

		        	while(rs.next()) {//gets data from database
		        		
		        		//double Quantity = Double.valueOf(quantitytextField.getText());				
						//String date = datetextField.getText();
						//String custEmail = custEmailtextfield.getText();
						//double custLocation = Double.valueOf(custLocationtextField.getText());
						//String ProductID = ProductIDtextField.getText();
						
						
		        	String productId = rs.getString("product_id");
		        	double Quantity = rs.getDouble("product_quantity");
		        	String date = rs.getString("DATE");
		        	double custLocation = rs.getDouble("cust_location");
		        	String custEmail = rs.getString("cust_email");
		        	double orderId = rs.getDouble("order_id");
		        	oriQuantity = rs.getDouble("product_quantity");
		        	DateTimeFormatter dateFormatter = 
					        new DateTimeFormatterBuilder()
					            .parseCaseInsensitive()
					            .appendPattern("yyyy-MM-dd")
					            .toFormatter(Locale.ENGLISH);
					LocalDate date2 = LocalDate.parse(date, dateFormatter);
					
					
				quantitytextField.setText(String.valueOf(Quantity));
				datetextField.setText(String.valueOf(date));
				custEmailtextfield.setText(custEmail);
				custLocationtextField.setText((String.valueOf(custLocation)));
				ProductIDtextField.setText(productId);
				OrderIdtextField.setText(String.valueOf(orderId));
		        	}
				statement.close();
	    	}
	    	catch (SQLException e) {       //to check if the db connection was successful or not
		        System.out.println("Oops, error!");
		        e.printStackTrace();
		     }  
				
			}
		});
		
		scrollPane.setViewportView(table);
				
		prodIdLabel = new JLabel("Product ID");
		prodIdLabel.setBounds(6, 12, 95, 16);
		frmClass.getContentPane().add(prodIdLabel);
	
		ProductIDtextField = new JTextField();
		ProductIDtextField.setBounds(6, 40, 130, 26);
		frmClass.getContentPane().add(ProductIDtextField);
		ProductIDtextField.setColumns(10);
		
		quantitytextField = new JTextField();
		quantitytextField.setBounds(148, 40, 95, 26);
		frmClass.getContentPane().add(quantitytextField );
		quantitytextField.setColumns(10);
		
		custEmailtextfield = new JTextField();
		custEmailtextfield.setBounds(255, 40, 95, 26);
		frmClass.getContentPane().add(custEmailtextfield);
		custEmailtextfield.setColumns(10);
		
		quantitiyLabel = new JLabel("Quantity ");
		quantitiyLabel.setBounds(148, 12, 61, 16);
		frmClass.getContentPane().add(quantitiyLabel);
		
		custEmailLabel = new JLabel("Email");
		custEmailLabel.setBounds(255, 12, 84, 16);
		frmClass.getContentPane().add(custEmailLabel);
		
		custLocationtextField = new JTextField();
		custLocationtextField.setBounds(362, 40, 95, 26);
		frmClass.getContentPane().add(custLocationtextField);
		custLocationtextField.setColumns(10);
		
		custLocationLabel = new JLabel("Location");
		custLocationLabel.setBounds(362, 12, 61, 16);
		frmClass.getContentPane().add(custLocationLabel);
		
		datetextField = new JTextField();
		datetextField.setBounds(469, 40, 130, 26);
		frmClass.getContentPane().add(datetextField);
		datetextField.setColumns(10);
		
		dateLabel = new JLabel("Date YYYY-MM-DD");
		dateLabel.setBounds(469, 12, 200, 16);
		frmClass.getContentPane().add(dateLabel);
		
		exitDbBtn = new JButton("Finance Reports");
		exitDbBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//get ProductID from text field
				
				FinancialGUI  nw = new FinancialGUI() ;//calls to GUI for employee
				
				nw.NewScreen();
				
			}
		});
		exitDbBtn.setBounds(6, 283, 134, 29);
		frmClass.getContentPane().add(exitDbBtn);
		
		searchBtn = new JButton("Search Inventory");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//get ProductID from text field
				
				try {
					String userinput = ProductIDtextField.getText();//get ProductID from textfield with userinput
					
					List<ProductInfo> productInfo = null;
					
					if(userinput != null && userinput.trim().length() > 0) {
						productInfo = productTable.searchAllProudctInfo(userinput);//call the productinfo
						
					}
					else {
						productInfo = productTable.getAllProudctInfo();//if ProductID empty, get all productinfo
					}
					//prints the data onto the GUI table
				ProductTableModel model = new ProductTableModel(productInfo);
					table.setModel(model);
				}
				
				catch(Exception exc){
					
				}
			}
		});
		searchBtn.setBounds(6, 78, 134, 29);
		frmClass.getContentPane().add(searchBtn);
	
		showBtn = new JButton("Show Orders");
		showBtn.addActionListener(new ActionListener() { // connecting method to mysql database with GU to show inventory
			public void actionPerformed(ActionEvent e) {
				List<OrderInfo> orderInfo = null;
				try {
					orderInfo = orderTable.getAllOrderInfo();
					OrderTableModel model = new OrderTableModel(orderInfo);
					table.setModel(model);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		showBtn.setBounds(6, 119, 134, 29);
		frmClass.getContentPane().add(showBtn);
		
		addBtn = new JButton("Order Product");
		
		addBtn.addActionListener(new ActionListener() { // connecting method to mysql database with GU to show inventory
			public void actionPerformed(ActionEvent e) {			
			
				double Quantity = Double.valueOf(quantitytextField.getText());				
				String date = datetextField.getText();
				String custEmail = custEmailtextfield.getText();
				double custLocation = Double.valueOf(custLocationtextField.getText());
				String ProductID = ProductIDtextField.getText();

				boolean check = false;
				check = productTable.checkInventory(ProductID, Quantity);
				double InventQuantity = productTable.getQuantity(ProductID);
				
				if (check == false) {
					JOptionPane.showMessageDialog(null, "No enough product in Inventory.Only '"+InventQuantity+"' available" );
				}
				
				else {
				DateTimeFormatter dateFormatter = 
				        new DateTimeFormatterBuilder()
				            .parseCaseInsensitive()
				            .appendPattern("yyyy-MM-dd")
				            .toFormatter(Locale.ENGLISH);
				
				
				LocalDate date2 = LocalDate.parse(date, dateFormatter);
				System.out.println("this is quantity = "+Quantity);
				System.out.println("email="+custEmail);
				System.out.println("location="+custLocation);
				System.out.println("producctid="+ProductID);
				System.out.println("date= "+ date2);
				
				
					
				try {
					
					orderTable.addOrder(date2, custEmail, custLocation, ProductID, Quantity);
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
			}
			}
			});
			addBtn.setBounds(6, 160, 134, 29);
			frmClass.getContentPane().add(addBtn);
			
			JButton updateBtn_1 = new JButton("Edit Order ");
			
			updateBtn_1.addActionListener(new ActionListener() { // connecting method to mysql database with GU to show inventory
				public void actionPerformed(ActionEvent e) {
					
					//get values from textfiles
					double Quantity = Double.valueOf(quantitytextField.getText());				
					String date = datetextField.getText();
					String custEmail = custEmailtextfield.getText();
					double custLocation = Double.valueOf(custLocationtextField.getText());
					String ProductID = ProductIDtextField.getText();
					double orderId = Double.valueOf(OrderIdtextField.getText());
					
					DateTimeFormatter dateFormatter = 
					        new DateTimeFormatterBuilder()
					            .parseCaseInsensitive()
					            .appendPattern("yyyy-MM-dd")
					            .toFormatter(Locale.ENGLISH);
					
					
					LocalDate date2 = LocalDate.parse(date, dateFormatter);
					System.out.println("this is quantity = "+Quantity);
					System.out.println("email="+custEmail);
					System.out.println("location="+custLocation);
					System.out.println("producctid="+ProductID);
					System.out.println("date= "+ date2);	
					
					
					try {
						
						orderTable.editOrder(custEmail, custLocation,  Quantity,orderId);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					double difference = (Math.abs(oriQuantity- Quantity));
					
					if (oriQuantity > Quantity ) {
						//if ordering less add quantity of product back into inventory
						
					}
					else {
						//if ordering more subtract from inventory
						
					}
					
				}
			});
			updateBtn_1.setBounds(6, 200, 134, 29);
			frmClass.getContentPane().add(updateBtn_1);
			
			deleteBtn = new JButton("Delete Order ");
			deleteBtn.addActionListener(new ActionListener() { // connecting method to mysql database with GU to show inventory
				public void actionPerformed(ActionEvent e) {
						
					double orderId = Double.valueOf(OrderIdtextField.getText());// get text from table and use orderId to delete entire order selected 
					
					try {
						
					orderTable.Delete(orderId);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			
			
			
			deleteBtn.setBounds(6, 243, 134, 29);
			frmClass.getContentPane().add(deleteBtn);
			
			JButton orderHx = new JButton("search by email");
			orderHx.addActionListener(new ActionListener() { // connecting method to mysql database with GU to show inventory
				public void actionPerformed(ActionEvent e) {			
					String custEmail = custEmailtextfield.getText();
					List<OrderInfo> orderInfo = null;
					try {
						orderInfo = orderTable.searchAllOrderInfo(custEmail);
						OrderTableModel model = new OrderTableModel(orderInfo);
						table.setModel(model);
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				});
			
			orderHx.setBounds(12, 323, 124, 26);
			frmClass.getContentPane().add(orderHx);
			
			
			JButton btnSearchByOrder = new JButton("search by order Id");
			
			btnSearchByOrder.addActionListener(new ActionListener() { // connecting method to mysql database with GU to show inventory
				public void actionPerformed(ActionEvent e) {			
					
					double orderId = Double.valueOf(OrderIdtextField.getText());// get text from table and use orderId to delete entire order selected 
					List<OrderInfo> orderInfo = null;
					try {
						orderInfo = orderTable.searchAllOrderInfo2(orderId);
						OrderTableModel model = new OrderTableModel(orderInfo);
						table.setModel(model);
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				});
			btnSearchByOrder.setBounds(12, 362, 124, 26);
			frmClass.getContentPane().add(btnSearchByOrder);
			
			
			lblNewLabel = new JLabel("Order Number");
			lblNewLabel.setBounds(637, 13, 78, 14);
			frmClass.getContentPane().add(lblNewLabel);
			
			JTextArea textArea = new JTextArea();
			textArea.setBounds(697, 51, -60, 22);
			frmClass.getContentPane().add(textArea);
			
			OrderIdtextField = new JTextField();
			OrderIdtextField.setBounds(629, 43, 104, 20);
			frmClass.getContentPane().add(OrderIdtextField);
			OrderIdtextField.setColumns(10);
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					dbPage back = new dbPage();
					back.NewScreen();
				}
			});
			btnBack.setBounds(6, 468, 95, 26);
			frmClass.getContentPane().add(btnBack);
			
			
		
		
		
	}
}