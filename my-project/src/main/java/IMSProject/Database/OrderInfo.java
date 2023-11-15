package IMSProject.Database;

import java.time.LocalDate;
import java.util.Date;
//import java.time.LocalDate;

public class OrderInfo {//extends CustomerInfo{
		private String userName;
		private String password;
		private String authorization;
		private Date date;
	    private String cust_email;
	    private double cust_location;
	    private String orderproductId;
	    private double orderquantity;
		private double orderId;
		private String date2;
	    

	    public OrderInfo (Date date, String cust_email, double cust_location, String orderproductId,double orderquantity, double orderId )
	    {	
	    super();
	    this.date = date;
		this.cust_email = cust_email;
		this.cust_location = cust_location;
		this.orderproductId = orderproductId;
		this.orderquantity = orderquantity;
		this.orderId = orderId;
	    }
	    
	

		public OrderInfo(String date2, String cust_email, double cust_location, String orderproductId, double orderquantity, double orderId) {
		    super();
		    this.date2 = date2;
			this.cust_email = cust_email;
			this.cust_location = cust_location;
			this.orderproductId = orderproductId;
			this.orderquantity = orderquantity;
			this.orderId = orderId;
		}



		public String getEmail() {
	        return cust_email;
	    }
	    public void setEmail(String cust_email) {
	        this.cust_email = cust_email;
	    }
	    public double getLocation() {
	        return cust_location;
	    }
	    public void setLocation(double cust_location) {
	        this.cust_location = cust_location;
	    }
	    public String getOrderproductId() {
	        return orderproductId;
	    }
	    public void setorderproductId(String orderproductId) {
	        this.orderproductId = orderproductId;
	   }
	    public double getOrderquantity() {
	        return orderquantity;
	}
	    public void setOrderquantity(double orderquantity) {
	        this.orderquantity = orderquantity;
	    } 

		public Date getDate() {
	        return date;
	    }
	    public void setDate(Date date) {
	        this.date = date;
	    }
	   
	    public String getDate2() {
	        return date2;
	    }
	    public void setDate(String date2) {
	        this.date2 = date2;
	    }
	   
	   
	    public double getOrderId() {
	        return orderId;
	}
	    public void setOrderId(double orderId) {
	        this.orderId = orderId;
	    } 

	    
	  //toString()
	    @Override
	    public String toString() {
	        return "OderInfo [ Date =" + date + ", customer email =" 
	        		+ cust_email + ",customer location ="+ cust_location +" product Id =" + orderproductId +", "
	        		+ "order quantity=" + orderquantity +", order id =" + orderId +"]";
	    } 
	    
	    
}
