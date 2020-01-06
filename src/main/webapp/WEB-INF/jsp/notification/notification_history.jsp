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
		<table class="table" id="notificationHistorytable">
			<thead class="thead-light">
				<tr>
					<th class="text-center" scope="col">#</th>
					<th class="text-center" scope="col">Notification Type</th>
					<th class="text-center" scope="col">Sender</th>
					<th class="text-center" scope="col">Receiver</th>
					<th class="text-center" scope="col">Subject</th>
					<th class="text-center" scope="col">Status</th>
					<th class="text-center" scope="col">Send Date</th>
					<th class="text-center" scope="col">Action</th>
				</tr>
			</thead>
		<tbody>
			<c:forEach var="notif" items="${notificationHistorylist}">
	    		<tr>
	    			<td>${notif.nhId}</td>
	    			<td>${notif.nhNcNotiftype}</td>
	    			<td>${notif.nhSender}</td>
	    			<td>${notif.nhReceiver}</td>
	    			<td>${notif.nhSubject}</td>
	    			<c:choose>
  						<c:when test="${notif.nhStatus}">
  							<td>OK</td>
					  	</c:when>
  						<c:otherwise>
  							<td>-</td>
  						</c:otherwise>
					</c:choose>
	    			<td>${notif.nhSendDate}</td>
	    			<td>
	    				<a class="btn btn-primary" href="${path}/notification/${notif.nhId}" role="button">
	    					<i class="fa fa-info-circle" aria-hidden="true"></i>
	    				</a>
	    			</td>
	    		</tr>
			</c:forEach>
		</tbody>
		</table>
	</div>
</div>
</body>
</html>