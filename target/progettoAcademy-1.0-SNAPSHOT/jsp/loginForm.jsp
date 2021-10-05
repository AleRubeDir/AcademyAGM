<%-- 
    Document   : loginForm
    Created on : 29 set 2021, 09:55:57
    Author     : Alessandro
--%>

<%@page import="javafile.driverDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html> 
    <head> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <title>Login :)</title>
    </head> 
    <body> 
        <h1>Login</h1> 
    <center> 
        <h2>Inserisci i tuoi dati</h2> 
        <form action="<%= request.getContextPath()%>/login" method="post"> 
            <br/>Username:<input type="text" name="usr"> 
            <br/>Password:<input type="password" name="pwd"> 
            <br/>
            <input type="submit" value="Accedi"> 
        </form> 
    </center> 
</body> 
</html>

