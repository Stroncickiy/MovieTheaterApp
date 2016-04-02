<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en-US" xmlns="http://www.w3.org/1999/xhtml" dir="ltr">
<head>
    <title>Movie Theater</title>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8"/>
    <jsp:include page="essentials/essentials.jsp"/>
    <jsp:include page="essentials/dateTimePicker.jsp"/>
</head>
<body>
<div id="shell">
    <jsp:include page="header.jsp"/>
    <!-- Main -->
    <div id="main">
        <form:form method="post" action="register" modelAttribute="newUser">
            <form:label path="firstName">First Name</form:label>
            <form:input path="firstName"></form:input>
            <form:label path="lastName">Last Name</form:label>
            <form:input path="lastName"></form:input>
            <form:label path="email">Email</form:label>
            <form:input path="email"></form:input>
            <form:label path="password">Password</form:label>
            <form:input path="password"></form:input>
            <form:label path="roles">Roles</form:label>
            <form:select path="roles" multiple="multiple">
                <form:options items="${availableRoles}"></form:options>
            </form:select>
            <form:label path="birthDate">Birthday</form:label>
            <div class="col-md-7">
                <div class="form-group">
                    <div class='input-group date' id='datepicker'>
                        <form:input path="birthDate" cssClass="form-control"></form:input>
                        <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                        </span>
                    </div>
                </div>
            </div>
            <script type="text/javascript">
                $(function () {
                    $('#datepicker').datetimepicker({
                        format: 'DD/MM/YYYY'
                    });
                });
            </script>
            <form:button type="submit">Submit</form:button>
        </form:form>

    </div>
    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>