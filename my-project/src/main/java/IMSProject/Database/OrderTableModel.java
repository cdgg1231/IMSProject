package IMSProject.Database;


import java.util.List;


import javax.swing.table.AbstractTableModel;

public class OrderTableModel extends AbstractTableModel  {
	
	
	/**
	 * 
	 */
	public final int OBJECT_COL = -1;
	private static final int date_col = 0;
	private static final int cust_email_col = 1;
	private static final int cust_location_col = 2;
	private static final int productId_col = 3;
	private static final int product_quantity_col = 4;
	private static final int orderId_col =5;
	
	private String[] columnNames = { "Order Date", "Customer Email", "Customer Location", "Product Id", "Product Quantity, Order Number"};
	private List<OrderInfo> orderInfo;
	
	public OrderTableModel(List<OrderInfo> theOrderinfo) {
		orderInfo = theOrderinfo;
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public int getRowCount() {
		return orderInfo.size();
		}
	
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		OrderInfo tempOrderInfo = orderInfo.get(row);
		switch (col)
			{
			case date_col :
				return tempOrderInfo.getDate();
			case cust_email_col :
				return tempOrderInfo.getEmail();
			case cust_location_col :
				return tempOrderInfo.getLocation();
			case productId_col :
				return tempOrderInfo.getOrderproductId();
			case product_quantity_col :
				return tempOrderInfo.getOrderquantity();
			default  :
				return tempOrderInfo.getOrderId();
			
			}
		
	}
	
	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
	
}