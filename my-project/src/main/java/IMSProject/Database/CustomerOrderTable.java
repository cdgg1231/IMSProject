package IMSProject.Database;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class CustomerOrderTable extends AbstractTableModel {

	private static final long serialVersionUID = -715697162861296537L;
	private static final int productID_col = 0;
	private static final int quantity_col = 1;
	private static final int wholesale_col = 2;
	private static final int saleprice_col = 3;
	private static final int supplierID_col = 4;

	private String[] columnNames = { "Product ID", "Quantity", "Sale's Price", "Supplier ID" };
	private List<CustomerInfo> customerInfo;

	public CustomerOrderTable(List<CustomerInfo> theCustomerInfo) {
		customerInfo = theCustomerInfo;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return customerInfo.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		CustomerInfo tempCustomerInfo = customerInfo.get(row);
		switch (col) {
			case productID_col:
				return tempCustomerInfo.getProductId();
			case quantity_col:
				return tempCustomerInfo.getQuantity();
			case wholesale_col:
				return tempCustomerInfo.getWholesale();
			case saleprice_col:
				return tempCustomerInfo.getSalePrice();
			default:
				return tempCustomerInfo.getSupplierId();
		}
	}

	// Assuming you want to add the setQuantity method here for some reason
	public void setQuantity(double quantity) {
		// Implement the setQuantity method logic if needed
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
