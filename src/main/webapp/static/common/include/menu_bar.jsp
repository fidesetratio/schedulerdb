<div>
	<!-- navbar -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="#"><b>Scheduler</b></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
	
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
			    <li class="nav-item dropdown">
		     		<span class="nav-link dropdown-toggle" id="jobDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		          		Job
		        	</span>
		        	<div class="dropdown-menu" aria-labelledby="navbarDropdown">
		          		<a class="dropdown-item" href="${path}/schedulerdb/joblist">List Job</a>
		          		<a class="dropdown-item" href="${path}/schedulerdb/createjob?jobName=&groupName=">Create Job</a>
		        	</div>
		      	</li>
			</ul>
			<form class="form-inline my-2 my-lg-0">
				<div class="nav-item dropdown mr-sm-2">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			        	Search By
			        </a>
			    	<div class="dropdown-menu" aria-labelledby="navbarDropdown">
			        	<a class="dropdown-item" href="#">Job Group</a>
			          	<a class="dropdown-item" href="#">Job Name</a>
			        </div>
				</div>
			    <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
			    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
		</div>
	</nav>
</div>