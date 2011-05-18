<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
    <p>School project</p>
    <div id="topnav">
      <ul>
        <li><a  href="index.jsp">Home</a></li>
        <% if(name != null && !"".equals(name)) { %>
        <li><a href="Reservation.jsp">Reservation</a></li>
        <li><a class="active" href="MyProfile.jsp">My Profile</a></li>
        <% } %>
      </ul>
    </div>
  </div>
</div>
<div class="wrapper col4">
  <div id="container">
   
      <div class="homecontent">
        <ul>
          <li>
            <h1>Your details</h1>
            <p>Email : ${user.email} </p>
            <p>First name : ${user.name} </p>
            <p>Surname : ${user.surname} </p>

          </li>
          <li class="last">
            <h1>Your reservations :</h1> <!--
             <c:forEach items="${reservations}" var="book">
            <tr>
                <td><c:out value="${book.isbn}"/></td>
                <td><c:out value="${book.name}"/></td>
                <td><c:out value="${book.author}"/></td>
                <td><c:out value="${book.pageNumber}"/></td>
                <td><c:out value="${book.owner}"/></td>
                <td><c:out value="Book"/></td>
            </tr>
        </c:forEach> -->
          </li>
        </ul>
        <div class="clear"></div>
      </div>
      <p>This application is created by students, so please dont use you primary passwords</p>
 

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
