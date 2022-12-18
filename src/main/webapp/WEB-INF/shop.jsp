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
    <title>Radiant Apparel</title>
    <link rel="stylesheet" href="/css/library.css">
    <!-- Shop bottom so takes prio -->
    <link rel="stylesheet" href="/css/shop.css">
</head>
<body>
    <nav class="d-flex justify-content-between">
        <p><img src="#" alt="#"></p>
        <div>

            <a href="/home">home</a> |
            <a href="#">About</a> |
            <a href="/shop">Shop</a> |
            <a href="#">Help</a> |
        </div>
    </nav>
    
    <div class="d-flex flex-column justify-content-center align-items-center">
        <!-- Border decor around -->
        <h1 class="shopHeading">Radiant Apparel</h1>
        <div id="headingLine"></div>
        <div>
            <form action="#">
                <input type="text" placeholder="Search..." name="search">
                <button type="submit">Submit</button>
            </form>
        </div>
        <div>
            <!-- For Each -->
            <div class="d-flex">
            <c:forEach var="type" items="${allTypes}">
                <div class="dropdown">
                    <p class="">
                        <c:out value="${type.name}"></c:out>&nbsp;<c:if test="${allTypes.indexOf(type) != allTypes.size()- 1}">|</c:if>&nbsp;
                    </p>
                    <div class="dropdown-content">
                        <c:forEach var="category" items="${type.categories}">
                            <a href="/category/${category.id}"><c:out value="${category.name}"></c:out></a>
                        </c:forEach>
                    </div>
                </div>
                
            </c:forEach>
            </div>
        </div>
    </div>



    <!-- <c:forEach var="product" items="${allProducts}">
        <img src="<c:out value="${product.images}"></c:out>" alt="">
        <p><c:out value="${product.name}"></c:out></p>
    </c:forEach> -->
</body>
</html>