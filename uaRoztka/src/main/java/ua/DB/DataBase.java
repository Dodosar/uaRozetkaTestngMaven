package ua.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DataBase {
	private static Connection connection = null;	
	private static String username = "root";
	private static String password = "Dima650065";	
	private static String dbUrl = "jdbc:mysql://127.0.0.1:3306/emp";
	private static boolean print = false;	

	
	public static void setPrint(boolean p){print = p;}

	String InsertIntoQueary = "INSERT INTO TopSmartPhones (Smartphone,Price) VALUES ('','');";
	

	public static void InsertIntoValues(){
		
	}
	
	public static Connection getDBConnection(){		
	Connection dbConnection = null;	
	try{// Load mysql jdbc driver
		Class.forName("com.mysql.jdbc.Driver");
	}catch(Exception e){
		System.out.println("FAIL");
	}
	try{
	dbConnection = DriverManager.getConnection(dbUrl, username, password);
		return dbConnection;
	} catch (SQLException e)
		{if(print)e.printStackTrace();
	}	
	return dbConnection;
	}
	
    public static void connect(){
        if(DataBase.connection  == null){
            DataBase.connection = getDBConnection();
        }
    }    	
    
    public static ResultSet select (String query) {
        ResultSet result = null;
        PreparedStatement stmt = null;
        try {
            if(print)System.out.println("started: "+query);
            stmt = connection.prepareStatement(query);
            result = stmt.executeQuery();
    		while (result.next()) {
    			String Id = result.getString(1);
    			String Smartphone = result.getString(2);
    			String Price = result.getString(3);
    			System.out.println(Id + "  " + Smartphone + " " + Price);
    		}		
            if(print)System.out.println("complete: "+query);
        } catch (SQLException e) {
            if(print)e.printStackTrace();
        } catch (Exception e) {
            if(print)e.printStackTrace();
        } finally {
            return result;
        }
    }

	
    public static boolean execute (String query) {
        boolean result = false;
        Statement stmt = null;
        try {
            System.out.println("started: "+query);
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            result = true;
            if(print)System.out.println("complete: "+query);
        } catch (SQLException e) {
            if(print)e.printStackTrace();
        } catch (Exception e) {
            if(print)e.printStackTrace();
        } finally {
            return result;
        }
    }
    


	public static void close() {
		try {
			DataBase.connection.close();
			DataBase.connection = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			if(print)e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
}

