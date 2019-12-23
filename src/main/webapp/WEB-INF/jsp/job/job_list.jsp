<!DOCTYPE HTML>
<%@ include file="/taglibs.jsp"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html lang="en">
<head>
	<link rel="stylesheet" type="text/css" href="${path}/static/plugins/bootstrap/bootstrap-4.3.1-dist/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${path}/static/plugins/css/dataTables.bootstrap4.min.css">
	
	<title>Job List</title>
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
<body>
<div>
	<jsp:include page="/static/common/include/menu_bar.jsp" />
	<br />
	<div class="processButtons">
		<div class="processButtonleft">
			<a class="btn btn-light refreshButton" href="${path}/job" role="button">Refresh Page</a>
			<button type="button" class="btn btn-primary processButton">Pause All</button>
			<button type="button" class="btn btn-info processButton">Resume All</button>
		</div>
		<div class="searchScheduler">
			<form class="form-inline" action="${path}/job" method="post" autocomplete="off">
				<div class="dropdown mb-2 mr-sm-2">
					<select class="custom-select" name="jobSearchoption">
				    	<option value="bothJobnameGroup" selected>Search By</option>
				  		<option value="inJobName">Job Name</option>
				  		<option value="inJobGroup">Job Group</option>
					</select>
				</div>
				<input class="form-control mb-2 mr-sm-2" type="search" placeholder="Search" aria-label="Search" name="jobSearchinput">
				<button class="btn btn btn-info mb-2 mr-sm-2" type="submit">Search</button>
			</form>
		</div>
		<div class="processButtonright">
			<!-- <button type="button" class="btn btn-warning processButton">Start Now</button> -->
			<button type="button" class="btn btn-primary processButton">Pause</button>
			<button type="button" class="btn btn-info processButton">Resume</button>
			<button type="button" class="btn btn-warning processButton">Delete</button>
		</div>		
	</div>
	<br />
	<div class="table-responsive">
		<table class="table" id="jobDatalist">
			<thead class="thead-light">
				<tr>
					<th class="text-center" scope="col"><input type="checkbox" id="parentBox" /></th>
					<th class="text-center" scope="col">Job Name</th>
					<th class="text-center" scope="col">Group Name</th>
					<th class="text-center" scope="col">Schedule Time</th>
					<th class="text-center" scope="col">Last Fired Time</th>
					<th class="text-center" scope="col">Next Fire Time</th>
					<th class="text-center" scope="col">Job State</th>
					<th class="text-center" scope="col">Action</th>
				</tr>
			</thead>
		<tbody>
			<c:forEach var="job" items="${jobList}">
	    		<tr>
	    			<td><input type="checkbox" name="childboxName" />&nbsp;</td>
	    			<td class="jobName">${job.jobName}</td>
	    			<td class="groupName">${job.groupName}</td>
	    			<td>${job.scheduleTime}</td>
	    			<td>${job.lastFiredTime}</td>
	    			<td>${job.nextFireTime}</td>
	    			<td class="jobState">${job.jobState}</td>
	    			<td><a class="btn btn-primary" href="${path}/job/create?jobName=${job.jobName}&groupName=${job.groupName}" role="button">Edit</a></td>
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
	$('#jobDatalist').DataTable({
		"lengthChange": false,
        "searching": false,
        "autoWidth": false,
        "pageLength": 5,
        "order": [[ 1, "asc" ]]
	});
	
	$("#parentBox").click(function(){
		$('input:checkbox').not(this).prop('checked', this.checked);
	});
	
	$(".processButton").click(function(){
		var thisProcess = $(this).text(); 
		var boxes = $('input[name=childboxName]:checked');
		var jobList = [];
		
		boxes.each(function(){
			var jobName = $(this).closest("tr").find(".jobName").text();  
			var jobGroupname = $(this).closest("tr").find(".groupName").text(); 
			var jobState = $(this).closest("tr").find(".jobState").text(); 
			var SchedulerJobInfo = {
				jobName: jobName,
				jobGroup: jobGroupname
			}
			jobList.push(SchedulerJobInfo);
		});
		
		var AjaxRequestModel = {
	   		jobProcess: thisProcess,
	        jobList: jobList
	    }

		$.ajax({
			url: '${path}/job/ajax',
			type: 'POST',
			data: JSON.stringify(AjaxRequestModel),
	        contentType: 'application/json',
	        processData: false,
	        success: function(responseData, status, xhr) {
	        	
	        	if (status == 'success') {
	        		console.log("success");
	        		location.reload(true);
	        	}
	        },
	        error: function(request, status, error) {
	            console.log("status: ", status);
	            console.log("error: ", error);
	        }
		});
	});
});
</script>
</body>
</html>