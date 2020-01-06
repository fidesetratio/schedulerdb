<!DOCTYPE HTML>
<%@ include file="/taglibs.jsp"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html lang="en">
<head>
	<link rel="stylesheet" type="text/css" href="${path}/static/plugins/bootstrap/bootstrap-4.3.1-dist/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${path}/static/plugins/css/bootstrap-datetimepicker.min.css">
	<link rel="stylesheet" type="text/css" href="${path}/static/plugins/bootstrap/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${path}/static/plugins/css/tempusdominus-bootstrap-4.min.css">
    
    <title>${title} Notification</title>
    <style>
    </style>
</head>
<script type="text/javascript" src="${path}/static/plugins/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="${path}/static/plugins/js/popper.min.js"></script>
<script type="text/javascript" src="${path}/static/plugins/js/moment.min.js"></script>
<script type="text/javascript" src="${path}/static/plugins/bootstrap/bootstrap-4.3.1-dist/js/bootstrap.min.js"></script> 
<script type="text/javascript" src="${path}/static/plugins/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="${path}/static/plugins/js/tempusdominus-bootstrap-4.min.js"></script>
<body>
	<jsp:include page="/static/common/include/menu_bar.jsp" />
	<div>
        <div class="container">
        	<br />
        	<h3>${title}</h3><br />
        	<form:form method="POST" action="${path}/notification/submit" modelAttribute="notificationsConfiguration" autocomplete="off">
	        	<span style = "color:#ff0000;">
	        		<c:if test="${not empty errors}"><b>Error</b>: 
	        			<ul>
				        	<c:forEach var="itemError" items="${errors}">
				        		<li>${itemError}</li>
				        	</c:forEach>
			        	</ul>
	        		</c:if>
	        	</span>
	        	<div class="form-group row" style="display: none;">
		    		<form:label class="col-sm-2 col-form-label" path="ncId">ID</form:label>
				    <div class="col-sm-8">
						<form:input type="text" class="form-control" path="ncId" placeholder="" value="${notificationsConfiguration.ncId}" />
				    </div>
		  		</div>
	        	<div class="form-group row">
		    		<form:label class="col-sm-2 col-form-label" path="ncPlatform">Notification Type</form:label>
				    <div class="col-sm-8">
						<form:select path="ncPlatform" class="custom-select">
						    <c:forEach var="nc" items="${notificationType}">
						        <form:option value="${nc.key}" selected="${ncPlatform == nc.key ? 'selected' : ''}">${nc.value}</form:option>
						    </c:forEach>
						</form:select>
				    </div>
		  		</div>
		  		<div class="form-group row">
		    		<form:label class="col-sm-2 col-form-label" path="ncHost">Host</form:label>
				    <div class="col-sm-8">
						<form:input type="text" class="form-control" path="ncHost" placeholder="" />
				    </div>
		  		</div>
		  		<div class="form-group row">
		    		<form:label class="col-sm-2 col-form-label" path="ncPort">Port</form:label>
				    <div class="col-sm-8">
						<form:input type="text" class="form-control" path="ncPort" placeholder="" />
				    </div>
		  		</div>
		  		<div class="form-group row">
		    		<form:label class="col-sm-2 col-form-label" path="ncUsername">Username</form:label>
				    <div class="col-sm-8">
						<form:input type="text" class="form-control" path="ncUsername" placeholder="" />
				    </div>
		  		</div>
		  		<div class="form-group row">
		    		<form:label class="col-sm-2 col-form-label" path="ncPassword">Password</form:label>
				    <div class="col-sm-8">
						<form:input type="text" class="form-control" path="ncPassword" placeholder="" />
				    </div>
		  		</div>
		  		<div class="form-group row">
		    		<form:label class="col-sm-2 col-form-label" path="ncAuth">Authentication</form:label>
				    <div class="col-sm-8">
						<form:select path="ncAuth" class="custom-select">
							<form:option value="true">Yes</form:option>
							<form:option value="false">No</form:option>
						</form:select>
				    </div>
		  		</div>
  				<div class="form-group row">
		    		<form:label class="col-sm-2 col-form-label" path="ncSender">Sender</form:label>
				    <div class="col-sm-8">
						<form:input type="text" class="form-control" path="ncSender" placeholder="" />
				    </div>
		  		</div>
		  		<div class="form-group row">
		    		<form:label class="col-sm-2 col-form-label" path="ncReceiver">Receiver</form:label>
				    <div class="col-sm-8">
						<form:input type="text" class="form-control" path="ncReceiver" placeholder="" />
				    </div>
		  		</div>
		  		<div class="form-group row">
		    		<form:label class="col-sm-2 col-form-label" path="ncSubject">Subject</form:label>
				    <div class="col-sm-8">
						<form:input type="text" class="form-control" path="ncSubject" placeholder="" />
				    </div>
		  		</div>
		  		<div class="form-group row">
					<form:label class="col-sm-2 col-form-label" path="ncContent">Content</form:label>
					<div class="col-sm-8">
				    	<form:textarea class="form-control" rows="3" path="ncContent"></form:textarea>
				    </div>
				</div>
		  		<div class="form-group row">
				    <div class="col-sm-8">
				    	<button type="submit" class="btn btn-primary" id="submitButton">
							<i class="fa fa-floppy-o"></i> Submit
						</button>
				    	<a class="btn btn-primary" href="${path}/notification" role="button">
							<i class="fa fa-times"></i> Cancel
						</a>
				    </div>
  				</div>
		  	</form:form>
		</div>
	</div>
<script type="text/javascript">
$(document).ready(function () {
	
});
</script> 
</body>
</html>
        