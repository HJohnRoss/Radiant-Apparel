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
    <c:choose>
        <c:when test="${cart != []}">            
            <c:forEach var="oneObject" items="${cart}">
                <c:forEach var="oneKey" items="${oneObject.entrySet()}">
                        <h1><c:out value="${oneKey.getKey().name}"></c:out></h1>
                        <img src="${oneKey.getKey().image}"/>
                        <h4>Price: <c:out value="${currencyFormat.format(oneKey.getKey().price.unitAmount * oneKey.getValue())}"></c:out></h4>
                        <form action="/cart/update/${oneKey.getKey().id}" method="post">
                            <label for="quantity">Quantity:</label>
                            <select name="quantity">
                                <option value="${oneKey.getValue()}"><c:out value="${oneKey.getValue()}"></c:out></option>
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                                <option value="6">6</option>
                                <option value="7">7</option>
                                <option value="8">8</option>
                                <option value="9">9</option>
                                <option value="10">10</option>
                            </select>
                            <div class="mt-10 d-flex">
                                <div class="cartBtn">
                                    <button class="productBtn">Update Quantity</button>
                                </div>
                        </form>
                        <form action="/cart/delete/${oneKey.getKey().id}" method="post">
                            <button><img src="/img/delete.png" alt="Delete icons created by Ilham Fitrotul Hayat - Flaticon"></button>
                        </form>
                    </c:forEach>
            </c:forEach>
            <form action="/checkout" method="post">
                <h2>Total: <c:out value="${currencyFormat.format(total)}"></c:out></h2>
                <button>Checkout</button>
            </form>
        </c:when>
        <c:otherwise>
            <p>Cart is empty</p>
        </c:otherwise>
    </c:choose>
</body>
</html>