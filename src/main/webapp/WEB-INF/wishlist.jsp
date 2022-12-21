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
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Wishlist</title>
    <link rel="stylesheet" href="/css/library.css">
    <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- Shop bottom so takes prio -->
    <link rel="stylesheet" href="/css/wishlist.css">
</head>

<body class="gradient">
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
    <div class="wrapper">
        <h1 class="wishListHead">Your wishlist</h1>
        <c:forEach var="product" items="${wishlist}">
            <div class="oneProduct d-flex justify-content-between">
                <a href="/product/show/${product.id}">

                    <img class="productImages" src='<c:out value="${product.images}"/>'
                        alt='<c:out value="${product.name}"/>'>
                    <div class="productInfo">
                        <p>
                            <c:out value="${currencyFormat.format(product.price.unitAmount)}">
                            </c:out>
                        </p>
                        <p>
                            <c:out value="${product.name}"></c:out>
                        </p>

                </a>

                <div>
                    <form action="/wishlist/remove/${product.id}" method="post">
                        <input type="hidden" name="_method" value="put">
                        <input type="submit" class="productBtn" value="Remove from Wishlist">
                    </form>
                </div>

            </div>
    </div>
    <div class="seperator"></div>
    </c:forEach>
    </div>
</body>

</html>