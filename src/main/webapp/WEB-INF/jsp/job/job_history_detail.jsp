<!DOCTYPE HTML>
<%@ include file="/taglibs.jsp"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html lang="en">
<head>
	<link rel="stylesheet" type="text/css" href="${path}/static/plugins/bootstrap/bootstrap-4.3.1-dist/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${path}/static/plugins/css/bootstrap-datetimepicker.min.css">
	<link rel="stylesheet" type="text/css" href="${path}/static/plugins/bootstrap/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${path}/static/plugins/css/tempusdominus-bootstrap-4.min.css">
    
    <title>Scheduler History Detail</title>
    <style>
		.table {
		   	width: 40% !important; 
		   	overflow-y: auto !important;
		}
		.table td {
   			text-align: left;   
		}
	</style>
</head>
<script type="text/javascript" src="${path}/static/plugins/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="${path}/static/plugins/js/popper.min.js"></script>
<script type="text/javascript" src="${path}/static/plugins/js/moment.min.js"></script>
<script type="text/javascript" src="${path}/static/plugins/bootstrap/bootstrap-4.3.1-dist/js/bootstrap.min.js"></script> 
<script type="text/javascript" src="${path}/static/plugins/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="${path}/static/plugins/js/tempusdominus-bootstrap-4.min.js"></script>
<body>
<div>
	<jsp:include page="/static/common/include/menu_bar.jsp" />
	<br />
	<div class="container">
		<div class="row">
			<div class="col-sm table-responsive bg-light text-dark tableDetailcontainer">
				<table class="table table-borderless" id="schedulerHistorytable" >
		  			<thead style="display:none;">
		    			<tr>
							<th scope="col">Field Name</th>
						    <th scope="col">:</th>
						    <th scope="col">Field Value</th>
		    			</tr>
		  			</thead>
		  			<tbody>
		    			<tr>
						    <td>Job Name</td>
						    <td>:</td>
						    <td>${history.sjhJobName}</td>
		    			</tr>
		    			<tr>
						    <td>Job Group</td>
						    <td>:</td>
						    <td>${history.sjhJobGroup}</td>
		    			</tr>
		    			<tr>
						    <td>Fired Time</td>
						    <td>:</td>
						    <td>${history.sjhFireTime}</td>
		    			</tr>
		    			<tr>
						    <td>Trigger Name</td>
						    <td>:</td>
						    <td>${history.sjhTriggerName}</td>
		    			</tr>
		    			<tr>
						    <td>HTTP Method</td>
						    <td>:</td>
						    <td>${history.sjhHttpMethod}</td>
		    			</tr>
		    			<tr>
						    <td>URL</td>
						    <td>:</td>
						    <td>${history.sjhUrl}</td>
		    			</tr>
		    			<tr>
						    <td>URL Parameter</td>
						    <td>:</td>
						    <td>${history.sjhParams}</td>
		    			</tr>
		  			</tbody>
				</table>
			</div>
			<div class="col-sm bg-light text-dark" style="margin-left: 20px;">
				<table class="table table-borderless">
		  			<thead style="display:none;">
		    			<tr>
							<th scope="col">Field Name</th>
						    <th scope="col">:</th>
						    <th scope="col">Field Value</th>
		    			</tr>
		  			</thead>
		  			<tbody>
		    			<tr>
						    <td>Request Body</td>
						    <td>:</td>
						    <td>${history.sjhRequestBody}</td>
		    			</tr>
		    			<tr>
						    <td>Response Status</td>
						    <td>:</td>
						    <td>${history.sjhResponseStatus}</td>
		    			</tr>
		    			<tr>
						    <td>Response Body</td>
						    <td>:</td>
						    <td>${history.sjhRequestBody}</td>
		    			</tr>
		  			</tbody>
				</table>
    		</div>
		</div>
	</div>
	<br />
	<div class="container" style="text-align: right;">
		<a class="btn btn-primary" href="${path}/job/history" role="button">
			<i class="fa fa-chevron-right" aria-hidden="true"></i>
		</a>
	</div>
	<br />
</div>
<script type="text/javascript">
</script>
</body>
</html>