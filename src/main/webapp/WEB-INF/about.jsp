<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- c:out ; c:forEach etc. -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Formatting (dates) -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en" class="fullheight">

<head">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/library.css">
    <link rel="stylesheet" href="/css/about.css">
    <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Radiant Apparel</title>
</head>

<body class="fullheight">
    <div class = "navextension bgdark d-flex align-items-center">
    </div>
    <nav class="navbar bgdark">
        <div class = "navcontentstart bgdark">
            <img src="/img/thirdlogodark.png" alt="Radiance Logo" id = "logo">
        </div>
        <div class = "navcontentend bgdark">
            <div class="d-flex align-items-center justify-content-between">
                <a href="/" class = "nav-tag pointer-select">Home</a>
                <p class = "nav-space">|</p>
                <a href="/about" class = "nav-tag">About</a>
                <p class = "nav-space">|</p>
                <a href="/shop" class = "nav-tag">Shop</a>
                <c:if test="${userId != null}">
                    <p class = "nav-space">|</p>
                    <a href="/wishlist/${userId}" class="nav-tag">Wishlist</a>
                    <c:if test="${userId == 1}">
                        <p class = "nav-space">|</p>
                        <a href="/admin" class = "nav-tag">Admin</a>
                    </c:if>
                    <p class="nav-space">|</p>
                    <a href="/logout" class="nav-tag">Logout</a>
                </c:if>
                <c:if test="${userId == null}">
                    <p class="nav-space">|</p>
                    <a href="/login" class="nav-tag">Login/Register</a>
                </c:if>
            </div>
            <div class = "navcart">
                <form action="/cart">
                    <button type = "submit" style="cursor:pointer; border: none; background-color: transparent; color: rgb(198,241,241); font-size:24px"><i class="fa fa-shopping-cart"></i> Cart</button>
                </form>
            </div>
        </div>
    </nav>
    <div class="gradient  text-center">
        <h1 class="text-light about-margin">This Site was made to demonstrate the skills of these three developers</h1>
        <div class="d-flex justify-content-around about-margin">
            <div>
                <h1 class="about-text">Ben Ackerman</h1>
                <h3 class="about-text">Front-End Dev</h3>
            </div>
            <div>
                <h1 class="about-text">John Ross</h1>
                <h3 class="about-text">Back-End Dev</h3>
            </div>
            <div class="ciaran">
                <h1 class="about-text">Ciaran Voros</h1>
                <h3 class="about-text">Full-Stack Dev</h3>
                <h3 class="about-text"><a class="about-text" href="https://github.com/CiaranVoros">GitHub</a></h3>
                <h3 class="about-text"><a class="about-text" href="https://linkedin.com/in/ciaranvoros">LinkedIn</a></h3>
            </div>
        </div>
            
    </div>
</body>

</html>