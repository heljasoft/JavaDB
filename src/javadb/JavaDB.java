
package javadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JavaDB {

     public static void main(String[] args) {
        
         try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/test_db", "test_user", "123");)
         {
             
             
             //koristenje PreparedStatement
             String statement = "insert into users (username, password) values (?, ?)";
             PreparedStatement preparedStatement = conn.prepareStatement(statement);
             preparedStatement.setString(1, "preparedUserName");
             preparedStatement.setString(2, "preparedPassword");
             preparedStatement.executeUpdate();
             
             //koristenje Statement
             Statement stInsert = conn.createStatement();
             stInsert.execute("insert into users (username, password) values ('myUserName', 'myPassword')");
             
             Statement stFech = conn.createStatement();
             ResultSet rs = stFech.executeQuery("select * from users");
             
             while(rs.next())
             {
                 System.out.println("User: ");
                 System.out.println("Username: "+ rs.getString("username"));
                 System.out.println("Password: "+rs.getString("password"));
                 System.out.println("\n");
             }
         } catch (SQLException e) {
             System.out.println("Error in database connection: \n" + e.getMessage());
         }
         
         
    }
    
}
