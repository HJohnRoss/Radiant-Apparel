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
    <title>Admin Types</title>
</head>
<body>
    <h2>Add a Product to <c:out value="${oneCategory.name}"></c:out></h2>
    <a href="/admin">go back</a>
    <form action="/product/add/${oneCategory.id}" method="post">
        <input type="hidden" name="_method" value="put"/>
        <div>
            <label name="productId">Type of Category:</label>
            <select name="productId">
                <c:forEach var="oneProduct" items="${allProducts}">
                        <option value="${oneProduct.id}"><c:out value="${oneProduct.name}"></c:out></option>
                </c:forEach>
            </select>
        </div>
        <button>Add Product</button>
    </form>
</body>
</html>