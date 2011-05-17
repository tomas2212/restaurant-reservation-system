<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


   <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN" dir="ltr">
<head profile="http://gmpg.org/xfn/11">
<title>Corporation</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="imagetoolbar" content="no" />
<link rel="stylesheet" href="styles/layout.css" type="text/css" />
</head>
<body id="top">
<div class="wrapper col1">
  <div id="head">
    <h1><a href="#">The IT Restaurant</a></h1>
    <p>(Free Open Source CSS Website Template)</p>
    <div id="topnav">
      <ul>
          <li><a class="active" href="index.jsp">Home</a></li> <% String name = (String)session.getAttribute("name");
            if(name == null || "".equals(name)) { %>
        <li><a href="Registration.jsp">Registration</a></li>  <% } %>
        <li><a href="Reservation.jsp">Reservation</a></li>
      </ul>
    </div>
  </div>
</div>
<div class="wrapper col4">
  <div id="container">
    <div id="content">
      <h1>Who are you?</h1>
      <div class="homecontent">
        <ul>
          <li>
            <h1>In the case you are not registered, register here</h1>
            <form action="${pageContext.request.contextPath}/LoginServlet" method="post" >
                Name : <input type="text" name="name" /> <br/>
                Password : <input type="text" name="password" /> <br/>
                <input type="Submit" value="Register" />
             </form>
          </li>
          <li class="last">
            <h1>In the case you are registered, log in here :</h1>
            <form action="${pageContext.request.contextPath}/LoginServlet" method="post" >
                Name : <input type="text" name="name" /> <br/>
                Password : <input type="text" name="password" /> <br/>
                <input type="Submit" value="Log in" />
             </form>
          </li>
        </ul>
        <div class="clear"></div>
      </div>
      <p>This application is created by students, so please dont use you primary passwords</p>
    </div>

    <div class="clear"></div>
  </div>
</div>

<div class="wrapper col5">
  <div id="footer">
    <!-- End Contact Form -->
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
