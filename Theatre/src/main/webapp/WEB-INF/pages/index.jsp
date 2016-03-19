<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en-US" xmlns="http://www.w3.org/1999/xhtml" dir="ltr">
<head>
<title>Movie Theater</title>
<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
<jsp:include page="essentials/essentials.jsp" />
</head>
<body>
	<div id="shell">
		<jsp:include page="header.jsp" />
		<!-- Main -->
		<div id="main">
			<!-- Content -->
			<div id="content">
				<div class="box">
					<div class="head">
						<h2>SOON IN THEATER</h2>
						<p class="text-right">
							<a href="#">See all</a>
						</p>
					</div>
					<div class="row">
						<c:forEach items="${nextEvents}" var="event">
							<div class="col-md-4">
								<div class="movie">
									<div class="movie-image">
										<span class="play"><span class="name">${event.name}</span></span>
										<a href="#"><img src="resources/css/images/movie1.jpg"
											alt="movie" /></a>
									</div>
								</div>
							</div>
						</c:forEach>

					</div>
				</div>
			</div>
		</div>
		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>