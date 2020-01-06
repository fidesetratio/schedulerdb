<div>
	<!-- navbar -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="${path}/schedulerdb/job"><b>Scheduler</b></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
	
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item dropdown">
		     		<span class="nav-link dropdown-toggle" id="groupDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		          		Group
		        	</span>
		        	<div class="dropdown-menu" aria-labelledby="navbarDropdown">
		          		<a class="dropdown-item" href="${path}/schedulerdb/group">Group List</a>
		        	</div>
		      	</li>
			    <li class="nav-item dropdown">
		     		<span class="nav-link dropdown-toggle" id="jobDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		          		Job
		        	</span>
		        	<div class="dropdown-menu" aria-labelledby="navbarDropdown">
		          		<a class="dropdown-item" href="${path}/schedulerdb/job/create?jobName=&groupName=">Create Job</a>
		          		<a class="dropdown-item" href="${path}/schedulerdb/job/history">History</a>
		          		<a class="dropdown-item" href="${path}/schedulerdb/job">Job List</a>
		        	</div>
		      	</li>
		      	<li class="nav-item dropdown">
		     		<span class="nav-link dropdown-toggle" id="notificationDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		          		Notification
		        	</span>
		        	<div class="dropdown-menu" aria-labelledby="navbarDropdown">
		        		<a class="dropdown-item" href="${path}/schedulerdb/notification/history">History</a>
		        		<a class="dropdown-item" href="${path}/schedulerdb/notification">List</a>
		        	</div>
		      	</li>
			</ul>
		</div>
	</nav>
</div>