/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionformation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author mariem
 */
public class ConnectionService {
    
    public static  Connection openConnection() throws ClassNotFoundException, SQLException{
        Connection c = null;
        Class.forName("com.mysql.jdbc.Driver");  
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestioncf","root","0000");
            c.setAutoCommit(false);
    return c;
    }
    
    public static void closeConnection(Connection c) throws SQLException{
         c.close();
    }
    
}
