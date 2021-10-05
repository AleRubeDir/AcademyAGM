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
import javafile.driverDB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alessandro
 */
@WebServlet(name = "loginServlet", urlPatterns = {"/login"})
public class loginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // response.sendRedirect("/progettoAcademy/prodotti");
           String usr = request.getParameter("usr").toString();
           String pwd = request.getParameter("pwd").toString();
                 String query = "SELECT * "
                        + "FROM UTENTE "
                        + "WHERE username = ? AND password = ?";
        try {
            if(auth(usr,pwd)){
                HttpSession  oldsession=request.getSession(false);
                if(oldsession!=null){
                    oldsession.invalidate();
                }
                HttpSession currentSession = request.getSession();
                currentSession.setAttribute("usr", usr);
              response.sendRedirect("/progettoAcademy/prodotti");
            }else{
                response.sendRedirect("/progettoAcademy/jsp/loginForm.jsp");
            }
          } catch (SQLException ex) {
            Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
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

    private Boolean auth(String usr, String pwd) throws SQLException {
          Connection c = driverDB.getConn();
            String query = "SELECT * FROM UTENTE where username = ? AND password = ?";
            PreparedStatement stmt = c.prepareStatement(query);
            stmt.setString(1, usr);
            stmt.setString(2, pwd);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
           
    }

}
