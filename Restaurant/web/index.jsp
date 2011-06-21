
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN" dir="ltr">
<head profile="http://gmpg.org/xfn/11">
<title>Reservation System</title>
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
    <p>School project</p>
    <div id="topnav"> 
      <ul>
        <li><a class="active" href="index.jsp">Home</a></li>
        <% if(name != null && !"".equals(name)) { %>
        <li><a href="Reservation.jsp">Reservation</a></li>
        <li><a href="MyProfile.jsp">My Profile</a></li>
        <% } %>
      </ul>
    </div>
    
    
  </div>
    
</div>

    
<div class="wrapper col2">
  <div id="gallery">
    <ul>
      <li class="placeholder" style="background-image:url(images/1.jpg);">Image Holder</li> <br/> <br/>
      <li style="padding-left: 100px; text-indent:50px; font-size: 1.5em;">M.Briskar : <strong> "best restaurant ever" </strong> </li>
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
            <p class="imgholder">
              <% if(name != null && !"".equals(name)) { %>
              <a href="Reservation.jsp" > <img src="images/8.jpg" alt="" /> </a> 
              <% } else { %> 
              <a href="Registration.jsp" > <img src="images/8.jpg" alt="" /> </a> 
              <% }%>
            </p>

            <h2>NEW RESERVATION SYSTEM</h2>
            <p>If you are logged, please feel free to try our new reservetion system for the tables in our restaurant.</p>
            <p>We are working hard to make a reservation much easier</p>
            <% if(name != null && !"".equals(name)) { %>
              <a href="Reservation.jsp" > Reserve now ! </a>
              <% } else { %>
              <a href="Registration.jsp" > Reserve now ! </a>
              <% }%>
            
          </li>
          <li class="last">
            <p class="imgholder"><img src="images/second.jpg" alt="" /></p>
            <h2>Our food is the best in the city</h2>
            <p>We pay attention to the trend and make the food like everyone wants it</p>
            <p>Our prices are the biggest, but why not to pay for such a good meat? </p>
            
          </li>
        </ul>
        <div class="clear"></div>
      </div>
      
    </div>
    <div id="column">
      <div id="featured">
        <ul>
          <li>
            <h2>We are a 5-star restaurant !</h2>
            <p class="imgholder"><img src="images/star.jpg" alt="" /></p>
            <p>We have achieved the fifth star for the newest menu we have prepared. </p>
             <p>Now we know, it's a really good restaurant. </p>
            
          </li>
        </ul>
      </div>
      <div class="holder">
        <div class="imgholder"><img src="images/hiring.jpg" alt="" /></div>
        <p>We are looking for some new chefs and waitresses to join us.</p>
        <p>If you are interested, feel free to contact us.</p>
       
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
          
          <li>Email: </li>
          <li class="last"></li>
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
