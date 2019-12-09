<!DOCTYPE HTML>
<%@ include file="/taglibs.jsp"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html lang="en">
<head>
	<link rel="stylesheet" type="text/css" href="${path}/static/plugins/bootstrap/bootstrap-4.3.1-dist/css/bootstrap.min.css">
    	
    <title>Create Job</title>
</head>
<body>
	<jsp:include page="/static/common/include/menu_bar.jsp" />
	<div>
        <div class="container">
        	<br />
        	<h3>Create Job</h3><br />
	        <form:form method="POST" action="${path}/submit" modelAttribute="schedulerJobInfo">
	        	<span style = "color:#ff0000;"><c:if test="${not empty errors}"><b>Error</b>: ${errors}<br /><br /></c:if></span>
	        	<div class="form-group row align-items-center">
		    		<form:label class="col-sm-2 col-form-label" path="jobName">Name</form:label>
				    <div class="col-sm-8">
						<form:input type="text" class="form-control" path="jobName" placeholder="" />
				    </div>
		  		</div>
		  		<div class="form-group row">
		    		<form:label class="col-sm-2 col-form-label" path="jobGroup">Group Name</form:label>
				    <div class="col-sm-8">
						<form:input type="text" class="form-control" path="jobGroup" placeholder="" />
				    </div>
		  		</div>
		  		<div class="form-group row">
		    		<form:label class="col-sm-2 col-form-label" path="jobClass">Class</form:label>
				    <div class="col-sm-8">
						<form:input type="text" class="form-control" path="jobClass" placeholder="" />
				    </div>
		  		</div>
		  		<div class="form-group row">
		    		<form:label class="col-sm-2 col-form-label" path="url">URL</form:label>
				    <div class="col-sm-8">
						<form:input type="text" class="form-control" path="url" placeholder="" />
				    </div>
		  		</div>
		  		<div class="form-group row">
    				<div class="col-sm-2">
						<form:label class="col-form-label" path="cronJob">Cron Job</form:label>
					</div>
    				<div class="col-sm-8">
      					<div class="form-check">
				        	<form:checkbox class="form-check-input" path="cronJob" value="true" id="cronJobid" />
      					</div>
    				</div>
  				</div>
  				<div class="form-group row">
		    		<form:label class="col-sm-2 col-form-label" path="cronExpression">Cron Expression</form:label>
				    <div class="col-sm-8">
						<form:input type="text" class="form-control" path="cronExpression" disabled="${schedulerJobInfo.cronJob ? 'false' : 'true'}" placeholder="" id="cronExpressionid" />
				    </div>
		  		</div>
		  		<div class="form-group row">
		    		<form:label class="col-sm-2 col-form-label" path="repeatTime">Repeat Time</form:label>
				    <div class="col-sm-8">
						<form:input type="text" class="form-control" path="repeatTime" disabled="${schedulerJobInfo.cronJob ? 'true' : 'false'}" placeholder="" id="repeatTimeid" />
				    </div>
		  		</div>
		  		<div class="form-group row">
		    		<form:label class="col-sm-2 col-form-label" path="httpMethod">Http Method</form:label>
				    <div class="col-sm-8">
						<form:input type="text" class="form-control" path="httpMethod" placeholder="" />
						<form:errors path="httpMethod" />
				    </div>
		  		</div>
		  		<div class="form-group row">
				    <div class="col-sm-8">
				    	<button type="submit" class="btn btn-primary">Submit</button>
				    </div>
  				</div>
	        </form:form>
		</div>
	</div>   

<script type="text/javascript" src="${path}/static/plugins/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="${path}/static/plugins/bootstrap/bootstrap-4.3.1-dist/js/bootstrap.min.js"></script> 
<script type="text/javascript">
	$("#cronJobid").click(function(){
		if($(this).is(':checked')) {
			$('#cronExpressionid').prop('disabled', false);
			$('#cronExpressionid').val("");
			$('#repeatTimeid').prop('disabled', true);
			$('#repeatTimeid').val("");
		} else {
			$('#cronExpressionid').prop('disabled', true);
			$('#cronExpressionid').val("");
			$('#repeatTimeid').prop('disabled', false);
			$('#repeatTimeid').val("");
		}
	});

</script>    
</body>
</html>