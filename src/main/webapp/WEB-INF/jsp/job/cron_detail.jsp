<%@ include file="/taglibs.jsp"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<nav>
	<div class="nav nav-tabs" id="nav-tab" role="tablist">
		<a class="nav-item nav-link active" id="nav-Minutes-tab" data-toggle="tab" href="#nav-Minutes" role="tab" aria-controls="nav-Minutes" aria-selected="true">Minutes</a>
		<a class="nav-item nav-link" id="nav-Hourly-tab" data-toggle="tab" href="#nav-Hourly" role="tab" aria-controls="nav-Hourly" aria-selected="false">Hourly</a>
		<a class="nav-item nav-link" id="nav-Daily-tab" data-toggle="tab" href="#nav-Daily" role="tab" aria-controls="nav-Daily" aria-selected="false">Daily</a>
		<a class="nav-item nav-link" id="nav-Weekly-tab" data-toggle="tab" href="#nav-Weekly" role="tab" aria-controls="nav-Weekly" aria-selected="false">Weekly</a>
		<a class="nav-item nav-link" id="nav-Monthly-tab" data-toggle="tab" href="#nav-Monthly" role="tab" aria-controls="nav-Monthly" aria-selected="false">Monthly</a>
		<a class="nav-item nav-link" id="nav-Yearly-tab" data-toggle="tab" href="#nav-Yearly" role="tab" aria-controls="nav-Yearly" aria-selected="false">Yearly</a>
  	</div>
</nav>
<div class="tab-content" id="nav-tabContent">
	<div class="tab-pane fade show active" id="nav-Minutes" role="tabpanel" aria-labelledby="nav-Minutes-tab">
		<br/>
		<div class="form-group row align-items-center">
			<form:label class="col-sm-2 col-form-label" path="cronProperties.minutes">Every</form:label>
			<div class="col-sm-2">
				<form:input type="number" min="0" class="form-control" path="cronProperties.minutes" placeholder="" />
			</div>
			<label class="col-sm-2 col-form-label">minutes.</label>
		</div>
	</div>
	<div class="tab-pane fade" id="nav-Hourly" role="tabpanel" aria-labelledby="nav-Hourly-tab">
		<br/>
		<div class="form-group row align-items-center">
			<form:label class="col-sm-2 col-form-label" path="cronProperties.hourly">Every</form:label>
			<div class="col-sm-2">
				<form:input type="number" min="0" class="form-control" path="cronProperties.hourly" placeholder="" />
			</div>
			<label class="col-sm-2 col-form-label">hour(s).</label>
		</div>
	</div>
	<div class="tab-pane fade" id="nav-Daily" role="tabpanel" aria-labelledby="nav-Daily-tab">
		<br/>
		<div class="form-group row align-items-center">
		 	<div class="col-sm-4">
				<form:select path="cronProperties.everyDay" class="custom-select">
					<form:option value="true">Everyday</form:option>
					<form:option value="false">Monday - Friday</form:option>
				</form:select>
			</div>
		</div>
		<div class="form-group row align-items-center">
			<form:label class="col-sm-2 col-form-label" path="cronProperties.dailyHour">Starts at</form:label>
			<div class="col-sm-4">
				<div class="input-group date" id="datetimepicker3" data-target-input="nearest">
	                <form:input type="text" class="form-control datetimepicker-input" path="cronProperties.dailyHour" data-target="#datetimepicker3" />
	                <div class="input-group-append" data-target="#datetimepicker3" data-toggle="datetimepicker">
	                	<div class="input-group-text"><i class="fa fa-clock-o"></i></div>
	                </div>
            	</div>
			</div>
  		</div>
	</div>
	<div class="tab-pane fade" id="nav-Weekly" role="tabpanel" aria-labelledby="nav-Weekly-tab">
		<br/>
		<div class="form-group row align-items-center">
			<div class="col-sm-4">
				<form:select path="cronProperties.weeklyDay" class="custom-select">
					<c:forEach var="day" items="${days}">
						<form:option value="${day.value}">${day.value}</form:option>
					</c:forEach>
				</form:select>
			</div>
		</div>
		<div class="form-group row align-items-center">
			<form:label class="col-sm-2 col-form-label" path="cronProperties.weeklyHour">Starts at</form:label>
			<div class="col-sm-4">
				<div class="input-group date" id="datetimepicker4" data-target-input="nearest">
	                <form:input type="text" class="form-control datetimepicker-input" path="cronProperties.weeklyHour" data-target="#datetimepicker4" />
	                <div class="input-group-append" data-target="#datetimepicker4" data-toggle="datetimepicker">
	                	<div class="input-group-text"><i class="fa fa-clock-o"></i></div>
	                </div>
            	</div>
			</div>
  		</div>
	</div>
	<div class="tab-pane fade" id="nav-Monthly" role="tabpanel" aria-labelledby="nav-Monthly-tab">
		<br/>
		<div class="form-group row align-items-center">
			<form:label class="col-sm-2 col-form-label" path="cronProperties.monthlyDay">Day</form:label>
			<div class="col-sm-2">
				<form:input type="number" min="1" max="31" class="form-control" path="cronProperties.monthlyDay" placeholder="" />
			</div>
			<label class="col-sm-3 col-form-label">of every month.</label>
		</div>
		<div class="form-group row align-items-center">
			<form:label class="col-sm-2 col-form-label" path="cronProperties.monthlyHour">Starts at</form:label>
			<div class="col-sm-4">
				<div class="input-group date" id="datetimepicker5" data-target-input="nearest">
	                <form:input type="text" class="form-control datetimepicker-input" path="cronProperties.monthlyHour" data-target="#datetimepicker5" />
	                <div class="input-group-append" data-target="#datetimepicker5" data-toggle="datetimepicker">
	                	<div class="input-group-text"><i class="fa fa-clock-o"></i></div>
	                </div>
            	</div>
			</div>
  		</div>
	</div>
	<div class="tab-pane fade" id="nav-Yearly" role="tabpanel" aria-labelledby="nav-Yearly-tab">
		<br/>	
		<div class="form-group row align-items-center">
			<div class="col-sm-2">
				<form:input type="number" min="0" max="31" class="form-control" path="cronProperties.yearlyDate" placeholder="" />
			</div>
			<div class="col-sm-4">
				<form:select path="cronProperties.yearlyMonth" class="custom-select">
					<c:forEach var="month" items="${months}">
						<form:option value="${month.value}">${month.value}</form:option>
					</c:forEach>
				</form:select>
			</div>
		</div>
		<div class="form-group row align-items-center">
			<form:label class="col-sm-2 col-form-label" path="cronProperties.yearlyHour">Starts at</form:label>
			<div class="col-sm-4">
				<div class="input-group date" id="datetimepicker6" data-target-input="nearest">
	                <form:input type="text" class="form-control datetimepicker-input" path="cronProperties.yearlyHour" data-target="#datetimepicker6" />
	                <div class="input-group-append" data-target="#datetimepicker6" data-toggle="datetimepicker">
	                	<div class="input-group-text"><i class="fa fa-clock-o"></i></div>
	                </div>
            	</div>
			</div>
  		</div>
	</div>
</div>