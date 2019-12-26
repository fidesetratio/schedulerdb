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
		.createGroup {
			text-align: center;
			margin-left: 100px !important;
		}
	</style>
</head>
<script type="text/javascript" src="${path}/static/plugins/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="${path}/static/plugins/js/popper.min.js"></script>
<script type="text/javascript" src="${path}/static/plugins/bootstrap/bootstrap-4.3.1-dist/js/bootstrap.min.js"></script> 
<script type="text/javascript" src="${path}/static/plugins/js/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="${path}/static/plugins/js/dataTables.bootstrap4.min.js"></script>
<body>
<div>
	<jsp:include page="/static/common/include/menu_bar.jsp" />
	<br />
	<div class="table-responsive">
		<button type="button" class="btn btn-primary createGroup" data-toggle="modal" data-target="#createGroupmodal">
  			Create Group
		</button>
		<br /><br />
		<table class="table" id="groupDatalist">
			<thead class="thead-light">
				<tr>
					<th scope="col"><input type="checkbox" style="display:none" id="parentBox" /></th>
					<th scope="col">Group Name</th>
					<th scope="col">Total Jobs</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
		<tbody>
			<c:forEach var="group" items="${groupList}">
	    		<tr>
	    			<td><input type="checkbox" class="checkId" style="display:none" name="childboxName" value="${group.groupId}" />&nbsp;</td>
	    			<td class="groupName">${group.groupName}</td>
	    			<td>${group.totalJobs}</td>
	    			<td><button type="button" class="btn btn-primary editButton">Edit</button></td>
	    		</tr>
			</c:forEach>
		</tbody>
		</table>
	</div>
	
	<!-- Modal -->
	<div class="modal fade" id="createGroupmodal" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="exampleModalLabel" aria-hidden="true">
  		<div class="modal-dialog" role="document">
    		<div class="modal-content">
      			<div class="modal-header">
        			<h5 class="modal-title">${title}</h5>
			        <!-- <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			        	<span aria-hidden="true">&times;</span>
			        </button> -->
      			</div>
	      		<div class="modal-body">
	      			<b><label class="col-form-label" id="errorLabel" style="color:#ff0000;"></label></b>
	      			<input type="hidden" class="form-control" id="groupIdinput" value="">
          			<div class="form-group">         				
			            <label class="col-form-label">Group Name</label>
			            <input type="text" class="form-control" id="groupNameinput">
		          	</div>
		          	<div class="form-group">
		          		<label class="col-form-label">Description</label>
		            	<textarea class="form-control" id="descriptionInput"></textarea>
		          	</div>
      			</div>
      			<div class="modal-footer">
        			<button type="button" class="btn btn-secondary closeModal">Close</button>
        			<button type="button" class="btn btn-primary submitForm">Submit</button>
      			</div>
   			</div>
  		</div>
	</div>
</div> 
<script type="text/javascript">
$(document).ready(function () {
	$("#errorLabel").hide();
	
	$(".createGroup").click(function() {
		$('#groupNameinput').prop('disabled', false);
	});
	
	$(".closeModal").click(function() {
		$('#createGroupmodal').modal('hide');
		
		$("#groupIdinput").val("");
		$("#groupNameinput").val("");
		$("#descriptionInput").val("");
		$("#errorLabel").hide();
	});

	$(".submitForm").click(function(e) {
		e.preventDefault();
		var groupId = $("#groupIdinput").val();
		var groupName = $("#groupNameinput").val();
		var description = $("#descriptionInput").val();
		
		var SchedulerGroupInfo = {
			groupId: groupId,
			groupName: groupName,
			description: description
		}

		$.ajax({
			url: '${path}/group/submit',
			type: 'POST',
			data: JSON.stringify(SchedulerGroupInfo),
		    contentType: 'application/json',
		    processData: false,
		    success: function(responseData, status, xhr) {	
				if (xhr.status == 200) {
					if (responseData.data != 'success') {
						$("#errorLabel").show();
						$("#errorLabel").text(responseData.data);
					} else {
						setTimeout(function() {
					        document.location.reload()
					  }, 50);
					}
				}
		    },
		    error: function(request, status, error) {
		    	console.log("status: ", status);
		        console.log("error: ", error);
		    }
		});
	});
	
	$(".editButton").click(function(e){
		var groupId = $(this).closest("tr").find(".checkId").val(); 
		
		$.ajax({
			url: '${path}/group/' + groupId,
			type: 'GET',
			contentType: 'application/json',
		    processData: false,
		    success: function(responseData, status, xhr) {
				if (xhr.status == 200) {
					var groupInfo = $.parseJSON(responseData.data);
					$("#groupIdinput").val(groupInfo.groupId);
					$('#groupNameinput').val(groupInfo.groupName);
					$('#descriptionInput').val(groupInfo.description);
					
					$('#createGroupmodal').modal('show');
					$('#groupNameinput').prop('disabled', true);
				} else {
					setTimeout(function() {
				        document.location.reload()
				  }, 50);
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