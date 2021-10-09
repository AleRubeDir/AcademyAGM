<%-- 
    Document   : signup
    Created on : 7 ott 2021, 17:46:56
    Author     : Alessandro
--%>

<%@page import="java.util.regex.Matcher"%>
<%@page import="java.util.regex.Pattern"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
                   <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
           <style>
        html {
  height:100%;
}

body {
  margin:0;
}
.center {
  margin: 0;
  position: absolute;
  top: 50%;
  left: 50%;
  -ms-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
}
.bg {
  animation:slide 3s ease-in-out infinite alternate;
  background-image: linear-gradient(-60deg, #6c3 50%, #09f 50%);
  bottom:0;
  left:-50%;
  opacity:.5;
  position:fixed;
  right:-50%;
  top:0;
  z-index:-1;
}

.bg2 {
  animation-direction:alternate-reverse;
  animation-duration:4s;
}

.bg3 {
  animation-duration:5s;
}

.content {
  background-color:rgba(255,255,255,.8);
  border-radius:.25em;
  box-shadow:0 0 .25em rgba(0,0,0,.25);
  box-sizing:border-box;
  left:50%;
  padding:10vmin;
  position:fixed;
  text-align:center;
  top:50%;
  transform:translate(-50%, -50%);
}

h1 {
  font-family:monospace;
}

@keyframes slide {
  0% {
    transform:translateX(-25%);
  }
  100% {
    transform:translateX(25%);
  }
}
        
    </style>
        
        <title>Signup</title>
    </head>
    <body>
          <div class="bg"></div>
        <div class="bg bg2"></div>
        <div class="bg bg3"></div>
    <center>
        <h1 style="margin-top: 5rem;">Registrati a Shoppy!</h1>
     <form class="center" action="<%= request.getContextPath()%>/signup" method="post"> 
            <h2>Inserisci i tuoi dati</h2> 
            <label> Username: <input type="text" name="usr" placeholder="mario.rossi@gmail.com"> </label> <br>
            <%
  
            Object err = request.getAttribute("err");
            String errC = "";
            if(err != null){
                errC = request.getAttribute("err").toString();
            }else{
                errC = "invisible";
            }

            Object err2 = request.getAttribute("err2");
            String errC2 = "";
            if(err2 != null){
                errC2 = request.getAttribute("err2").toString();
            }else{
                errC2 = "invisible";
            }
            
            Object err3 = request.getAttribute("err3");
            String errC3 = "";
            if(err3 != null){
                errC3 = request.getAttribute("err3").toString();
            }else{
                errC3 = "invisible";
            }

            %>
            <label> Password: <input type="password" name="pwd"> </label><br>
            <label> Nome del tuo primo animale domestico? </label><br>
            <label> Risposta: <input type="text" name="recovery">  </label> <br>
            <input type="submit" class="btn btn-primary" value="Registrati"> <br/>
            <span style="color: red; background: transparent" class="<%out.println(errC);%>"> Inserire una mail valida</span></br>
            <span style="color: red; background: transparent" class="<%out.println(errC2);%>"> Email già utilizzata</span></br>
            <span style="color: red; background: transparent" class="<%out.println(errC3);%>"> Inserisci una risposta per il recupero password </span></br>
            
        </form> 
    </center>
    </body>
</html>
