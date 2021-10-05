/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.progettoacademy;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javafile.driverDB;
import javafile.Prodotto;
import javax.servlet.RequestDispatcher;
/**
 *
 * @author Alessandro
 */
@WebServlet(name = "prodottiServlet", urlPatterns = {"/prodotti"})
public class prodottiServlet extends HttpServlet {
int idCarrello=-1;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
           try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            ArrayList listaProdotti =  getList();
            
            String user = request.getSession().getAttribute("usr").toString();
            ArrayList carrello = getCart(user);
                    request.setAttribute("list", (ArrayList<Prodotto>)listaProdotti);
                    request.setAttribute("carrello", (ArrayList<Prodotto>)carrello);
                    RequestDispatcher rd = request.getRequestDispatcher("/jsp/home.jsp");
                    rd.forward(request, response);
           }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {       
                    try {
                        processRequest(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(prodottiServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            //   }
              
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                String codice = request.getParameter("codice");
                String elimina = request.getParameter("elimina");
                String user = request.getSession().getAttribute("usr").toString();
                try { 
                    idCarrello = getId(user);
                if(codice!=null && codice!="-1"){
                    int ris = parseInt(codice);     
                      
                    add(ris,idCarrello);
                }
                if(elimina!=null){
                    int ris = parseInt(elimina);
                        remove(ris,idCarrello);
                }
                if(request.getParameter("button2")!=null){
                    removeAll();
                }
                } catch (SQLException ex) {
                        Logger.getLogger(prodottiServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                finally{
                    response.sendRedirect("/progettoAcademy/prodotti");
                }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private ArrayList getList() throws SQLException {
          Connection c = driverDB.getConn();
            Statement st = c.createStatement();
            String query = "SELECT * from prodotto";
                 
                    ResultSet rs = st.executeQuery(query);
                    ArrayList listaProdotti = new ArrayList<Prodotto>();
                    while(rs.next()){
                        Prodotto p = new Prodotto();
                        p.setCodice(rs.getInt("Codice"));
                        p.setNome(rs.getString("Nome"));
                        p.setPrezzo(rs.getDouble("Prezzo"));
                        p.setImg(rs.getString("Img"));
                        listaProdotti.add(p);
                    }
                    c.close();
                    return listaProdotti;
    }
     private ArrayList getCart(String user) throws SQLException {
            Connection c = driverDB.getConn();
            String query = "SELECT * from carrprod, carrello, prodotto, utente WHERE utente.username = ? AND utente.username = carrello.username AND carrello.id=carrprod.idCarrello AND carrprod.idProdotto = prodotto.codice";
            PreparedStatement st = c.prepareStatement(query);
            st.setString(1,user);
                    ResultSet rs = st.executeQuery();
                    ArrayList listaProdotti = new ArrayList<Prodotto>();
                    while(rs.next()){
                        Prodotto p = new Prodotto();
                        p.setCodice(rs.getInt("Codice"));
                        p.setNome(rs.getString("Nome"));
                        p.setPrezzo(rs.getDouble("Prezzo"));
                        p.setImg(rs.getString("Img"));
                        listaProdotti.add(p);
                    }
                    c.close();
                    return listaProdotti;
    }
    
    public void add(int idProdotto, int idCarrello) throws SQLException{
        System.out.println(idProdotto+idCarrello);
          Connection c = driverDB.getConn();
            String query = "INSERT into carrprod (`idCarrello`, `idProdotto`) VALUES (?, ?)";
            PreparedStatement stmt = c.prepareStatement(query);
            stmt.setInt(1, idCarrello);
            stmt.setInt(2, idProdotto);
            stmt.executeUpdate(); 
            c.close();
            stmt.close();
    }
        
    public void remove(int idProdotto, int idCarrello) throws SQLException{
        System.out.println(idProdotto+idCarrello);
          Connection c = driverDB.getConn();
            String query = "DELETE FROM carrprod  WHERE carrprod.idCarrello = ? AND carrprod.idProdotto = ? LIMIT 1 ";
            PreparedStatement stmt = c.prepareStatement(query);
            stmt.setInt(1, idCarrello);
            stmt.setInt(2, idProdotto);
            stmt.executeUpdate(); 
            c.close();
            stmt.close();
    }
    
      public void removeAll() throws SQLException{
          Connection c = driverDB.getConn();
            String query = "DELETE FROM carrprod";
            PreparedStatement stmt = c.prepareStatement(query);
            stmt.executeUpdate(); 
            c.close();
            stmt.close();
    }

    private int getId(String user) throws SQLException {
         Connection c = driverDB.getConn();
            String query = "SELECT id FROM carrello WHERE username=? ";
            PreparedStatement stmt = c.prepareStatement(query);
            stmt.setString(1, user);
            ResultSet rs = stmt.executeQuery();
            int ris = -1;
            while(rs.next()){
                ris = rs.getInt("id");
                String val = Integer.toString(rs.getInt("id"));
            Logger.getLogger(prodottiServlet.class.getName()).log(Level.SEVERE, val , "CIAONEEEEEEEEE");
            }
            return ris;
    }
}
