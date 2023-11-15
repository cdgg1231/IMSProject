package IMSProject.Database;

import IMSProject.Database.ProductTableModel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ProductTableModel extends AbstractTableModel {

	// private static final long serialVersionUID = -715697162861296537L;
	public final int OBJECT_COL = -1;
	private static final int productID_col = 0;
	private static final int quantity_col = 1;
	private static final int wholesale_col = 2;
	private static final int saleprice_col = 3;
	private static final int supplierID_col = 4;

	private String[] columnNames = { "Product ID", "Quantity", "Wholesale", "Sale's Price", "Supplier ID" };
	private List<ProductInfo> productInfo;

	public ProductTableModel(List<ProductInfo> theProductinfo) {
		productInfo = theProductinfo;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return productInfo.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		ProductInfo tempProductInfo = productInfo.get(row);
		switch (col) {
			case productID_col:
				return tempProductInfo.getProductId();
			case quantity_col:
				return tempProductInfo.getQuantity();
			case wholesale_col:
				return tempProductInfo.getWholesale();
			case saleprice_col:
				return tempProductInfo.getSalePrice();
			default:
				return tempProductInfo.getSupplierId();

		}

	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

}