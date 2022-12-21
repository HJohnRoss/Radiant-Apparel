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
    <link rel="stylesheet" href="/css/library.css">
    <link rel="stylesheet" href="/css/about.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Radiant Apparel</title>
</head>
<body>
    <div class="navextension bgdark d-flex align-items-center">
    </div>
    <nav class="navbar bgdark">
        <div class="navcontentstart bgdark">
            <img src="/img/thirdlogodark.png" alt="Radiance Logo" id="logo">
        </div>
        <div class="navcontentend bgdark">
            <a href="/" class="nav-tag">Home</a>
            <p>|</p>
            <a href="#" class="nav-tag">About</a>
            <p>|</p>
            <a href="/shop" class="nav-tag ">Shop</a>
            <p>|</p>
            <a href="/admin" class="nav-tag">Help</a>
            <c:if test="${loggedIn == true}">
                <p>|</p>
                <a href="/wishlist/${userId}" class="nav-tag">Wishlist</a>
            </c:if>
            <p>|</p>
            <a href="/cart" class="pointer-select"
                style="border: none; background-color: transparent; color: white; font-size:24px"><i
                    class="fa fa-shopping-cart"></i> Cart</a>
        </div>
    </nav>
    <h1>It's Working</h1>
</body>
</html>