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
		.processButtons {
			text-align: left;
			width: 85% !important;
			display: table;
			margin-left: 100px !important;
		}
		.processButtonleft {
			display: table-cell;
		}
		.processButtonright {
			display: table-cell;
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
</html>
