<%-- 
    Document   : index
    Created on : 28.4.2011, 12:44:42
    Author     : Tomasius
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="IndexCss.css" type="text/css">
        <title>Reservation System</title>
    </head>
    <body>
        <div align="right" style="margin-top:10px;" >
         <form action="${pageContext.request.contextPath}/LoginServlet" method="post" >
                Name : <input type="text" name="name" /> <br/>
                Password : <input type="text" name="password" /> <br/>
                <input type="Submit" value="Zadat" />
         </form>
        </div>

        <div align="center" style="margin-top:10px; " ><h1>IT Restaurant</h1> </div>
        

        <div id="mainWindow">


        </div>


    </body>
</html>
