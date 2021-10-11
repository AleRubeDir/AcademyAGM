/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafile;
import java.sql.*;
/**
 *
 * @author Alessandro
 */
public class driverDB {
   public static Connection getConn(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    }
                    try {
                    // crea una connessione
                    Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/prodottiacademy", "root", "admin");
                    System.out.println("Connessione al db avvenuta con successo");
                    return c;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return null;
   }
} 
