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
<html lang="en" class="fullheight">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/library.css">
    <link rel="stylesheet" href="/css/success.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Your purchase</title>
</head>
<body class="fullheight">
    <div class="navextension bgdark d-flex align-items-center">
    </div>
    <nav class="navbar bgdark">
        <div class="navcontentstart bgdark">
            <img src="/img/thirdlogodark.png" alt="Radiance Logo" id="logo">
        </div>
        <div class="navcontentend bgdark">
            <div class="d-flex align-items-center justify-content-between">
                <a href="/" class="nav-tag pointer-select">Home</a>
                <p class="nav-space">|</p>
                <a href="/about" class="nav-tag">About</a>
                <p class="nav-space">|</p>
                <a href="/shop" class="nav-tag">Shop</a>
                <c:if test="${userId != null}">
                    <p class="nav-space">|</p>
                    <a href="/wishlist/${userId}" class="nav-tag">Wishlist</a>
                    <c:if test="${userId == 1}">
                        <p class="nav-space">|</p>
                        <a href="/admin" class="nav-tag">Admin</a>
                    </c:if>
                </c:if>
                <c:if test="${userId == null}">
                    <p class="nav-space">|</p>
                    <a href="/login" class="nav-tag">Login/Register</a>
                </c:if>
            </div>
            <div class="navcart">
                <form action="/cart">
                    <button type="submit"
                        style="cursor:pointer; border: none; background-color: transparent; color: rgb(198,241,241); font-size:24px"><i
                            class="fa fa-shopping-cart"></i> Cart</button>
                </form>
            </div>
        </div>
    </nav>
    <div class="gradient fullheight">
        <div class="d-flex pt-5 justify-content-around">
            <div>
                <h1 class="text-light">Thank you for shopping at Radiant Apparel</h1>
                <h2 class="text-light">This is a project used for learning purposes!</h2>
                <h3 class="text-light">Not a real store</h3>
            </div>
            <div class="bottle">
                <h1 class="mb-2 text-light">Your Purchase!</h1>
                <div class="d-flex flex-wrap gap-2 mb-2 container line">
                    <c:forEach var="oneObject" items="${cart}">
                        <c:forEach var="oneKey" items="${oneObject.entrySet()}">
                            <div class="mb-2">
                                <img class="productImage" src="${oneKey.getKey().image}"/>
                                <h4 class="word-wrap w-1 text-light"><c:out value="${oneKey.getKey().name}"></c:out></h4>
                                <h4 class="text-light"><c:out value="${currencyFormat.format(oneKey.getKey().price.unitAmount * oneKey.getValue())}"></c:out></h4>
                            </div>
                        </c:forEach>
                    </c:forEach>
                </div>
                <h2 class="mb-2 text-light">Total: <c:out value="${currencyFormat.format(total)}"></c:out></h2>
                <form action="/success/dashboard">
                    <button class="btn" type = "submit" style="cursor:pointer; font-size:24px">Home</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>