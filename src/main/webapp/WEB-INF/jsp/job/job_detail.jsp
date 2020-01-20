<!-- https://javarevisited.blogspot.com/2014/11/how-to-loop-hashmap-or-hashtable-in-jsp-example.html -->
<!--  https://stackoverflow.com/questions/7202368/spring-mvc-formselect-selected-value -->
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
    	.idDiv {
		 	display: none;
		 }
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
	        <form:form method="POST" action="${path}/job/submit" modelAttribute="schedulerJobInfo" autocomplete="off">
	        	<span style = "color:#ff0000;">
	        		<c:if test="${not empty errors}"><b>Error</b>: 
	        			<ul>
				        	<c:forEach var="itemError" items="${errors}">
				        		<li>${itemError}</li>
				        	</c:forEach>
			        	</ul>
	        		</c:if>
	        	</span>
	        	<div class="form-group row align-items-center idDiv" >
		    		<form:label class="col-sm-2 col-form-label" path="id">ID</form:label>
				    <div class="col-sm-8">
						<form:input type="text" class="form-control" path="id" placeholder="" />
				    </div>
		  		</div>
	        	<div class="form-group row align-items-center">
		    		<form:label class="col-sm-2 col-form-label" path="jobName">Name</form:label>
				    <div class="col-sm-8">
						<form:input type="text" class="form-control" path="jobName" readonly="${(not empty schedulerJobInfo.jobName) && (submitFailed == false) ? 'true' : 'false'}" placeholder="" />
				    </div>
		  		</div>
		  		<div class="form-group row">
		    		<form:label class="col-sm-2 col-form-label" path="jobGroup">Job Name</form:label>
					<div class="col-sm-8">
						<form:select path="jobGroup"  class="custom-select">
						 <form:option value="-" label="-Please Select-"/>
						  <c:forEach var="jobName" items="${jobsName}">
						   			<option value="${jobName.key}" selected="true">${jobName.value}</option>
							</c:forEach>	
						</form:select>				    
						</div>
		  		</div>
		  		
		  		<div class="form-group row">
		    		<form:label class="col-sm-2 col-form-label" path="jobGroup">Notification</form:label>
					<div class="col-sm-8">
						<form:select path="jobGroup"  class="custom-select">
						 <form:option value="-" label="-Please Select-"/>
						  <c:forEach var="jobName" items="${jobsName}">
						   			<option value="${jobName.key}" selected="true">${jobName.value}</option>
							</c:forEach>	
						</form:select>				    
						</div>
		  		</div>
		  		 		<div class="form-group row">
		    		<form:label class="col-sm-2 col-form-label" path="jobGroup">Group Name</form:label>
					<div class="col-sm-8">
						<form:select path="jobGroup" class="custom-select">
						                   
						    <c:forEach var="itemGroup" items="${jobGrouplist}">
						        <form:option value="${itemGroup}" selected="${itemGroup == schedulerJobInfo.jobGroup ? 'selected' : ''}" />
						    </c:forEach>
						</form:select>
				    </div>
		  		</div>
		  		<div class="form-group row">
		    		<form:label class="col-sm-2 col-form-label" path="url">URL</form:label>
				    <div class="col-sm-8">
						<form:input type="text" class="form-control" path="url" placeholder="" />
				    </div>
		  		</div>
		  		<div class="form-group row">
		    		<form:label class="col-sm-2 col-form-label" path="paramName">URL Parameters</form:label>
		    		<div class="col-sm-8 paramForm">
						<c:choose>
							<c:when test="${not empty schedulerJobInfo.paramName}">
								<c:forEach var="counter" begin="${0}" end="${fn:length(schedulerJobInfo.paramName) - 1}">
									<div class="form-row paramRow" style="margin-top: 5px;">
		                        		<div class="col">
							      			<form:input type="text" class="form-control paramName" path="paramName" value="${schedulerJobInfo.paramName[counter]}" />
							    		</div>
							    		<div class="col">
									    	<form:input type="text" class="form-control paramInput" path="paramInput" value="${schedulerJobInfo.paramInput[counter]}" />
									    </div>
									    <div class="col">
									    	<button type="button" class="btn btn-info deleteParam">
									    		<i class="fa fa-trash-o fa-lg" aria-hidden="true"></i>
											</button>
									     	<button type="button" class="btn btn-info addParam">
												<i class="fa fa-plus fa-lg" aria-hidden="true"></i>
											</button>
									    </div>
									</div>
	                    		</c:forEach>
							</c:when>
							<c:when test="${empty schedulerJobInfo.paramName}">
								<div class="form-row paramRow" style="margin-top: 5px;">
						    		<div class="col">
						      			<form:input type="text" class="form-control paramName" path="paramName" />
						    		</div>
								    <div class="col">
								    	<form:input type="text" class="form-control paramInput" path="paramInput" />
								    </div>
								    <div class="col">
								    	<button type="button" class="btn btn-info deleteParam">
								    		<i class="fa fa-trash-o fa-lg" aria-hidden="true"></i>
								    	</button>
								      	<button type="button" class="btn btn-info addParam">
											<i class="fa fa-plus fa-lg" aria-hidden="true"></i>
										</button>
								    </div>
								</div>
	  						</c:when>
	  					</c:choose>
				    </div>
		  		</div>
		  		<%-- <div class="form-group row">
    				<div class="col-sm-2">
						<form:label class="col-form-label" path="cronJob">Cron Job</form:label>
					</div>
    				<div class="col-sm-8">
      					<div class="form-check">
				        	<form:checkbox class="form-check-input" path="cronJob" value="true" id="cronJobid" />
      					</div>
    				</div>
  				</div> --%>
  				<div class="form-group row">
  					<label class="col-sm-2 col-form-label">Schedule Time</label>
				    <div class="col-sm-8">
						<jsp:include page="cron_detail.jsp" />
				    </div>
  				</div>
  				<%-- <div class="form-group row">
		    		<form:label class="col-sm-2 col-form-label" path="cronExpression">Cron Expression</form:label>
				    <div class="col-sm-8">
						<form:input type="text" class="form-control" path="cronExpression" disabled="${schedulerJobInfo.cronJob ? 'false' : 'true'}" placeholder="" id="cronExpressionid" />
				    </div>
		  		</div> --%>
		  		<%-- <div class="form-group row">
		    		<form:label class="col-sm-2 col-form-label" path="repeatInterval">Repeat Interval</form:label>
				    <div class="col-sm-8">
						<form:input type="text" class="form-control" path="repeatInterval" disabled="${schedulerJobInfo.cronJob ? 'true' : 'false'}" placeholder="" id="repeatIntervalid" />
				    </div>
		  		</div> --%>
		  		<div class="form-group row">
		    		<form:label class="col-sm-2 col-form-label" path="httpMethod">Http Method</form:label>
				    <div class="col-sm-8">
						<form:select path="httpMethod" class="custom-select">
						    <c:forEach var="methodItem" items="${httpMethodlist}">
						        <form:option value="${methodItem}" selected="${methodItem == schedulerJobInfo.httpMethod ? 'selected' : ''}" />
						    </c:forEach>
						</form:select>
				    </div>
		  		</div>
		  		<div class="form-group row">
					<form:label class="col-sm-2 col-form-label" path="requestBody">Request Body (JSON)</form:label>
					<div class="col-sm-8">
				    	<form:textarea class="form-control" rows="3" path="requestBody"></form:textarea>
				    </div>
				</div>
				<div class="form-group row" style="display: none;">
		    		<form:label class="col-sm-2 col-form-label" path="cronProperties.cronTab">Schedule Time</form:label>
				    <div class="col-sm-8">
						<form:input type="text" class="form-control" id="cronTabid" path="cronProperties.cronTab" placeholder="" />
				    </div>
		  		</div>
		  		<div class="form-group row">
				    <div class="col-sm-8">
				    	<button type="submit" class="btn btn-primary" id="submitButton">
							<i class="fa fa-floppy-o"></i> Submit
						</button>
				    	<a class="btn btn-primary" href="${path}/job" role="button">
							<i class="fa fa-times"></i> Cancel
						</a>
				    </div>
  				</div>
	        </form:form>
		</div>
	</div>   
<script type="text/javascript">
$(function () {
	$('#datetimepicker3').datetimepicker({
		format:'HH:mm'
    });
	$('#datetimepicker4').datetimepicker({
		format:'HH:mm'
    });
	$('#datetimepicker5').datetimepicker({
		format:'HH:mm'
    });
	$('#datetimepicker6').datetimepicker({
		format:'HH:mm'
    });
});

$(document).ready(function () {
	/* $("#cronJobid").click(function(){
		if($(this).is(':checked')) {
			$('#cronExpressionid').prop('disabled', false);
			$('#cronExpressionid').val("");
			$('#repeatIntervalid').prop('disabled', true);
			$('#repeatIntervalid').val("");
		} else {
			$('#cronExpressionid').prop('disabled', true);
			$('#cronExpressionid').val("");
			$('#repeatIntervalid').prop('disabled', false);
			$('#repeatIntervalid').val("");
		}
	}); */
	
	$(document).on('click', '.addParam', function(e) {
		$(this).closest(".paramRow").clone().find("input:text").val("").end().insertAfter($(this).closest(".paramRow"));
	});
	
	$(document).on('click', '.deleteParam', function(e) {
		if($('.paramRow').length > 1) {
			$(this).closest(".paramRow").remove();	
		}
	});
	
	$('a[data-toggle="tab"]').click(function(){  
		// mengosongkan inputan
		/* $('#nav-tabContent input').val('');
		$('#nav-tabContent input[type="radio"]').prop('checked', false);  */
		
		$("#cronTabid").val($(this).text());
	});
	
	// mengembalikan data pada form cron saat gagal submit dan edit job
	if ("${schedulerJobInfo.cronProperties.cronTab}" != "") {
		$('.nav-item').removeClass('active');
		$('.tab-pane').removeClass('active');
		var className = "nav-" + "${schedulerJobInfo.cronProperties.cronTab}" + "-tab";
		var classChildname = "nav-" + "${schedulerJobInfo.cronProperties.cronTab}";
		
		$('.nav-item').each(function(i, obj) {
			var id = this.id;
	    	if(id == className) {
	    		$(this).trigger("click");
	    	}
		});
	}
});
</script>    
</body>
</html>