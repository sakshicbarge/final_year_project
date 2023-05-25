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
<script type="text/javascript" src="js/ajax.js"></script>

<script type="text/javascript" src="jquery/jquery-1.9.1.js"></script>   
<script type="text/javascript" src="jquery/jquery-ui.js"></script>
    
<link rel="stylesheet" href="jquery-ui-1.10.3/themes/base/jquery-ui.css"/>
<link rel="stylesheet" href="jQuery.jqGrid/css/ui.jqgrid.css"/>
<script src="jQuery.jqGrid/js/i18n/grid.locale-en.js"></script>
<script src="jQuery.jqGrid/js/jquery.jqGrid.src.js"></script>


<script type="text/javascript">

jQuery(document).ready(function() {


    $("#list").jqGrid({
                url : "Audit",
                datatype : "json",
                mtype : 'POST',
                multiselect: true,
	    		multiboxonly: true,
                colNames : [ 'JobId', 'StartTime', 'Location', 'FileName','AllocatedJobs', 'Status','EndTime' ],
                colModel : [ {
                        name : 'jobid',
                        index : 'jobid',
                        width : 40,
                        sortable:true,
                        sorttype:"int"
                }, {
                        name : 'startTime',
                        index : 'startTime',
                        width : 120,
                        editable : true,
                        sortable:true
                }, {
                        name : 'loc',
                        index : 'loc',
                        width : 100,
                        editable : true,
                        sortable:true
                }, {
                        name : 'usrFile',
                        index : 'usrFile',
                        width : 90,
                        editable : true,
                        sortable:true
                }, {
                	
                        name : 'audList',
                        index : 'audList',
                        width : 220,
                        editable : true,
                        sortable:true
                }, {
                        name : 'stat',
                        index : 'stat',
                        width : 220,
                        editable : true,
                        sortable:true
                }, {
                        name : 'endTime',
                        index : 'endTime',
                        width : 100,
                        editable : true,
                        sortable:true
                }
                ],
                pager : '#pager',
                rowNum :10,
                rowList : [ 10, 20, 30 ,40,50,60,70,80,90,100],
                sortname : 'jobid',
                sortorder : 'desc',
                viewrecords : true,
                gridview : true,
               
                caption : 'Audit Report',
                jsonReader : {
                        repeatitems : false,
                },
                editurl : "Audit"
        });
        jQuery("#list").jqGrid('navGrid', '#pager', {
                edit : false,
                add : false,
                del : false,
                search : true
        });
        $("#list").jqGrid('navButtonAdd','#pager',{
	
    id:'ExportToExcel',
    caption:'Export Selected Rows To CSV',
    title:'Export Selected Rows To CSV',
    onClickButton : function(e)
    {
        exportData(e, '#list');
    },
    buttonicon: 'ui-icon ui-icon-document'
	}
);
        	
});

setInterval(function() { $("#list").trigger("reloadGrid");},10000);
	

function exportData(e, id){
	
	var gridid 		= jQuery(id).getDataIDs(); // Get all the ids in array
	var label 		= jQuery(id).getRowData(gridid[0]); // Get First row to get the labels
	var selRowIds 	= jQuery(id).jqGrid ('getGridParam', 'selarrrow');	

	var obj 		= new Object();
	obj.count		= selRowIds.length;
	
	if(obj.count) {
		alert(obj.count);
		obj.items		= new Array();
		
		for(elem in selRowIds) {
			obj.items.push(jQuery(id).getRowData( selRowIds[elem] ));
		}
		
		var json = JSON.stringify(obj);
		
		JSONToCSVConvertor(json, "csv", 1);
	}
	JSONToCSVConvertor(js, "csv", 1);
}


function JSONToCSVConvertor(JSONData, ReportTitle, ShowLabel) {     

	//If JSONData is not an object then JSON.parse will parse the JSON string in an Object
	var arrData = typeof JSONData != 'object' ? JSON.parse(JSONData) : JSONData;
	var CSV = '';    
	//This condition will generate the Label/Header
	if (ShowLabel) {
	    var row = "";

	    //This loop will extract the label from 1st index of on array
	    for (var index in arrData.items[0]) {
	        //Now convert each value to string and comma-seprated
	        row += index + ',';
	    }
	    row = row.slice(0, -1);
	    //append Label row with line break
	    CSV += row + '\r\n';
	}

	//1st loop is to extract each row
	for (var i = 0; i < arrData.items.length; i++) {
	    var row = "";
	    //2nd loop will extract each column and convert it in string comma-seprated
	    for (var index in arrData.items[i]) {
	        row += '"' + arrData.items[i][index].replace(/(<([^>]+)>)/ig, '') + '",';
	    }
	    row.slice(0, row.length - 1);
	    //add a line break after each row
	    CSV += row + '\r\n';
	}

	if (CSV == '') {        
	    alert("Invalid data");
	    return;
	}   

	/*
	 * 
	 * FORCE DOWNLOAD
	 * 
	 */
	
	
	//this trick will generate a temp "a" tag
	var link = document.createElement("a");    
	link.id="lnkDwnldLnk";

	//this part will append the anchor tag and remove it after automatic click
	document.body.appendChild(link);

	var csv = CSV;  
	blob = new Blob([csv], { type: 'text/csv' }); 
	
	var myURL = window.URL || window.webkitURL;
	
	var csvUrl = myURL.createObjectURL(blob);
	var filename = 'UserExport.csv';
	jQuery("#lnkDwnldLnk")
	.attr({
	    'download': filename,
	    'href': csvUrl
	}); 

	jQuery('#lnkDwnldLnk')[0].click();    
	document.body.removeChild(link);
	
}

</script>--%>
</head>

<body>

  <div class="header">
    <div class="header_resize">
      <div class="logo">
        <h1><a href="#"><small> </small>Efficient File Auditing</a></h1>
      </div>
      <div class="menu_nav">
        <ul>
          <li><a href="index.jsp"><span><span>Home</span></span></a></li>
          <li><a href="AdminLog.jsp"><span><span>Admin</span></span></a></li>
          <li class="active"><a href="BlockChainRetrival"><span><span>Public Audit</span></span></a></li>
        </ul>
      </div>
     
      <div class="clr"></div>
    </div>
  </div>
  <div class="hbg">&nbsp;</div>
  <div class="content">
    <div class="content_resize">	
    Next Job Start Time:		
	 <a href="pause">pause</a>    
     <a href="resume">resume</a>
     <a href="Audit" target="_blank">JSON Data</a>
     <a href="clear">clear</a>
 
	<table id="list">
                <tr>
                        <td/>
                </tr>
     </table>
    
     <div id="pager"></div>
     <div class="clr"></div>
      
    </div>
  </div><%--
  <form action="BlockChainRetrival" method="get">
 <button> <a href="BlockChainRetrival">yyyyyyyyyyy</a></button></form>
  --%><div class="footer">
    <div class="footer_resize">
      <p class="lf">Copyright &copy; <a href="#">Domain Name</a>. All Rights Reserved</p>
      <p class="rf">Design by <a target="_blank" href="http://www.rocketwebsitetemplates.com/">RocketWebsiteTemplates</a></p>
      <div class="clr"></div>
    </div>
  </div>
</div>
</body>
</html>

