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
    <h2>Delete Types</h2>
    <a href="/admin">go back</a>
    <ul>
        <c:forEach var="oneType" items="${allTypes}">
            <li>
                <p><c:out value="${oneType.name}"></c:out></p>
                <form action="/type/delete/${oneType.id}" method="post">
                    <input type="hidden" name="_method" value="delete"/>
                    <button class="btn btn-danger">Delete</button>
                </form>
            </li>
        </c:forEach>
    </ul>
</body>
</html>