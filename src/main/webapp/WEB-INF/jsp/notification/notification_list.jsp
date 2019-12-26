<!DOCTYPE HTML>
<%@ include file="/taglibs.jsp"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html lang="en">
<head>
	<link rel="stylesheet" type="text/css" href="${path}/static/plugins/bootstrap/bootstrap-4.3.1-dist/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${path}/static/plugins/css/dataTables.bootstrap4.min.css">
	<link rel="stylesheet" type="text/css" href="${path}/static/plugins/bootstrap/font-awesome-4.7.0/css/font-awesome.min.css">
	
	<title>Notification History</title>
	<style>
		.table {
			margin: auto;
		   	width: 85% !important; 
		   	overflow-y: auto !important;
		   	text-align: center;
		}
		.createNotification {
			text-align: center;
			margin-left: 110px !important;
		}
		.dataTables_info {
			width: 75%;
			float: center;
			text-align: center;
		}
		.dataTables_paginate {
			width: 85%;
			float: center;
			text-align: center;
		}
	</style>
</head>
<script type="text/javascript" src="${path}/static/plugins/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="${path}/static/plugins/js/popper.min.js"></script>
<script type="text/javascript" src="${path}/static/plugins/bootstrap/bootstrap-4.3.1-dist/js/bootstrap.min.js"></script> 
<script type="text/javascript" src="${path}/static/plugins/js/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="${path}/static/plugins/js/dataTables.bootstrap4.min.js"></script> 
<body>
<div>
	<jsp:include page="/static/common/include/menu_bar.jsp" />
	<br />
	<div class="table-responsive">
		<a class="btn btn-primary createNotification" href="${path}/notification/create?notifId=" role="button">
			<i class="fa fa-plus"></i> Notification
		</a>
		<br /><br />
		<table class="table" id="notificationListtable">
			<thead class="thead-light">
				<tr>
					<th class="text-center" scope="col">#</th>
					<th class="text-center" scope="col">Notification Type</th>
					<th class="text-center" scope="col">Host</th>
					<th class="text-center" scope="col">Action</th>
				</tr>
			</thead>
		<tbody>
			<c:forEach var="notif" items="${notificationList}">
	    		<tr>
	    			<td>${notif.ncId}</td>
	    			<td>${notif.platformType}</td>
	    			<td>${notif.ncHost}</td>
	    			<td>
	    				<a class="btn btn-primary" href="${path}/notification/create?notifId=${notif.ncId}" role="button">
	    					<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
	    				</a>
	    			</td>
	    		</tr>
			</c:forEach>
		</tbody>
		</table>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function () {
	$('#notificationListtable').DataTable({
		"ordering" :false,
		"lengthChange": false,
	    "searching": false,
	    "autoWidth": false,
	    "pageLength": 5
	});
});
</script>
</body>
</html>