<!DOCTYPE HTML>
<%@ include file="/taglibs.jsp"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html lang="en">
<head>
	<link rel="stylesheet" type="text/css" href="${path}/static/plugins/bootstrap/bootstrap-4.3.1-dist/css/bootstrap.min.css">
	
	<title>Job List</title>
	<style>
		.table {
		   margin: auto;
		   width: 85% !important; 
		   overflow-y: auto !important;
		}
		.processButtons {
			text-align: right;
			width: 90% !important;
			display: table;
		}
		.processButtonsleft {
		 	display: table-cell;
		}
		.processButtonsright {
			display: table-cell;
		}
	</style>
</head>
<body>
<div>
	<jsp:include page="/static/common/include/menu_bar.jsp" />
	<br />
	<div class="processButtons">
		<div class="processButtonsleft">
			<button type="button" class="btn btn-primary processButton">Pause All</button>
			<button type="button" class="btn btn-info processButton">Resume All</button>
		</div>
		<div>
			<button type="button" class="btn btn-warning processButton">Start Now</button>
			<button type="button" class="btn btn-primary processButton">Pause</button>
			<button type="button" class="btn btn-info processButton">Resume</button>
			<button type="button" class="btn btn-danger processButton">Delete</button>
		</div>
	</div>
	<br />
	<div class="table-responsive">
		<!-- tabel daftar job -->
		<table class="table" id="jobDatalist">
			<thead class="thead-light">
				<tr>
					<th scope="col"><input type="checkbox" id="parentBox" /></th>
					<th scope="col">Job Name</th>
					<th scope="col">Group Name</th>
					<th scope="col">Schedule Time</th>
					<th scope="col">Last Fired Time</th>
					<th scope="col">Next Fire Time</th>
					<th scope="col">Job State</th>
					<th scope="col">Action</th>
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
	    			<td><a class="btn btn-primary" href="${path}/createjob?jobName=${job.jobName}&groupName=${job.groupName}" role="button">Edit</a></td>
	    		</tr>
			</c:forEach>
		</tbody>
		</table>
	</div>
</div>
<script type="text/javascript" src="${path}/static/plugins/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="${path}/static/plugins/bootstrap/bootstrap-4.3.1-dist/js/bootstrap.min.js"></script>

<script type="text/javascript">
$(document).ready(function () {
	$("#parentBox").click(function(){
		$('input:checkbox').not(this).prop('checked', this.checked);
	});
	
	$(".processButton").click(function(){
		var thisProcess = $(this).text(); 
		var boxes = $('input[name=childboxName]:checked');
		var jobList = [];
		console.log(boxes.length);
		
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
		
		console.log("job list : " + jobList);
		
		var AjaxRequestModel = {
	   		jobProcess: thisProcess,
	        jobList: jobList
	    }

		$.ajax({
			type: "POST",
	        url: "${path}/ajax",
	        dataType: 'json',
	        contentType: "application/json; charset=utf-8",
	        data: JSON.stringify(AjaxRequestModel),
	        success: function(responseData, status, xhr) {
	        	location.reload(true);
	        },
	        error: function(request, status, error) {
	            console.log(request.responseText);
	        }
		});
	});
});
</script>
</body>
</html>