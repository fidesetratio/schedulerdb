<!DOCTYPE HTML>
<%@ include file="/taglibs.jsp"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<nav>
	<div class="nav nav-tabs" id="nav-tab" role="tablist">
		<a class="nav-item nav-link active" id="nav-minutes-tab" data-toggle="tab" href="#nav-minutes" role="tab" aria-controls="nav-minutes" aria-selected="true">Minutes</a>
		<a class="nav-item nav-link" id="nav-hourly-tab" data-toggle="tab" href="#nav-hourly" role="tab" aria-controls="nav-hourly" aria-selected="false">Hourly</a>
		<a class="nav-item nav-link" id="nav-daily-tab" data-toggle="tab" href="#nav-daily" role="tab" aria-controls="nav-daily" aria-selected="false">Daily</a>
		<a class="nav-item nav-link" id="nav-weekly-tab" data-toggle="tab" href="#nav-weekly" role="tab" aria-controls="nav-weekly" aria-selected="false">Weekly</a>
		<a class="nav-item nav-link" id="nav-monthly-tab" data-toggle="tab" href="#nav-monthly" role="tab" aria-controls="nav-monthly" aria-selected="false">Monthly</a>
		<a class="nav-item nav-link" id="nav-yearly-tab" data-toggle="tab" href="#nav-yearly" role="tab" aria-controls="nav-yearly" aria-selected="false">Yearly</a>
  	</div>
</nav>
<div class="tab-content" id="nav-tabContent">
	<div class="tab-pane fade show active" id="nav-minutes" role="tabpanel" aria-labelledby="nav-minutes-tab">
		<br/>
		<div class="form-group row align-items-center">
			<form:label class="col-sm-2 col-form-label" path="cronProperties.everyMinute">Every</form:label>
			<div class="col-sm-2">
				<form:input type="number" min="0" class="form-control" path="cronProperties.everyMinute" placeholder="" />
			</div>
			<label class="col-sm-2 col-form-label">minutes.</label>
		</div>
	</div>
	<div class="tab-pane fade" id="nav-hourly" role="tabpanel" aria-labelledby="nav-hourly-tab"></div>
	<div class="tab-pane fade" id="nav-daily" role="tabpanel" aria-labelledby="nav-daily-tab"></div>
	<div class="tab-pane fade" id="nav-weekly" role="tabpanel" aria-labelledby="nav-weekly-tab"></div>
	<div class="tab-pane fade" id="nav-monthly" role="tabpanel" aria-labelledby="nav-monthly-tab"></div>
	<div class="tab-pane fade" id="nav-yearly" role="tabpanel" aria-labelledby="nav-yearly-tab"></div>
</div>