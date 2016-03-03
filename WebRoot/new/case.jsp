<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="zh-Ch">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SYSU WfClient: Workdesk</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">

    <!-- Custom styles for this template -->
    <link href="css/offcanvas.css" rel="stylesheet">

  </head>

  <body>
	<%@ include file="common/header.jsp" %>
	
    <div class="container">
      <div class="row row-offcanvas row-offcanvas-right">
        
        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
          <div class="list-group">
            <a href="case.action" class="list-group-item active">过程管理</a>
            <a href="#" class="list-group-item">资源管理</a>
            <a href="app.action" class="list-group-item">应用管理</a>
            <a href="service.action" class="list-group-item">YAWL组件管理</a>
          </div>
        </div><!--/span-->
        
        <div class="col-xs-12 col-sm-9">
        	<h4>Loaded Specifications</h4><hr>
        	<table class="table table-striped table-condensed">
        		<thead>
					<tr>
					<th>Name</th>
					<th>Version</th>
					<th>Status</th>
					<th>Documentation</th>
					<th>Actions</th>
					</tr></thead>
				<tbody id="tbody1">

				</tbody>
			</table><br /><br /><br />
									
			<form name="uploadForm" action="upload.action" method="post" enctype="multipart/form-data">
				<div class="row">
					<div class="col-xs-6"><input type="file" class="btn btn-default" name="ywl"></div>
				  	<div class="col-xs-6"><input type="submit" class="btn btn-default" value="Upload"></div>
				</div>
			</form><br /><br /><br />
			
			<div class="panel-group" id="accordion">
			
			  <div class="panel panel-default">
			    <div class="panel-heading">
			      <h4 class="panel-title">
			       <a data-toggle="collapse" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
			       		Running Cases
			       </a>
			      </h4>
			    </div>
			    <div id="collapseOne" class="panel-collapse collapse in">
			    	<div class="panel-body">
					<table class="table table-striped table-condensed" id="applist">
		        		<thead>
							<tr>
							<th>case</th>
							<th>spec name</th>
							<th>spec version</th>
							<th>Action</th>
							</tr></thead>
						<tbody id="tbody2">
		
						</tbody>
					</table><br /><br /><br />
						
					</div>
			    </div>
			  </div>
			  		  
		  </div><!-- /panel-group -->
			
        </div><!--/span-->

      </div><!--/row-->

      <%@ include file="common/footer.jsp" %>
    </div><!--/.container-->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/offcanvas.js"></script>
	<script type="text/javascript">
	$(function(){
		$("#nav-mgt").addClass("active");
		
		$.ajax({
			url		:	"loadSpecs.action",
			type	:	"post",
			dataType:	"json",
			success	:	updateTable1
		});
		
		$.ajax({
			url		:	"loadRunningCases.action",
			type	:	"post",
			dataType:	"json",
			success	:	updateTable2
		});
	});
	
	function updateTable1(specsJson) {
		var specs = JSON.parse(specsJson);
		$("#tbody1").empty();
	
		$(specs).each(function() {
			var str = $('<tr/>');
			str.append("<td style='display:none'>"+this.identifier+"</td>");
			str.append("<td>"+this.name+"</td>");
			str.append("<td>"+this.version+"</td>");
			str.append("<td>"+this.status+"</td>");
			str.append("<td>"+this.uri+"</td>");
			
			var btns = $('<td/>');
			btns.append("<button class='btn btn-default btn-xs' onclick='launch(\""+this.identifier+"\",\""+this.version+"\",\""+this.uri+"\")'>Launch</button>&nbsp;");
			btns.append("<button class='btn btn-default btn-xs' onclick='unload(\""+this.identifier+"\",\""+this.version+"\",\""+this.uri+"\")'>Unload</button>&nbsp;");
			btns.append("<button class='btn btn-default btn-xs' onclick='getInfo(\""+this.identifier+"\")'>Get Info</button>&nbsp;");
			btns.append("<button class='btn btn-default btn-xs' onclick='downloadLog(\""+this.identifier+"\")'>Download Log</button>");
			str.append(btns);
			
			$("#tbody1").append(str);
		})		
	}
	
	function updateTable2(caseRunningJson) {
		var cases = JSON.parse(caseRunningJson);
		$("#tbody2").empty();
	
		$(cases).each(function() {
			var str = $('<tr/>');
			str.append("<td>"+this.id+"</td>");
			str.append("<td>"+this.specname+"</td>");
			str.append("<td>"+this.specversion+"</td>");
			
			var btns = $('<td/>');
			btns.append("<button class='btn btn-default btn-xs' onclick='cancel(\""+this.id+"\")'>Cancel Case</button>&nbsp;");
			btns.append("<button class='btn btn-default btn-xs' onclick='info(\""+this.id+"\")'>Get Info</button>&nbsp;");
			btns.append("<button class='btn btn-default btn-xs' onclick='logs(\""+this.id+"\")'>Get EventLogs</button>");
			str.append(btns);
			
			$("#tbody2").append(str);
		})
	}
	
	function launch(identifier, version, uri) {
		$.ajax({
			url		:	"launch.action",
			data	:	"identifier="+identifier+"&version="+version+"&uri="+uri,
			type	:	"post",
			dataType:	"json",
			success	:	updateTable2
		});	
	}
	
	function unload(identifier, version, uri) {
		$.ajax({
			url		:	"unload.action",
			data	:	"identifier="+identifier+"&version="+version+"&uri="+uri,
			type	:	"post",
			dataType:	"json",
			success	:	updateTable1
		});
	}
	
	function getInfo(identifier) {
		alert(identifier);

	}
	
	function downloadLog(identifier) {
		alert(identifier);

	}
	
	function cancel(id) {
		$.ajax({
			url		:	"cancel.action",
			data	:	"selectedCase="+id,
			type	:	"post",
			dataType:	"json",
			success	:	updateTable2
		});
	
	}
	</script>
  </body>
</html>