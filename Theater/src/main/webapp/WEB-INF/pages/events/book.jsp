<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en-US" xmlns="http://www.w3.org/1999/xhtml" dir="ltr">
<head>
<title>Buy Tickets For ${event.name }</title>
<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
<jsp:include page="../essentials/essentials.jsp" />
</head>
<body>
	<div id="shell">
		<jsp:include page="../header.jsp" />
		<div id="main">
			<div id="content" class="bg-success">
				<br />
				<div class="head">
					<h2>${event.name}</h2>
				</div>
				<div class="box">
					<div class="row">
						<div class="col-md-2">Auditorium:</div>
						<span>${event.auditorium.name}</span>
					</div>
					<div class="row">
						<div class="col-md-2">Start:</div>
						<span>${event.start}</span>
					</div>
					<div class="row">
						<div class="col-md-2">Stop:</div>
						<span>${event.end}</span>
					</div>
					<div class="row">
						<div class="col-md-2">Base price:</div>
						<span>${event.basePrice}</span>
					</div>
					<div class="row">
						<div class="col-md-2">Rating:</div>
						<span>${event.rating}</span>
					</div>
					<form
						action="${pageContext.request.contextPath}/ticket/book/${event.id}"
						method="post">
						<div class="row">
							<select multiple="multiple" name="targetSeats">
								<c:forEach items="${event.auditorium.seats}" var="seat">
									<option style="${seat.vip?'color:red;':''}"
										value="${seat.number}">${seat.number}</option>
								</c:forEach>
							</select>
						</div>
						<button type="submit">Buy</button>
					</form>
				</div>
			</div>
		</div>
		<jsp:include page="../footer.jsp" />
	</div>
</body>
</html>