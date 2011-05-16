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
<div class="wrapper col1">
  <div id="head">
    <h1><a href="#">The IT Restaurant</a></h1>
    <p>(Free Open Source CSS Website Template)</p>
    <div id="topnav">
      <ul>
        <li><a class="active" href="index.html">Home</a></li>
        <li><a>Style Demo</a></li>
        <li><a href="full-width.html">Full Width</a></li>
        <li><a href="#">DropDown</a>
          <ul>
            <li><a href="#">Link 1</a></li>
            <li><a href="#">Link 2</a></li>
            <li><a href="#">Link 3</a></li>
          </ul>
        </li>
        <li class="last"><a href="#">A Long Link Text</a></li>
      </ul>
    </div>
  </div>
</div>
<div class="wrapper col2">
  <div id="gallery">
    <ul>
      <li class="placeholder" style="background-image:url(images/1.jpg);">Image Holder</li>
      <li><a class="swap" style="background-image:url(images/2.jpg);" href="#gallery"><strong>Services</strong><span><img src="images/demo/gallery_1.gif" alt="" /></span></a></li>
      <li><a class="swap" style="background-image:url(images/demo/290x105.gif);" href="#gallery"><strong>Products</strong><span><img src="images/demo/gallery_2.gif" alt="" /></span></a></li>
      <li class="last"><a class="swap" style="background-image:url(images/demo/290x105.gif);" href="#gallery"><strong>Company</strong><span><img src="images/demo/gallery_3.gif" alt="" /></span></a></li>
    </ul>
    <div class="clear"></div>
  </div>
</div>
<div class="wrapper col4">
  <div id="container">
    <div id="content">
      <h1>About This Free CSS Template</h1>
      <div class="homecontent">
        <ul>
          <li>
            <p class="imgholder"><img src="images/8.jpg" alt="" /></p>
            <p class="readmore"><a href="#">Read More &raquo;</a></p>
          </li>
          <li class="last">
            <p class="imgholder"><img src="images/demo/286x100.gif" alt="" /></p>
            <h2>Indonectetus facilis leo nibh</h2>
            <p>Nullamlacus dui ipsum conseque loborttis non euisque morbi penas dapibulum orna.</p>
            <p>Urnaultrices quis curabitur phasellentesque congue magnis vestibulum quismodo nulla et feugiat. Adipisciniapellentum leo ut consequam ris felit elit id nibh sociis malesuada.</p>
            <p class="readmore"><a href="#">Read More &raquo;</a></p>
          </li>
        </ul>
        <div class="clear"></div>
      </div>
      <p>Odiointesque at quat nam nec quis ut feugiat consequet orci liberos. Tempertincidunt sed maecenas eros elerit nullam vest rhoncus diam consequat amet. Diamdisse ligula tincidunt a orci proin auctor lacilis lacilis met vitae.</p>
    </div>
    <div id="column">
      <div id="featured">
        <ul>
          <li>
            <h2>Indonectetus facilis leonib</h2>
            <p class="imgholder"><img src="images/demo/240x90.gif" alt="" /></p>
            <p>Nullamlacus dui ipsum conseque loborttis non euisque morbi penas dapibulum orna. Urnaultrices quis curabitur phasellentesque congue magnis vestibulum quismodo nulla et feugiat. Adipisciniapellentum leo ut consequam ris felit elit id nibh sociis malesuada.</p>
            <p class="more"><a href="#">Read More &raquo;</a></p>
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
    <div id="contactform">
      <h2>Why Not Contact Us Today !</h2>
      <form action="#" method="post">
        <fieldset>
        <legend>Contact Form</legend>
        <label for="fullname">Name:
        <input id="fullname" name="fullname" type="text" value="" />
        </label>
        <label for="emailaddress" class="margin">Email:
        <input id="emailaddress" name="emailaddress" type="text" value="" />
        </label>
        <label for="phone">Telephone:
        <input id="phone" name="phone" type="text" value="" />
        </label>
        <label for="subject" class="margin">Subject:
        <input id="subject" name="subject" type="text" value="" />
        </label>
        <label for="message">Message:<br />
        <textarea id="message" name="message" cols="40" rows="4"></textarea>
        </label>
        <p>
          <input id="submitform" name="submitform" type="submit" value="Submit" />
          &nbsp;
          <input id="resetform" name="resetform" type="reset" value="Reset" />
        </p>
        </fieldset>
      </form>
    </div>
    <!-- End Contact Form -->
    <div id="compdetails">
      <div id="officialdetails">
        <h2>Company Information !</h2>
        <ul>
          <li>Company Name Ltd</li>
          <li>Registered in England &amp; Wales</li>
          <li>Company No. xxxxxxx</li>
          <li class="last">VAT No. xxxxxxxxx</li>
        </ul>
        <h2>Stay in The Know !</h2>
        <p><a href="#">Get Our E-Newsletter</a> | <a href="#">Grab The RSS Feed</a></p>
      </div>
      <div id="contactdetails">
        <h2>Our Contact Details !</h2>
        <ul>
          <li>Company Name</li>
          <li>Street Name &amp; Number</li>
          <li>Town</li>
          <li>Postcode/Zip</li>
          <li>Tel: xxxxx xxxxxxxxxx</li>
          <li>Fax: xxxxx xxxxxxxxxx</li>
          <li>Email: info@domain.com</li>
          <li class="last">LinkedIn: <a href="#">Company Profile</a></li>
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

    </body>
</html>
