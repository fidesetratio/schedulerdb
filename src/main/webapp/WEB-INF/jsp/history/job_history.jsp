<!DOCTYPE HTML>
<%@ include file="/taglibs.jsp"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html lang="en">
<head>
	<link rel="stylesheet" type="text/css" href="${path}/static/plugins/bootstrap/bootstrap-4.3.1-dist/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${path}/static/plugins/css/dataTables.bootstrap4.min.css">
	
	<title>Scheduler History</title>
	<style>
		.table {
			margin: auto;
		   	width: 85% !important; 
		   	overflow-y: auto !important;
		}
		.table td {
   			text-align: center;   
		}
		.searchScheduler {
		   	display: table-cell;
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
<body>
<div>
	<jsp:include page="/static/common/include/menu_bar.jsp" />
	<br />
	<div class="table-responsive">
		<table class="table" id="schedulerHistorytable">
			<thead class="thead-light">
				<tr>
					<th scope="col"><input type="checkbox" style="display:none" id="parentBox" /></th>
					<th class="text-center" scope="col">Job Name</th>
					<th class="text-center" scope="col">Group Name</th>
					<th class="text-center" scope="col">Fired Time</th>
					<th class="text-center" scope="col">Response Status</th>
					<th class="text-center" scope="col">Action</th>
				</tr>
			</thead>
		<tbody>
			<c:forEach var="history" items="${schedulerHistorylist}">
	    		<tr>
	    			<td><input type="checkbox" class="checkId" style="display:none" name="childboxName" value="${history.sjhId}" />&nbsp;</td>
	    			<td>${history.sjhJobName}</td>
	    			<td>${history.sjhJobGroup}</td>
	    			<td>${history.sjhFireTime}</td>
	    			<td>${history.sjhResponseStatus}</td>
	    			<td><a class="btn btn-primary" href="${path}/history/${history.sjhId}" role="button">Detail</a></td>
	    		</tr>
			</c:forEach>
		</tbody>
		</table>
	</div>
</div>
<script type="text/javascript" src="${path}/static/plugins/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="${path}/static/plugins/js/popper.min.js"></script>
<script type="text/javascript" src="${path}/static/plugins/bootstrap/bootstrap-4.3.1-dist/js/bootstrap.min.js"></script> 
<script type="text/javascript" src="${path}/static/plugins/js/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="${path}/static/plugins/js/dataTables.bootstrap4.min.js"></script> 
<script type="text/javascript">
$(document).ready(function () {
	$('#schedulerHistorytable').DataTable({
		"ordering": false,
		"lengthChange": false,
        "searching": false,
        "autoWidth": false,
        "pageLength": 7,
	});
});
</script>
</body>
</html>