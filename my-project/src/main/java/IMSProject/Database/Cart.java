package IMSProject.Database;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class Cart extends JDialog {

	/**
	 * 
	 */
	private JFrame frmClass;

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private OrderTable orderTable;

	private boolean answer;

	/**
	 * Launch the application.
	 */

	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cart window = new Cart();
					window.frmClass.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 * public static void main(String[] args) {
	 * try {
	 * Cart dialog = new Cart();
	 * dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	 * dialog.setVisible(true);
	 * } catch (Exception e) {
	 * e.printStackTrace();
	 * }
	 * }
	 * 
	 * /**
	 * Create the dialog.
	 */
	public Cart() {
		orderTable = new OrderTable();
		initialize();
	}

	public boolean initialize() {

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		// setVisible(true);

		setBounds(50, 50, 80, 80);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblNewLabel = new JLabel("Complete your order?");
			contentPanel.add(lblNewLabel);
		}
		{
			JButton YesBtn = new JButton("Yes");
			YesBtn.addActionListener(new ActionListener() { // connecting method to mysql database with GU to show
															// inventory
				public void actionPerformed(ActionEvent e) {

					try {
						answer = orderTable.Yes();

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			});

			contentPanel.add(YesBtn);
		}
		{
			JButton NoBtn = new JButton("No");

			NoBtn.addActionListener(new ActionListener() { // connecting method to mysql database with GU to show
															// inventory
				public void actionPerformed(ActionEvent e) {

					try {
						answer = orderTable.No();

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			});
			contentPanel.add(NoBtn);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
		// return answer;
		return answer;
	}

}
