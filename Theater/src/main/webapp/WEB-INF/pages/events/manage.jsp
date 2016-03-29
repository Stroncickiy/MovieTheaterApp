<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en-US" xmlns="http://www.w3.org/1999/xhtml" dir="ltr">
<head>
<title>Manage Events</title>
<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
<jsp:include page="../essentials/essentials.jsp" />
</head>
<body>
	<div id="shell">
		<jsp:include page="../header.jsp" />
		<div id="main">
			<br /> <a
				href="${pageContext.request.contextPath}/events/manage/add"
				class="btn btn-info">Add Event</a> <a
				href="${pageContext.request.contextPath}/events/all/get"
				class="btn btn-info">Load Events in Xml</a>
				<br></br>
			<div class="row">
				<form action="${pageContext.request.contextPath}/events/loadFromFile"
					enctype="multipart/form-data" method="post">
					<input type="file" name="fileWithEvents" />
					<button type="submit" class="btn btn-info">Add Events
						From File</button>
				</form>
			</div>

			<br></br>
			<div id="content" class="bg-success row" style="height: 100%">
				<c:forEach items="${allEvents}" var="event">
					<div class="col-md-3">
						<div class="movie-image">
							<span class="play"><span class="name">${event.name}</span></span>
							<a style="z-index: 9999;"
								href="${pageContext.request.contextPath}/event/${event.id}"><img
								src="${pageContext.request.contextPath}/resources/css/images/movie${allEvents.indexOf(event)+1}.jpg"
								alt="movie" /></a>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<jsp:include page="../footer.jsp" />
	</div>
</body>
</html>