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
         <center> 
       <h1 style="margin-top: 0.5rem"> Shoppy AGM </h1>
       <h4 style="margin-top: 2rem"> Il mondo dello shopping </h4>
         </center> 
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
        
       .mNeg{
           margin-top: -100px;
       }
       .toRight{
           margin-left: 100rem; 
            margin-right: 0;
       }
       
.circle{
  position: absolute;
  border-radius: 70%;
  background: #3399ff;
  animation: ripple 15s infinite;
  box-shadow: 0px 0px 1px 0px #508fb9;
}

.small{
  width: 200px;
  height: 200px;
  left: -100px;
  bottom: -100px;
}

.medium{
  width: 400px;
  height: 400px;
  left: -200px;
  bottom: -200px;
}

.large{
  width: 600px;
  height: 600px;
  left: -300px;
  bottom: -300px;
}

.xlarge{
  width: 800px;
  height: 800px;
  left: -400px;
  bottom: -400px;
}

.xxlarge{
  width: 1000px;
  height: 1000px;
  left: -500px;
  bottom: -500px;
}

.smallr{
  width: 200px;
  height: 200px;
  right: -100px;
  bottom: -100px;
}

.mediumr{
  width: 400px;
  height: 400px;
  right: -200px;
  bottom: -200px;
}

.larger{
  width: 600px;
  height: 600px;
  right: -300px;
  bottom: -300px;
}

.xlarger{
  width: 800px;
  height: 800px;
  right: -400px;
  bottom: -400px;
}

.xxlarger{
  width: 1000px;
  height: 1000px;
  right: -500px;
  bottom: -500px;
}


.shade1{
  opacity: 0.2;
}
.shade2{
  opacity: 0.5;
}

.shade3{
  opacity: 0.7;
}

.shade4{
  opacity: 0.8;
}

.shade5{
  opacity: 0.9;
}

@keyframes ripple{
  0%{
    transform: scale(0.8);
  }
  
  50%{
    transform: scale(1.2);
  }
  
  100%{
    transform: scale(0.8);
  }
}
       
    </style>
    
 
    
         <div class="ripple-background">
  <div class="circle xxlarge shade1"></div>
  <div class="circle xlarge shade2"></div>
  <div class="circle large shade3"></div>
  <div class="circle mediun shade4"></div>
  <div class="circle small shade5"></div>
</div>
       <div class="ripple-background">
  <div class="circle xxlarger shade1"></div>
  <div class="circle xlarger shade2"></div>
  <div class="circle larger shade3"></div>
  <div class="circle mediunr shade4"></div>
  <div class="circle smallr shade5"></div>
</div>
   
    <a href="${pageContext.request.contextPath}/logout" class="btn btn-primary toRight"> LOGOUT </a>
    <p class="toRight">Ciao <% out.println(session.getAttribute("usr"));%> </p>
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
                  <p class="card-text"><% out.println("Qta: "+ p.getQta()) ;%></p>
                  <%
                      String c = "visible";
                      if(p.getQta()==0) c = "invisible";
                  %>
                <form action="<%= request.getContextPath()%>/prodotti" method="post">
                    <center> <label class="in<%out.println(c);%>" style="color : red;">Esaurito :c </label></center>
                    <input class="invisible" type="text" name="codice" value="<% out.println(p.getCodice());%>" readonly>
                    <input class="<%out.print(c);%>" style="width: 100px" type="number" name="qta" value="1" min="1" max=<%out.println(p.getQta());%>>
                    <input type="submit" class="<%out.print(c);%> my-2 btn btn-primary mNeg" name="button1" value="Aggiungi al carrello" > 
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
                        totale += p.getPrezzo()* p.getQta();
                %>
                <li class="list-group-item" > <% out.println(p.getNome()) ;%> <% out.println(p.getPrezzo()) ;%>€ 
                    
                 <form action="<%= request.getContextPath()%>/prodotti" method="post">
                     <% request.setAttribute("carrello",carrello); %>
                    <input class="invisible" type="text" name="elimina" value="<% out.println(p.getCodice());%>" readonly>
                    <label> Qta : <input type="number" name="qtaElimina" min=1 max=<% out.println(p.getQta());%> value=<% out.println(p.getQta());%>> </label>
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
