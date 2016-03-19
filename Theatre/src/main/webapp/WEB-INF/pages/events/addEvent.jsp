<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en-US" xmlns="http://www.w3.org/1999/xhtml" dir="ltr">
<head>
<title>Add new Event</title>
<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
<jsp:include page="../essentials/essentials.jsp" />
<jsp:include page="../essentials/dateTimePicker.jsp" />

</head>
<body>
	<div id="shell">
		<jsp:include page="../header.jsp" />
		<div id="main">
			<br />
			<div id="content">
				<div class="box ">
					<form:form method="POST" 
						action="/events/add">

						<div class="row">
							<div class="col-md-2">
								<form:label path="name">Event Name</form:label>
							</div>
							<div class="col-md-7">
								<form:input path="name" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-2">
								<form:label path="basePrice">basePrice</form:label>
							</div>
							<div class="col-md-7">
								<form:input path="basePrice" />
							</div>

						</div>
						<div class="row">
							<div class="col-md-2">
								<form:label path="rating">Rating</form:label>
							</div>
							<div class="col-md-7">
								<form:select path="rating" itemValue="name" items="${ratingOptions}" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-2">
								<form:label path="auditorium">Auditorium</form:label>
							</div>
							<div class="col-md-7">
								<form:select itemValue="id" itemLabel="name" path="auditorium"
									items="${auditoriums}" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-2">
								<form:label path="start">Start</form:label>
							</div>
							<div class="col-md-7">
								<div class="form-group">
									<div class='input-group date' id='datetimepicker1'>
										<form:input path="start" cssClass="form-control" />
										<span class="input-group-addon"> <span
											class="glyphicon glyphicon-calendar"></span>
										</span>
									</div>
								</div>
							</div>
							<script type="text/javascript">
								$(function() {
									$('#datetimepicker1').datetimepicker();
								});
							</script>
						</div>
						<div class="row">
							<div class="col-md-2">
								<form:label path="end">End</form:label>
							</div>
							<div class='col-md-7'>
								<div class="form-group">
									<div class='input-group date' id='datetimepicker2'>
										<form:input path="end" cssClass="form-control" />
										<span class="input-group-addon"> <span
											class="glyphicon glyphicon-calendar"></span>
										</span>
									</div>
								</div>
							</div>
							<script type="text/javascript">
								$(function() {
									$('#datetimepicker2').datetimepicker();
								});
							</script>
						</div>
						<input type="submit" value="Submit" />
					</form:form>

				</div>
			</div>
		</div>
		<jsp:include page="../footer.jsp" />
	</div>
</body>
</html>