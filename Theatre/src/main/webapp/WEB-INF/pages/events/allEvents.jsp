<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en-US" xmlns="http://www.w3.org/1999/xhtml" dir="ltr">
<head>
<title>Movie Theater</title>
<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="resources/css/style.css" type="text/css"
	media="all" />
<!--[if IE 6]>
		<link rel="stylesheet" href="css/ie6.css" type="text/css" media="all" />
	<![endif]-->
<script type="text/javascript" src="resources/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="resources/js/jquery-func.js"></script>
</head>
<body>
	<div id="shell">
		<jsp:include page="header.jsp" />
		<div id="main">
			<div id="content">
				<div class="box">
					<div class="head">
						<h2>ALL EVENTS</h2>
					</div>
					<div class="movie">
						<div class="movie-image">
							<span class="play"><span class="name">X-MAN</span></span> <a
								href="#"><img src="resources/css/images/movie1.jpg"
								alt="movie" /></a>
						</div>
					</div>
					<div class="cl">&nbsp;</div>
				</div>
			</div>
		</div>
		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>