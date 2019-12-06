<!DOCTYPE HTML>
<%@ include file="/taglibs.jsp"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html lang="en">
<head>
	<link rel="stylesheet" type="text/css" href="${path}/static/plugins/bootstrap/bootstrap-4.3.1-dist/css/bootstrap.min.css">
	
	<title>Job List</title>
</head>
<body>
<div>
	<jsp:include page="/static/common/include/menu_bar.jsp" />
	<div>
		<button type="button" class="btn btn-primary processButton">Pause</button>
		<button type="button" class="btn btn-info processButton">Resume</button>
	</div>
	<div>
		<!-- tabel daftar job -->
		<table class="table" id="jobDatalist">
			<thead class="thead-light">
				<tr>
					<th scope="col"><input type="checkbox" id="parentBox" />&nbsp;</th>
					<th scope="col">Job Name</th>
					<th scope="col">Group Name</th>
					<th scope="col">Schedule Time</th>
					<th scope="col">Last Fired Time</th>
					<th scope="col">Next Fire Time</th>
					<th scope="col">Job State</th>
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
			var jobNamee = $(this).closest("tr").find(".jobName").text();  
			var jobGroupname = $(this).closest("tr").find(".groupName").text(); 
			var jobState = $(this).closest("tr").find(".jobState").text(); 
			var SchedulerJob = {
				jobName: jobNamee,
				groupName: jobGroupname,
				jobState: jobState
			}
			jobList.push(SchedulerJob);
		});
		
		console.log("job list : " + jobList);
		
		var AjaxRequestModel = {
	   		jobProcess: thisProcess,
	        jobList: jobList
	    }

		/* if (jobList.length > 0) {
			$.ajax({
				type: "POST",
		        url: "${path}/ajax",
		        dataType: 'json',
		        contentType: "application/json; charset=utf-8",
		        data: JSON.stringify(AjaxRequestModel),
		        success: function(responseData, status, xhr) {
		        	if (responseData == 'success') {
		        		window.location.reload();
		        	}
		        },
		        error: function(request, status, error) {
		            console.log(request.responseText);
		        }
			});
		}  */
	});
});
</script>
</body>
</html>