package IMSProject.Database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import com.mysql.cj.xdevapi.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UserInfo {
	PreparedStatement statement = null; 
	//java.sql.Statement state = null; 
    ResultSet rs = null;
    String q;
    Connection connection = new dbConnection().getConnection();//open connection to mySql database
    
    

    public void createUser( String userName, String password, int authorization , String FirstName , String LastName,
    		String email) {
    	PreparedStatement statement = null; 
    
    	try {

    		 String q = "INSERT INTO userInfo (userName, pass, authorization, FirstName, LastName, email )" //state to call to table and columns in mySql
    				+ "VALUES ('"+ userName + "',  '"+ password +"', '"+authorization+"',  '"+ FirstName+"', '"+ LastName+"', '"+ email+"')";//values from data obtain from user input
    		 	statement= connection.prepareStatement (q);
   		 
    		
    		 	statement.executeUpdate(q);//to update information in database with new entry
   		 
   		 
    		 	statement.close();
    	}
    	catch (SQLException e) {       //to check if the db connection was successful or not
	        System.out.println("Oops, error!");
	        e.printStackTrace();
	     }
    }
    
    
}