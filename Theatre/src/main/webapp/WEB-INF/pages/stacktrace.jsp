<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en-US" xmlns="http://www.w3.org/1999/xhtml" dir="ltr">
<head>
    <title>Error</title>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8"/>
    <jsp:include page="essentials/essentials.jsp"/>
</head>
<body>
<div id="shell">
    <jsp:include page="header.jsp"/>
    <!-- Main -->
    <div id="main">
        <div class="row  bg-success">
            <h4>There are exception occured </h4>
            <br>
            <h4> On page ${url}</h4>
            <br>
            <h4> Full StackTrace</h4>
            <code>${stackTrace}</code>

        </div>

    </div>
    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>