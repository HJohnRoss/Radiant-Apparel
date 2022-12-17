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
    <title>Admin</title>
</head>
<body>
    <h2>Add a Product</h2>
    <form:form action="/product/create" method="post" modelAttribute="product">
        <form:errors path="name"></form:errors>
        <div>
            <form:label path="name">Name of product:</form:label>
            <form:input path="name" name="name" type="text"></form:input>
        </div>
        <form:errors path="images"></form:errors>
        <div>
            <form:label path="images">Url for an image:</form:label>
            <form:input path="images" name="images" type="text"></form:input>
        </div>
        <button>Add Product</button>
    </form:form>


    <h1>Add a Price</h1>
    <form:form action="/price/create" method="post" modelAttribute="price">
        <form:errors path="unitAmount"></form:errors>
        <div>
            <form:label path="unitAmount">Price of Product:</form:label>
            <form:input path="unitAmount" name="unitAmount" type="text" placeholder="1.00"></form:input>
        </div>
        <div>
            <form:label path="product">Product:</form:label>
            <form:select path="product" name="product">
                <c:forEach var="oneProduct" items="${allProducts}">
                    <c:if test="${oneProduct.prices[0] == null}">
                        <option value="${oneProduct.id}"><c:out value="${oneProduct.name}"></c:out></option>
                    </c:if>
                </c:forEach>
            </form:select>
        </div>
        <button>Add Price</button>
    </form:form>
</body>
</html>