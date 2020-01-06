<!DOCTYPE HTML>
<%@ include file="/taglibs.jsp"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html lang="en">
<head>
	<link rel="stylesheet" type="text/css" href="${path}/static/plugins/bootstrap/bootstrap-4.3.1-dist/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${path}/static/plugins/css/bootstrap-datetimepicker.min.css">
	<link rel="stylesheet" type="text/css" href="${path}/static/plugins/bootstrap/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${path}/static/plugins/css/tempusdominus-bootstrap-4.min.css">
    
    <title>Notification History Detail</title>
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
        	<h3>Notification History Detail</h3><br />
        	<div class="container">
        		<div class="row">
        			<div class="col-sm">
		        		<div class="card" style="width: 18rem;">
		  					<div class="card-body">
							    <h6 class="card-title"><b>Notification Type</b></h6>
							    <p class="card-text">${notificationsHistory.nhNcNotiftype}</p>
		  					</div>
						</div>
						<br />
						<div class="card" style="width: 18rem;">
		  					<div class="card-body">
							    <h6 class="card-title"><b>Notification Sender</b></h6>
							    <p class="card-text">${notificationsHistory.nhSender}</p>
		  					</div>
						</div>
						<br />
						<div class="card" style="width: 18rem;">
		  					<div class="card-body">
							    <h6 class="card-title"><b>Notification Receiver</b></h6>
							    <p class="card-text">${notificationsHistory.nhReceiver}</p>
		  					</div>
						</div>
						<br />
					</div>
					<div class="col-sm">
						<div class="card" style="width: 18rem;">
		  					<div class="card-body">
							    <h6 class="card-title"><b>Notification Subject</b></h6>
							    <p class="card-text">${notificationsHistory.nhSubject}</p>
		  					</div>
						</div>
						<br />
						<div class="card" style="width: 18rem;">
		  					<div class="card-body">
							    <h6 class="card-title"><b>Notification Status</b></h6>
							    <c:choose>
  									<c:when test="${notificationsHistory.nhStatus}">
  										 <p class="card-text">OK</p>
  									</c:when>
  									<c:otherwise>
  										<p class="card-text">-</p>
  									</c:otherwise>
								</c:choose>
		  					</div>
						</div>
						<br />
						<div class="card" style="width: 18rem;">
		  					<div class="card-body">
							    <h6 class="card-title"><b>Notification Send Date</b></h6>
							    <p class="card-text">${notificationsHistory.nhSendDate}</p>
		  					</div>
						</div>
						<br />
					</div>
					<div class="col-sm">
						<div class="card" style="width: 18rem;">
		  					<div class="card-body">
							    <h6 class="card-title"><b>Notification Content</b></h6>
							    <p class="card-text">${notificationsHistory.nhContent}</p>
		  					</div>
						</div>
						<br />
						<div class="container">
							<a class="btn btn-primary" href="${path}/notification/history" role="button">
								<i class="fa fa-chevron-right" aria-hidden="true"></i>
							</a>
						</div>
					</div>
				</div>
        	</div>
		</div>
	</div>
<script type="text/javascript">
$(document).ready(function () {
	
});
</script> 
</body>
</html>
        