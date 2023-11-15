package IMSProject.Database;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditInventory extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField productIDTextField;
    private JTextField QuantityTextField;
    private JTextField WholesaleTextField;
    private JTextField SalepriceTextField;
    private JTextField SupplierIdTextField;

    private dbPage employee;
    private ProductTable productTable;

    private ProductInfo previousProductInfo = null;
    private boolean updateProductTable = false;

    public EditInventory(boolean theUpdateMode, ProductInfo thepreviousProductInfo) {
        try {
            employee = new dbPage();
            productTable = new ProductTable();
            previousProductInfo = thepreviousProductInfo;

            if (updateProductTable) {
                setTitle("Update Product");
                populateGUI(previousProductInfo);
            }
        } catch (Exception exc) {
            System.out.println("Oops, error!");
        }
    }

    private void populateGUI(ProductInfo theProduct) {
        QuantityTextField.setText(String.valueOf((theProduct.getQuantity())));
        WholesaleTextField.setText(String.valueOf((theProduct.getWholesale())));
        SalepriceTextField.setText(String.valueOf((theProduct.getSalePrice())));
        SupplierIdTextField.setText(theProduct.getSupplierId());
        productIDTextField.setText(theProduct.getProductId());
    }

    public EditInventory() {
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel lblNewLabel = new JLabel("Product Id");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        contentPanel.add(lblNewLabel, gbc);

        productIDTextField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(productIDTextField, gbc);
        productIDTextField.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Quantity");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        contentPanel.add(lblNewLabel_1, gbc);

        QuantityTextField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(QuantityTextField, gbc);
        QuantityTextField.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("Wholesale");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        contentPanel.add(lblNewLabel_2, gbc);

        WholesaleTextField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(WholesaleTextField, gbc);
        WholesaleTextField.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("Saleprice");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        contentPanel.add(lblNewLabel_3, gbc);

        SalepriceTextField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(SalepriceTextField, gbc);
        SalepriceTextField.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("Supplier Id");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        contentPanel.add(lblNewLabel_4, gbc);

        SupplierIdTextField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(SupplierIdTextField, gbc);
        SupplierIdTextField.setColumns(10);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = new JButton("Save");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveProduct();
            }
        });
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });
        buttonPane.add(cancelButton);
    }

    private void saveProduct() {
        double quantity = Double.valueOf(QuantityTextField.getText());
        double wholesale = Double.valueOf(WholesaleTextField.getText());
        double salePrce = Double.valueOf(SalepriceTextField.getText());
        String supplierID = SupplierIdTextField.getText();
        String productID = productIDTextField.getText();

        try {
            productTable.addProduct(productID, quantity, wholesale, salePrce, supplierID);

            setVisible(false);
            dispose();

         //   employee.refreshEmployeeWindow();

            JOptionPane.showMessageDialog(null, "Product added to inventory successfully.");
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null, "Error when adding product to inventory.");
            e1.printStackTrace();
        }
    }
}
