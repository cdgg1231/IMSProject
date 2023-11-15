package IMSProject.GUI;

import java.awt.Color;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import IMSProject.Database.Finance;
import IMSProject.Database.OrderInfo;
import IMSProject.Database.OrderTable;
import IMSProject.Database.OrderTableModel;

import javax.swing.JTextArea;



public class FinancialGUI {

	private JFrame frmClass;
	private Finance finance;
	private JTextField DateTextField;
	private OrderTable orderTable;
	private OrderInfo orderInfo;
	private OrderInfo tempOrderInfo;
	private JTable table;
	private JLabel DateTextFieldLabel;
	
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FinancialGUI window = new FinancialGUI();
					window.frmClass.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FinancialGUI() {
		try {
			orderTable = new OrderTable();
			finance = new Finance();

		}
		catch (Exception exc) {       //to check if the db connection was successful or not
	        System.out.println("Oops, error!");
	      //  JOptionPane.showMessageDialog(this, "Error:" + exc, "Error", JOptionPane.ERROR_MESSAGE);
	     }

	     initialize();
	}
	private void initialize() {
		//JPanel panel = new JPanel();
		frmClass = new JFrame();
		frmClass.setTitle("Finance Report");
		frmClass.getContentPane().setForeground(Color.BLUE);
		frmClass.setBounds(100, 100, 816, 575);
		frmClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmClass.getContentPane().setLayout(null);

		
		
		DateTextField = new JTextField();
		DateTextField.setBounds(206, 74, 123, 22);
		frmClass.getContentPane().add(DateTextField);
		DateTextField.setColumns(10);
///biggerScrollPanel

		DateTextFieldLabel= new JLabel("Date to Search YYYY-MM-DD");
		DateTextFieldLabel.setBounds(206, 10, 180, 100);
		frmClass.getContentPane().add(DateTextFieldLabel);
		table = new JTable();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(258, 107, 510, 410);

		frmClass.getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);
		
		JButton btnDailyOrders = new JButton("Report Daily Orders");
		btnDailyOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//get ProductID from text field
				
				String date = DateTextField.getText();
				
				DateTimeFormatter dateFormatter = 
				        new DateTimeFormatterBuilder()
				            .parseCaseInsensitive()
				            .appendPattern("yyyy-MM-dd")
				            .toFormatter(Locale.ENGLISH);
				
				
				LocalDate date2 = LocalDate.parse(date, dateFormatter);
				
				List<OrderInfo> orderInfo = null;
				try {
					orderInfo = orderTable.getDailyReport(date2);
				
					OrderTableModel model = new OrderTableModel(orderInfo);
					table.setModel(model);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});

		
		
		
		
		btnDailyOrders.setHorizontalAlignment(SwingConstants.LEFT);
		btnDailyOrders.setBounds(33, 156, 148, 23);
		frmClass.getContentPane().add(btnDailyOrders);

		JButton btnMonthlyOrders = new JButton("Report Monthly Orders");
		btnMonthlyOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//get ProductID from text field
				
				String date = DateTextField.getText();
				
				DateTimeFormatter dateFormatter = 
				        new DateTimeFormatterBuilder()
				            .parseCaseInsensitive()
				            .appendPattern("yyyy-MM-dd")
				            .toFormatter(Locale.ENGLISH);
				
				
				LocalDate date2 = LocalDate.parse(date, dateFormatter);
				
				List<OrderInfo> orderInfo = null;
				try {
					orderInfo = orderTable.getMonthyReport(date2);
				
					OrderTableModel model = new OrderTableModel(orderInfo);
					table.setModel(model);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});


		
		
		
		btnMonthlyOrders.setHorizontalAlignment(SwingConstants.LEFT);
		btnMonthlyOrders.setBounds(33, 266, 148, 23);
		frmClass.getContentPane().add(btnMonthlyOrders);

		JButton btnYearlyOrders = new JButton("Report Yearly Orders");
		btnYearlyOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//get ProductID from text field
				
				String date = DateTextField.getText();
				
				DateTimeFormatter dateFormatter = 
				        new DateTimeFormatterBuilder()
				            .parseCaseInsensitive()
				            .appendPattern("yyyy-MM-dd")
				            .toFormatter(Locale.ENGLISH);
				
				
				LocalDate date2 = LocalDate.parse(date, dateFormatter);
				
				List<OrderInfo> orderInfo = null;
				try {
					orderInfo = orderTable.getYearReport(date2);
					
					OrderTableModel model = new OrderTableModel(orderInfo);
					table.setModel(model);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		
		btnYearlyOrders.setHorizontalAlignment(SwingConstants.LEFT);
		btnYearlyOrders.setBounds(33, 361, 148, 23);
		frmClass.getContentPane().add(btnYearlyOrders);
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerOrderGUI back = new CustomerOrderGUI();
				back.NewScreen();
				
			}
		});
		backButton.setBounds(31, 421, 89, 23);
		frmClass.getContentPane().add(backButton);


	}
}



