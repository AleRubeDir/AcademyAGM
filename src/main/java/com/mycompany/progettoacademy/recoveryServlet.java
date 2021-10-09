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
@WebServlet(name = "recoveryServlet", urlPatterns = {"/recovery"})
public class recoveryServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet recoveryServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet recoveryServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         RequestDispatcher rd = request.getRequestDispatcher("/jsp/recovery.jsp");
                    rd.forward(request, response);
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
        if( request.getParameter("answer") != null
                && 
                request.getParameter("usr")!=null ){
           String answer = request.getParameter("answer").toString();
           String user = request.getParameter("usr").toString();
        try{
            String pwd = recupera(answer,user);
            if(pwd!=""){ 
                request.setAttribute("pwd", pwd);
            }else{
                request.setAttribute("err", "visible");
            }
             doGet(request,response);
        } catch (SQLException ex) {
                 request.setAttribute("err", ex);
                 doGet(request,response);
             Logger.getLogger(recoveryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
    }
    
    private String recupera(String answer, String user) throws SQLException {
          Connection c = driverDB.getConn();
            String query = "SELECT password FROM Utente WHERE username = ? AND recovery = ? ";
            PreparedStatement stmt = c.prepareStatement(query);
            stmt.setString(1, user);
            stmt.setString(2,answer);
            ResultSet rs = stmt.executeQuery();
            String pwd = "";
            while(rs.next())
                pwd= rs.getString("password");
          System.out.println("answer vale " + answer + "user vale " + user + "pwd vale " + pwd + "== " + pwd.equals(""));
            return pwd;
    }


}
