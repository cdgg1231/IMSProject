package IMSProject.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import IMSProject.Database.UserInfo;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class CreateAccountGUI extends JFrame {
	
	private UserInfo userInfo;

	Connection connect = null;
	private JFrame frmClass;

	private JPanel contentPane;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtEmail;
	private JTextField txtPassword;
	private JTextField txtAuthorization;
	private JLabel lblUserName;
	private JTextField txtUserName;
	String authorization; 
	

	/**
	 * Launch the application.
	 */
	

	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateAccountGUI window = new CreateAccountGUI();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateAccountGUI frame = new CreateAccountGUI();
					frame.setVisible(true);
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			}
		});
		
		
	}*/

	/**
	 * Create the frame.*/
	 
	
	
	public CreateAccountGUI() {
		setTitle("Create Account");
		
		try {
		userInfo = new UserInfo();
		
		}
		catch (Exception exc) {       //to check if the db connection was successful or not
	        System.out.println("Oops, error!");
	      //  JOptionPane.showMessageDialog(this, "Error:" + exc, "Error", JOptionPane.ERROR_MESSAGE);
	     }
		 initialize();
	}
	
	public void initialize() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel firstNamelbl = new JLabel("First Name");
		firstNamelbl.setBounds(22, 28, 71, 14);
		contentPane.add(firstNamelbl);
		
		JLabel lastNamelbl = new JLabel("Last Name");
		lastNamelbl.setBounds(22, 72, 68, 22);
		contentPane.add(lastNamelbl);
		
		JLabel emailLbl = new JLabel("Email");
		emailLbl.setBounds(22, 123, 47, 14);
		contentPane.add(emailLbl);
		
		JLabel passwordLbl = new JLabel("Password");
		passwordLbl.setBounds(22, 171, 47, 14);
		contentPane.add(passwordLbl);
		
		txtFirstName = new JTextField();
		txtFirstName.setBounds(125, 25, 96, 20);
		contentPane.add(txtFirstName);
		txtFirstName.setColumns(10);
		
		txtLastName = new JTextField();
		txtLastName.setBounds(125, 73, 96, 20);
		contentPane.add(txtLastName);
		txtLastName.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(125, 120, 96, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(125, 168, 96, 20);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
		
		JLabel lblAuthorization = new JLabel("Authorization");
		lblAuthorization.setBounds(22, 280, 68, 14);
		contentPane.add(lblAuthorization);
		
		txtAuthorization = new JTextField();
		txtAuthorization.setBounds(125, 277, 96, 20);
		contentPane.add(txtAuthorization);
		txtAuthorization.setColumns(10);
		
		lblUserName = new JLabel("UserName");
		lblUserName.setBounds(22, 227, 50, 14);
		contentPane.add(lblUserName);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(125, 224, 96, 20);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);
		
		JButton enterBtn = new JButton("Enter");
		enterBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String FirstName = txtFirstName.getText();
				String LastName = txtLastName.getText();
			    String email = txtEmail.getText();
			    String pass = txtPassword.getText();
			    int authoriz = 1;
				String userName = txtUserName.getText();
				
				try {
					
		userInfo.createUser(userName, pass, authoriz, FirstName, LastName, email);
		CustomerGUI nw2 = new CustomerGUI();//calls to GUI for customers
		nw2.NewScreen();
		//frmClass.setVisible(false);
		dispose();    
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		}
			
				
				
				);
		enterBtn.setBounds(287, 206, 89, 23);
		contentPane.add(enterBtn);
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI frm = new LoginGUI();
				frm.main(null);
				
				
			}
		});
		backButton.setBounds(287, 45, 89, 23);
		contentPane.add(backButton);
		
		
	}
}