/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafile;
import java.sql.*;
import java.util.*;
import javafile.Prodotto;
/**
 *
 * @author Alessandro
 */
public class driverDB {
   public static Connection getConn(){
        try {
            Class.forName("org.mysql.jdbc.Driver");
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
//   public static void main(String[] args) {
//    // carica il driver
//        try {
//            Class.forName("org.mysql.jdbc.Driver");
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                    }
//                    try {
//                    // crea una connessione
//                    Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/prodottiacademy", "root", "admin");
//                    System.out.println("Connessione al db avvenuta con successo");
//                    // ... utilizza la connessione ...
//                    Statement st = c.createStatement();
//                    String query = "SELECT * from prodotto";
//                    ResultSet rs = st.executeQuery(query);
//                    List listaProdotti = new ArrayList<Prodotto>();
//                    while(rs.next()){
//                        Prodotto p = new Prodotto();
//                        p.setCodice(rs.getInt("codice"));
//                        p.setNome(rs.getString("nome"));
//                        p.setPrezzo(rs.getDouble("prezzo"));
//                        p.setImg(rs.getString("img"));
//                        listaProdotti.add(p);
//                    }
//            for (Iterator it = listaProdotti.iterator(); it.hasNext();) {
//                Prodotto x = (Prodotto) it.next();
//                System.out.println(x);
//            }
//                    // chiudi la connessione
//                    c.close();
//                } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    } 
}
