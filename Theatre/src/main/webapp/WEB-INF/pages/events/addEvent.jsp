<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
					<form method="post" action="/events/add">
						<div class="row">
							<div class="col-md-2">
								<label>Event Name</label>
							</div>
							<div class="col-md-7">
								<input name="name" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-2">
								<label>basePrice</label>
							</div>
							<div class="col-md-7">
								<input name="basePrice" />
							</div>

						</div>
						<div class="row">
							<div class="col-md-2">
								<label>Rating</label>
							</div>
							<div class="col-md-7">
								<select name="rating">
									<c:forEach var="ratingItem" items="${ratingOptions}">
										<option value="${ratingItem}">${ratingItem}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col-md-2">
								<label>Auditorium</label>
							</div>
							<div class="col-md-7">
								<select name="auditorium">
									<c:forEach var="auditoriumItem" items="${auditoriums}">
										<option value="${auditoriumItem.id}">${auditoriumItem.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col-md-2">
								<label>Start</label>
							</div>
							<div class="col-md-7">
								<div class="form-group">
									<div class='input-group date' id='datetimepicker1'>
										<input name="start" class="form-control" /> <span
											class="input-group-addon"> <span
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
								<label>End</label>
							</div>
							<div class='col-md-7'>
								<div class="form-group">
									<div class='input-group date' id='datetimepicker2'>
										<input name="end" class="form-control" /> <span
											class="input-group-addon"> <span
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
					</form>

				</div>
			</div>
		</div>
		<jsp:include page="../footer.jsp" />
	</div>
</body>
</html>