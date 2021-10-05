<%-- 
    Document   : home
    Created on : 29 set 2021, 09:53:25
    Author     : Alessandro
--%>

<%@page import="java.util.ArrayList"%>
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
        <title> Shoppy AGM </title>
    </head>
    <body class="mx-5 my-2">
        <h1>Benvenuti nel mio shop</h1>
       
   <style>
        .grid-container {
          display: grid;
          grid-template-columns: 1fr 1fr;
        }
        .grid-container-prodotto{
            display: grid;
            grid-template-columns: 1fr 1fr;
        }
        .grid-item {
          font-size: 30px;
          text-align: center;
          width: 18rem;
          margin-bottom: 35px;
          margin-right: 35px;
        }
        .list-group-item{
            width:300px;
            text-align: center;
        }
        .list-group{
            width:300px;
        }
        .mfixed{
            position: fixed ;
        }
        .mTop{
            margin-top : 150px;
        }
        .invisible{
            visibility: invisible;
       }
       .mNeg{
           margin-top: -100px;
       }
       .toRight{
           margin-left: 100rem; 
            margin-right: 0;
       }
        
    </style>
    <%
    String user = null;
    if(session.getAttribute("usr")==null) response.sendRedirect("/progettoAcademy/jsp/loginForm.jsp");
    %>
    <a href="${pageContext.request.contextPath}/logout" class="btn btn-primary toRight"> LOGOUT </a>
    <div class="grid-container">
        <div>
              <h1>Prodotti</h1>
        <div id="prodotti" class="grid-container-prodotto">
              
            <% ArrayList<Prodotto> list = (ArrayList<Prodotto>)request.getAttribute("list"); 
               ArrayList<Prodotto> carrello= (ArrayList<Prodotto>)request.getAttribute("carrello"); 
            %>
           <%
               for(Prodotto p : list) { %>
               <div class="grid-item">
                    <div class="card" >
                <img src=<% out.println(p.getImg()) ;%> class="card-img-top">
                <div class="card-body">
                  <h5 class="card-title"> <% out.println(p.getNome()) ;%> </h5>
                  <p class="card-text"><% out.println(p.getPrezzo()) ;%> €</p>
                <form action="<%= request.getContextPath()%>/prodotti" method="post">
                    <input class="invisible" type="text" name="codice" value="<% out.println(p.getCodice());%>" readonly>
                    <input type="submit" class="btn btn-primary mNeg" value="Aggiungi al carrello" > 
                 </form>
              </div>
             </div>
               </div>
            <%  
            }
            %>
        </div>
        </div>
        <div class="mLeft ">
        <div id="carrello" class="mfixed mTop">
            <h1>Il mio carrello</h1>
            <ul class="list-group">
                
                <%
                    Double totale = 0.0;
                    for(Prodotto p : carrello){
                        totale += p.getPrezzo();
                %>
                <li class="list-group-item" > <% out.println(p.getNome()) ;%> <% out.println(p.getPrezzo()) ;%>€ 
                    
                 <form action="<%= request.getContextPath()%>/prodotti" method="post">
                    <input class="invisible" type="text" name="elimina" value="<% out.println(p.getCodice());%>" readonly>
                    <input class="invisible" type="text" name="codice" value="-1" readonly>
                    <input type="submit" class="btn btn-primary" value="ELIMINA" > 
                 </form>
                 
                </li> 
                 <%  
                }
            %>
            <div class="mx-auto my-3">
              <form action="<%= request.getContextPath() %>/prodotti" method="post">
                <input type="submit" name="button2" class="btn btn-primary" value="Compra!" > 
              </form>
            </div>
            </ul>
        </div>
        <div id="totale" class="mfixed">
                      <h1>Totale</h1>
                <ul class="list-group">
                    <li class="list-group-item" style="text-alig:center;"><%out.println(totale);%> € </li>
                </ul>
            </div>
               
        </div>
    </div>
    </body>

</html>
