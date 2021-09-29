<%-- 
    Document   : home
    Created on : 29 set 2021, 09:53:25
    Author     : Alessandro
--%>

<%@page import="java.util.List"%>
<%@page import="javafile.Prodotto"%>
<%@page import="javafile.driverDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shoppy AGM </title>
    </head>
    <body>
        <h1>Benvenuti nel mio shop</h1>
        <!-- 
        <% 
       //   String username=request.getParameter("username"); 
       //   String password=request.getParameter("password"); 
          
       //     if(!(username.equals("admin") && password.equals("admin"))){ 
       //         response.sendRedirect("ErrorLogin.jsp");
       //     } 
       //     session.setAttribute("username",username); 
        %>
        -->
        <div id="prodotti">
            <% List<Prodotto> list = (List) request.getAttribute("list");%>
            <% for(Prodotto p : list){ %>
            <div class="card" style="width: 18rem;">
                <img src="${p.img}" class="card-img-top">
                <div class="card-body">
                  <h5 class="card-title">${p.nome}</h5>
                  <p class="card-text">${p.prezzo}</p>
                  <a href="#" class="btn btn-primary">Aggiungi al carrello</a>
                </div>
             </div>
            <% } %>
        </div>
            
    </body>

</html>
