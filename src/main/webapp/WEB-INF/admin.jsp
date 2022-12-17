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
            <form:input path="name" type="text"></form:input>
        </div>
        <form:errors path="images"></form:errors>
        <div>
            <form:label path="images">Url for an image:</form:label>
            <form:input path="images" type="text"></form:input>
        </div>
        <button>Submit</button>
    </form:form>
</body>
</html>