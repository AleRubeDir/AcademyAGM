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
                           String user = null;
                           if(request.getSession().getAttribute("usr")== null){
                                response.sendRedirect("/progettoAcademy/login");
                           }else{
                            processRequest(request, response);
                           }
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
                String qta = request.getParameter("qta");
                String elimina = request.getParameter("elimina");
                String qtaElimina = request.getParameter("qtaElimina");
                String user = request.getSession().getAttribute("usr").toString();
                try { 
                ArrayList<Prodotto> carrello = getCart(user);
                    idCarrello = getId(user);
                    if(request.getParameter("button1")!=null){
                        int ris = parseInt(codice);     
                        int risQta = parseInt(qta);
                        add(ris,idCarrello,risQta);
                }
                if(elimina!=null){
                    int ris = parseInt(elimina);
                    int risQta = parseInt(qtaElimina);
                        remove(ris,idCarrello,risQta);
                }
                if(request.getParameter("button2")!=null){
                    compra(idCarrello,carrello);
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
                        p.setQta(rs.getInt("qta"));
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
                        p.setQta(rs.getInt("qta"));
                        listaProdotti.add(p);
                    }
                    c.close();
                    return listaProdotti;
    }
    
    public void add(int idProdotto, int idCarrello, int qta) throws SQLException{
          Connection c = driverDB.getConn();
          String select = "SELECT * from carrprod WHERE idCarrello = ?";
          PreparedStatement ssel = c.prepareStatement(select);
          ssel.setInt(1,idCarrello);
          ResultSet rs = ssel.executeQuery();
          String update = "INSERT into carrprod (`qta`, `idCarrello`, `idProdotto` ) VALUES (?, ?, ?)";
          while(rs.next()){
              if(rs.getInt("idProdotto")==idProdotto){
                  qta = rs.getInt("qta")+qta;
                  update = "UPDATE carrprod SET qta = ? WHERE carrprod.idCarrello = ? AND carrprod.idProdotto = ?";
              }
          }
            PreparedStatement stmt = c.prepareStatement(update);
            stmt = c.prepareStatement(update);
            stmt.setInt(1, qta);
            stmt.setInt(2, idCarrello);
            stmt.setInt(3, idProdotto);
            stmt.executeUpdate(); 
            c.close();
            stmt.close();
    }
        
    public void remove(int idProdotto, int idCarrello, int qta) throws SQLException{
        System.out.println(idProdotto+idCarrello);
          Connection c = driverDB.getConn();
            String query2 = "SELECT qta FROM carrprod WHERE carrprod.idCarrello = ? AND carrprod.idProdotto = ? ";
            PreparedStatement stmt2 = c.prepareStatement(query2);
            stmt2.setInt(1, idCarrello);
            stmt2.setInt(2, idProdotto);
            ResultSet rs = stmt2.executeQuery();
            int qtaCarrello = -1;
                    while(rs.next()) qtaCarrello = rs.getInt("qta");
            String update = "";
            if(qta==qtaCarrello) {
                update = "DELETE FROM carrprod  WHERE carrprod.idCarrello = ? AND carrprod.idProdotto = ? ";
            PreparedStatement stmt = c.prepareStatement(update);
            int newQta = qtaCarrello - qta;
            stmt.setInt(1, idCarrello);
            stmt.setInt(2, idProdotto);
            stmt.executeUpdate(); 
            }
            else {
                update = "UPDATE carrprod SET qta = ? WHERE carrprod.idCarrello = ? AND carrprod.idProdotto = ?";
                   PreparedStatement stmt = c.prepareStatement(update);
            int newQta = qtaCarrello - qta;
            
            stmt.setInt(1, newQta);
            stmt.setInt(2, idCarrello);
            stmt.setInt(3, idProdotto);
            stmt.executeUpdate(); 
            }
     
            c.close();
    }
    
      public void compra(int idCarrello, ArrayList<Prodotto> list) throws SQLException{
          Connection c = driverDB.getConn();
            String query = "DELETE FROM carrprod WHERE idCarrello = ?";
            PreparedStatement stmt = c.prepareStatement(query);
            stmt.setInt(1, idCarrello);
            stmt.executeUpdate(); 
            for(Prodotto p : list){
                int oldQta = -1;
                String query2 = "SELECT * FROM prodotto WHERE Codice = ?";
                PreparedStatement stmt2 = c.prepareStatement(query2);
                stmt2.setInt(1, p.getCodice());
                ResultSet rs = stmt2.executeQuery(); 
                int id = -1;
                while(rs.next()) {
                    oldQta=rs.getInt("qta");
                    id = rs.getInt("Codice");
                }
                if(oldQta!=-1 && oldQta>=p.getQta()){
                    String query3 = "UPDATE prodotto SET qta = ? WHERE Codice = ?";
                    PreparedStatement stmt3 = c.prepareStatement(query3);
                    int newQta = oldQta-p.getQta();
                    stmt3.setInt(1,newQta);
                    stmt3.setInt(2, id);
                    stmt3.executeUpdate(); 
                    stmt3.close();
                }
                  stmt2.close();
            }
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
