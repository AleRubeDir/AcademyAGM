<%-- 
    Document   : logout
    Created on : 29 set 2021, 09:59:08
    Author     : Alessandro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logout :c</title>
    </head>
    <body>
        <body> 
            <% 
                session.removeAttribute("username"); 
                session.removeAttribute("password"); 
                session.invalidate(); 
            %> 
            <h1>Logout effettuato con successo <h1>
        </body>

    </body>
</html>
