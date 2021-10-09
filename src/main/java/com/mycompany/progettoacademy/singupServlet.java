/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.progettoacademy;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafile.driverDB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.validator.EmailValidator;
/**
 *
 * @author Alessandro
 */
@WebServlet(name = "signupServlet", urlPatterns = {"/signup"})
public class singupServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                RequestDispatcher rd = request.getRequestDispatcher("/jsp/signup.jsp");
                    rd.forward(request, response);
       // processRequest(request, response);
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
      protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // response.sendRedirect("/progettoAcademy/prodotti");
           String usr = request.getParameter("usr").toString();
           if(check(usr)){
               String recovery = request.getParameter("recovery");
               if(recovery==null || recovery == ""){
                   request.setAttribute("err3", "visible");
                    doGet(request,response);
               }
               String pwd = request.getParameter("pwd").toString();
                  String query = "SELECT * "
                         + "FROM UTENTE "
                         + "WHERE username = ? AND password = ?";
         try {
                save(usr,pwd,recovery);
                response.sendRedirect("/progettoAcademy/login");
           } catch (SQLException ex) {
               System.out.println("GEC VALEEEEE : " + ex.getErrorCode());
               if(ex.getErrorCode() == 1062 ){
                    request.setAttribute("err2", "visible");
               }
                doGet(request,response);
             Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
         }     
     }else{
            request.setAttribute("err", "visible");
            doGet(request,response);
           }
    }


    private void save(String usr, String pwd, String recovery) throws SQLException {
          Connection c = driverDB.getConn();
          String update = "INSERT into UTENTE (`username`, `password`, `recovery`) VALUES (?, ?, ?)";
          PreparedStatement stmt = c.prepareStatement(update);
            stmt.setString(1, usr);
            stmt.setString(2, pwd);
            stmt.setString(3, recovery);
            stmt.executeUpdate();
            createCart(usr);
    }
    private void createCart(String usr) throws SQLException {
        Connection c = driverDB.getConn();
        String update = "INSERT into CARRELLO (`username`) VALUES ( ?)";
          PreparedStatement stmt = c.prepareStatement(update);
            stmt.setString(1, usr);
            stmt.executeUpdate();
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

    private Boolean check(String usr) {
       String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";
        Pattern pattern = Pattern.compile(regex);  
        Matcher matcher = pattern.matcher(usr);  
        System.out.println("usr : " + usr + "matcher: " + matcher.matches());
        return matcher.matches();
    }
}
