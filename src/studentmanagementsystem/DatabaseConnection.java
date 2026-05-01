/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author nico
 */
public class DatabaseConnection {
     private static final String URL = "jdbc:mysql://localhost:3306/student_management_system";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Your MySQL password
    
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, 
                "Database Connection Failed: " + e.getMessage(),
                "Error", 
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        return conn;
    }
}
