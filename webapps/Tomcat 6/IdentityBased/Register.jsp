<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Efficient File Auditing</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link href="style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/cufon-yui.js"></script>
<script type="text/javascript" src="js/arial.js"></script>
<script type="text/javascript" src="js/cuf_run.js"></script>
<script type="text/javascript">
function generateId()
{
	alert("Create ID");
	var x=Math.floor((Math.random()*899)+100);
	document.getElementById('regid').value="UID"+x;
}
</script>
</head>
<body>
<div class="main">
  <div class="header">
    <div class="header_resize">
      <div class="logo">
        <h1><a href="#"><small> </small><span>Efficient File Auditing</span></a></h1>
      </div>
      <div class="menu_nav">
        <ul>
          <li class="active"><a href="index.jsp"><span><span>Home</span></span></a></li>
          
        </ul>
      </div>
      <div class="clr"></div>
    </div>
  </div>
  <div class="hbg">&nbsp;</div>
  <div class="content">
    <div class="content_size">
     
      <h3>Register</h3>
       	  <s:form action="register" name="regform">
          <s:textfield name="regid" label="UserID" id="regid" onclick="generateId()"></s:textfield>
          <s:textfield name="regname" label="UserName"  ></s:textfield>
          <s:password name="regpasswd" label="Password"></s:password>
          <s:password name="regrepasswd" label="ReType Password"></s:password>
          <s:radio list="{'Male','Female'}" label="Gender" key="reggen" name="reggen"></s:radio>  
          <s:textfield name="regage" label="Age"  ></s:textfield>
          <s:textfield name="address" label="Address"  ></s:textfield>
          <s:textfield name="contact" label="Contact"  ></s:textfield>
          <s:textfield name="mail" label="Email"  ></s:textfield>
          <s:submit value="Submit"></s:submit>
          
          </s:form>
         
          <font size="2" color="Red"><s:actionerror/></font>
          <font color="red" size="2"><s:actionmessage/></font>
          <a href="index.jsp">Click here to login</a>
      <div class="clr"></div>
    </div>
  </div>
  <div class="fbg">
    <div class="fbg_resize">
      <div class="col c1">
        
      </div>
      <div class="col c2">
        
      </div>
      <div class="col c3">
        
      </div>
      <div class="clr"></div>
    </div>
  </div>
  <div class="footer">
    <div class="footer_resize">
      <p class="lf">Copyright &copy; <a href="#">Domain Name</a>. All Rights Reserved</p>
      <p class="rf">Design by <a target="_blank" href="http://www.rocketwebsitetemplates.com/">RocketWebsiteTemplates</a></p>
      <div class="clr"></div>
    </div>
  </div>
</div>
</body>
</html>