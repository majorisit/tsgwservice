package com.majoris.checkin.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteJDBCDriverConnection {
     /**
     * Connect to a sample database
     */
    public static void connect() throws Exception {
        Connection conn = null;
        try {
            // db parameters
        	Class.forName("org.sqlite.JDBC");        	
            String url = "jdbc:sqlite:C://sqllite//test.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            Statement stmt  = conn.createStatement();
 //           ResultSet rs    = stmt.executeQuery("select * from test1");
            ResultSet rs    = stmt.executeQuery("select * from MasterMembers");            
           
           // loop through the result set
           while (rs.next()) {
               System.out.println(rs.getInt("id") +  "\t" + 
                                  rs.getString("FirstName"));
           }
            
            System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        connect();
    }
}