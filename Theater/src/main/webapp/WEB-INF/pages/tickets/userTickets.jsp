<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en-US" xmlns="http://www.w3.org/1999/xhtml" dir="ltr">
<head>
    <title>My Tickets</title>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8"/>
    <jsp:include page="../essentials/essentials.jsp"/>
</head>
<body>
<div id="shell">
    <div class="alert alert-danger">
        <h4>MY BALANCE: </h4>
        <p>${balance}</p>
    </div>
    <jsp:include page="../header.jsp"/>
    <div id="main">
        <div id="content" class="bg-success ">
            <a href="${pageContext.request.contextPath}/tickets/my/get?format=pdf"
               class="btn btn-info">Download as PDF</a>
            <table class="table table-striped ">
                <thead>
                <tr>
                    <th>id</th>
                    <th>customer</th>
                    <th>event</th>
                    <th>seats</th>
                    <th>totalPrice</th>
                    <th>realPrice</th>
                    <th>discountStrategy</th>
                    <th>discountAmount</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${tickets}" var="ticket">
                    <tr>
                        <td>${ticket.id}</td>
                        <td>${ticket.customer.firstName}
                                ${ticket.customer.lastName}</td>
                        <td>${ticket.event.name}</td>
                        <td>${ticket.bookedSeats}</td>
                        <td>${ticket.totalPrice}</td>
                        <td>${ticket.realPrice}</td>
                        <td>${ticket.discountStrategy}</td>
                        <td>${ticket.discountAmount}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <jsp:include page="../footer.jsp"/>
</div>
</body>
</html>