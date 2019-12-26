<!DOCTYPE HTML>
<%@ include file="/taglibs.jsp"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html lang="en">
<head>
	<link rel="stylesheet" type="text/css" href="${path}/static/plugins/bootstrap/bootstrap-4.3.1-dist/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${path}/static/plugins/css/bootstrap-datetimepicker.min.css">
	<link rel="stylesheet" type="text/css" href="${path}/static/plugins/bootstrap/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${path}/static/plugins/css/tempusdominus-bootstrap-4.min.css">
    
    <title>${title}</title>
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
	        	<div class="form-group row">
		    		<form:label class="col-sm-2 col-form-label" path="ncHost">Notification Type</form:label>
				    <div class="col-sm-8">
						<form:input type="text" class="form-control" path="ncHost" placeholder="" />
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
        