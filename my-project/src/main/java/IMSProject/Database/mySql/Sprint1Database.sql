CREATE database Sprint1;
use sprint1;
CREATE table productInfo(ProductID CHAR(12),
						 Quantity double,
                         Wholesale double,
                         Saleprice double,
                         SupplierID CHAR(8));
select * FROM productInfo;

use Sprint1;
CREATE table userInfo(
		      userName CHAR(18),
		      pass CHAR(18),
                      authorization int (2),
		      FirstName Char(25),
                      LastName CHAR(25),
		      email CHAR(25));
select * FROM userInfo;

use Sprint1;
CREATE TABLE orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    Date DATE,
    cust_email CHAR(25),
    cust_location DOUBLE,
    product_id CHAR(12),
    product_quantity DOUBLE,
    orderproductid CHAR(12),
    orderquantity DOUBLE,
    orderproduct_id CHAR(12)):
			

select * FROM orders;
