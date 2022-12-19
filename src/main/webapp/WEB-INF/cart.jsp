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
    <title>Your Cart</title>
</head>
<body>
    <c:forEach var="oneProduct" items="${cart}">
        <img src='<c:out value="${oneProduct.images}"/>' alt="">
        <p><c:out value="${oneProduct.name}"></c:out></p>
        <p><c:out value="${currencyFormat.format(oneProduct.price.unitAmount)}"></c:out></p>
    </c:forEach>
    <form action="/checkout" method="post">
        <button>Checkout</button>
    </form>
</body>
</html>