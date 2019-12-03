<!DOCTYPE HTML>
<%@ include file="/taglibs.jsp"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html lang="en">
<head>
	<title>Job List</title>
</head>
<body>
	<table class="table table-striped">
		<tr>
			<th>Job Name</th>
			<th>Group Name</th>
			<th>Schedule Time</th>
			<th>Last Fired Time</th>
			<th>Next Fire Time</th>
			<th>Job State</th>
		</tr>
	<tbody>
		<c:forEach var="job" items="${jobList}">
    		<tr>
    			<td>${job.jobName}</td>
    			<td>${job.groupName}</td>
    			<td>${job.scheduleTime}</td>
    			<td>${job.lastFiredTime}</td>
    			<td>${job.nextFireTime}</td>
    			<td>${job.jobState}</td>
    		</tr>
		</c:forEach>
	</tbody>
	</table>
</body>
</html>