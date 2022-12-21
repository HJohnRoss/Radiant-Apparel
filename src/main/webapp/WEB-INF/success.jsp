<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Success!</title>
</head>
<body>
    <h1>Thank you for shopping at Radiant Apparel</h1>
    <h2>This is a project used for learning purposes!</h2>
    <h3>Not a real store</h3>
    <h1>Your Purchase!</h1>
    <c:forEach var="oneObject" items="${cart}">
        <c:forEach var="oneKey" items="${oneObject.entrySet()}">
            <h1><c:out value="${oneKey.getKey().name}"></c:out></h1>
            <img src="${oneKey.getKey().image}"/>
            <h4>Price: <c:out value="${currencyFormat.format(oneKey.getKey().price.unitAmount * oneKey.getValue())}"></c:out></h4>
        </c:forEach>
    </c:forEach>
    <h2>Total: <c:out value="${currencyFormat.format(total)}"></c:out></h2>
    <a href="/cart/delete">GET RID OF SESSION</a>
    <h1>^ Use this link when making routes to clear session or one like it <br> 
        It is inside Success Controller
    </h1>
</body>
</html>