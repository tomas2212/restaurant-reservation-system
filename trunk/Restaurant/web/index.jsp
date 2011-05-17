<%-- 
    Document   : index
    Created on : 28.4.2011, 12:44:42
    Author     : Tomasius
--%>
<!--
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
   -->

   <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN" dir="ltr">
<head profile="http://gmpg.org/xfn/11">
<title>Corporation</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="imagetoolbar" content="no" />
<link rel="stylesheet" href="styles/layout.css" type="text/css" />
</head>
<body id="top">
    <% String name = (String)session.getAttribute("name");
            if(name == null || "".equals(name)) { %>
    <div class="corner">
        <p><a class="login" href="Registration.jsp">Log in</a> </p>
    </div>
     <% } else { %>
     <p class="login"> Hello <%=session.getAttribute("name") %>! </p>
    <p><a class="logout" href="${pageContext.request.contextPath}/LoginServlet?logout=true">Log out</a></p>
    <% } %>
<div class="wrapper col1">
  <div id="head">
    <h1><a href="#">The IT Restaurant</a></h1>
    <div id="topnav"> 
      <ul>
        <li><a class="active" href="index.jsp">Home</a></li>

        <li><a href="Reservation.jsp">Reservation</a></li>
      </ul>
    </div>
    
    
  </div>
    
</div>

    
<div class="wrapper col2">
  <div id="gallery">
    <ul>
      <li class="placeholder" style="background-image:url(images/1.jpg);">Image Holder</li> <br/> <br/>
      <li style="padding-left: 100px; text-indent:50px; font-size: 1.5em;">       M.Briskar : <strong> "best restaurant ever" </strong> </li>
    </ul>
    <div class="clear"></div>
  </div>
</div>
<div class="wrapper col4">
  <div id="container">
    <div id="content">
      <h1>About The restaurant</h1>
      <div class="homecontent">
        <ul>
          <li>
            <p class="imgholder"><img src="images/8.jpg" alt="" /></p>
            <h2>Our environment is clear</h2>
            <p>and so on</p>
            
          </li>
          <li class="last">
            <p class="imgholder"><img src="images/demo/286x100.gif" alt="" /></p>
            <h2>Our menu is the best in the city</h2>
            <p>We are cool restaurant</p>
            <p>Our prices .... </p>
            
          </li>
        </ul>
        <div class="clear"></div>
      </div>
      <p>Come visit us!</p>
    </div>
    <div id="column">
      <div id="featured">
        <ul>
          <li>
            <h2>Indonectetus facilis leonib</h2>
            <p class="imgholder"><img src="images/demo/240x90.gif" alt="" /></p>
            <p>Nullamlacus dui ipsum conseque loborttis non euisque morbi penas dapibulum orna. Urnaultrices quis curabitur phasellentesque congue magnis vestibulum quismodo nulla et feugiat. Adipisciniapellentum leo ut consequam ris felit elit id nibh sociis malesuada.</p>
            
          </li>
        </ul>
      </div>
      <div class="holder">
        <div class="imgholder"><img src="images/demo/290x100.gif" alt="" /></div>
        <p>Nullamlacus dui ipsum conseque loborttis non euisque morbi penas dapibulum orna.</p>
        <p class="readmore"><a href="#">Read More &raquo;</a></p>
      </div>
    </div>
    <div class="clear"></div>
  </div>
</div>
<div class="wrapper col5">
  <div id="footer">
    
  
     <div id="compdetails">
      <div id="officialdetails">
        <h2>IT Restaurant</h2>
        <ul>
          <li>For the school project</li>
          <li class="last">PB158</li>
        </ul>
      </div>
      <div id="contactdetails">
        <h2>Our Contact Details !</h2>
        <ul>
          <li>IT Restaurant</li>
          <li>Masaryk University</li>
          <li>Brno</li>
          <li>Tel: xxxxx xxxxxxxxxx</li>
          <li></li>
          <li>Email: </li>
          <li class="last">Fax: xxxxx xxxxxxxxxx </li>
        </ul>
      </div>
      <div class="clear"></div>
    </div>
    <!-- End Company Details -->
    <div id="copyright">
      <p class="fl_left">Copyright &copy; 2010 - All Rights Reserved - <a href="#">Domain Name</a></p>
      <p class="fl_right">Template by <a href="http://www.os-templates.com/" title="Open Source Templates">OS Templates</a></p>
      <br class="clear" />
    </div>
    <div class="clear"></div>
  </div>
</div>
</body>

 
</html>
