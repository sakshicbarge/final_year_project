<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Trust Is Good</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link href="style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/cufon-yui.js"></script>
<script type="text/javascript" src="js/arial.js"></script>
<script type="text/javascript" src="js/cuf_run.js"></script>
<script type="text/javascript" src="js/ajax.js"></script>

<script type="text/javascript" src="jquery/jquery-1.9.1.js"></script>   
<script type="text/javascript" src="jquery/jquery-ui.js"></script>
    
<link rel="stylesheet" href="jquery-ui-1.10.3/themes/base/jquery-ui.css"/>
<link rel="stylesheet" href="jQuery.jqGrid/css/ui.jqgrid.css"/>
<script src="jQuery.jqGrid/js/i18n/grid.locale-en.js"></script>
<script src="jQuery.jqGrid/js/jquery.jqGrid.src.js"></script>
<style type="text/css">
.ui-jqgrid tr.jqgrow td {vertical-align:top !important}
</style>
<script type="text/javascript">
jQuery(document).ready(function() {
        $("#list").jqGrid({
                url : "fat",
                datatype : "json",
                mtype : 'POST',
                colNames : [  'FileName','Server Location','Block NO','Packet NO', 'Signature' ],
                colModel : [ 
                         {
                        name : 'usrFile',
                        index : 'usrFile',
                        
                        width : 150,
                        editable : true
                },
                {
                		name : 'serLoc',                
                        index : 'serLoc',
                        width : 100
                },
                {
                        name : 'blckNo',
                        index : 'blckNo',
                        width : 150,
                        editable : true
                },
                {
                        name : 'packNo',
                        index : 'packNo',
                        width : 150,
                        editable : true
                }, {
                        name : 'blckSign',
                        index : 'blckSign',
                        width : 250,
                        editable : true
                }
                ],
                pager : '#pager',
                loadonce:true,
                rowNum : 1,
                rowList : [ 1, 2, 3,4 ,5,6,7,8,9,10],
                sortname : 'invid',
                sortorder : 'desc',
                viewrecords : true,
                gridview : true,
                caption : 'Data Report',
                jsonReader : {
                        repeatitems : false,
                },
                editurl : "fat"
        });
        jQuery("#list").jqGrid('navGrid', '#pager', {
                edit : true,
                add : true,
                del : true,
                search : true
        });
});

</script>
</head>
<body>
<div class="main">
  <div class="header">
    <div class="header_resize">
      <div class="logo">
        <h1><a href="#"><small> </small>Trust Is Good Control Is Better</a></h1>
      </div>
      <div class="menu_nav">
        <ul>
          <li><a href="Upload.jsp"><span><span>Home</span></span></a></li>
          <li class="active"><a href="FileSystem.jsp"><span><span>FATFS</span></span></a></li>
        </ul>
      </div>
      <div class="clr"></div>
    </div>
  </div>
  <div class="hbg">&nbsp;</div>
  <div class="content">
    <div class="content_resize">
     <div class="mainbar">
      <div class="article">
    	<br/>
    	<br/>
		 <table id="list">
            <tr>
                 <td/>
            </tr>
     	</table>
     	<div id="pager"></div>
        <div class="clr"></div>
      
        </div>
    </div>
    <div class="sidebar">
        <div class="gadget">
                     <a href="index.jsp" style="float:right;"><font color="black" size="2">LogOut</font></a>

          <div class="clr"></div>
         
        </div>
       </div>
  .  </div>
  
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
     
      <div class="clr"></div>
    </div>
  </div>
</div>
</body>
</html>