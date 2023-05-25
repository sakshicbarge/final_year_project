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
<script>
function selectFiles(typ)
{
	//alert(typ);
	if(typ.toString()=="clear")
	{		
		document.attfrm.files.value={};		
	}
	else
	{		
		document.attfrm.editContent.value="";
	}
	document.attfrm.action="filesDyna";
	document.attfrm.submit();
}
</script>
</head>
<body>
<div class="main">
  <div class="header">
    <div class="header_resize">
      <div class="logo">
        <h1><a href="#"><small> </small>Efficient File Auditing</a></h1>
      </div>
      <div class="menu_nav">
        <ul>
          
          <li><a href="index.jsp"><span><span>Home</span></span></a></li>
      	  <li class="active"><a href="attack"><span><span>Attacker</span></span></a></li>
      	  
        </ul>
      </div>
      <div class="clr"></div>
    </div>
  </div>
  <div class="hbg">&nbsp;</div>
  <div class="content">
    <div class="content_resize">     	
         <s:form name="attfrm" action="files">
             <s:select list="servList" label="Servers" name="serverUI" id="server"></s:select>  
             <s:select list="blockList" label="Blocks" name="blocks" id="blocks" onchange="selectFiles('clear')"></s:select> 
             <s:select list="fileList" label="Files" name="files" id="files" onchange="selectFiles('s')"></s:select>
             <s:submit name="save" value="Save"></s:submit> 
             <s:textarea value="%{content}" name="editContent" rows="30" cols="100"></s:textarea>   
        </s:form>
        </div>
      <div class="clr"></div>
      
    </div>
  </div>
  <div class="fbg">
    <div class="fbg_resize">
    
    </div>
  </div>
  <div class="footer">
    <div class="footer_resize">
     
      <div class="clr"></div>
    </div>
  </div>
</div>
</body>
</html>
