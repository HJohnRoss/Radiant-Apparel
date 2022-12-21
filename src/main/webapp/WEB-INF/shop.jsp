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
    <title>Radiant Apparel</title>
    <link rel="stylesheet" href="/css/library.css">
    <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- Shop bottom so takes prio -->
    <link rel="stylesheet" href="/css/shop.css">
</head>

<body>
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
    <div class="gradient">

        <div class="d-flex flex-column justify-content-center align-items-center pt-10">
            <!-- Border decor around -->
            <div class="text-center">
                <h1 class="shopHeading">Radiant Apparel</h1>
            </div>
            <div id="headingLine"></div>
            <div class="test">
                <form action="/search">
                    <input class="searchbtn" type="text" placeholder="Search..." name="name">
                    <button class="btn" type="submit">Submit</button>
                </form>
            </div>

            <div>
                <div class="d-flex typeList">
                    <c:forEach var="type" items="${allTypes}">
                        <div>
                            <div class="dropdown">
                                <h3 class="pointer-select text-light">
                                    <c:out value="${type.name}"></c:out>&nbsp;<c:if
                                        test="${allTypes.indexOf(type) != allTypes.size()- 1}">|
                                    </c:if>
                                    &nbsp;
                                </h3>
                                <div class="dropdown-content">
                                    <c:forEach var="category" items="${type.categories}">
                                        <a href="/category/show/${category.id}">
                                            <c:out value="${category.name}"></c:out>
                                        </a>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

        <div class="listProducts d-flex justify-content-center">
            <div class="d-flex justify-content-between productColumns">
                <c:forEach var="oneProduct" items="${categoryProducts}">
                    <div class="oneProduct">
                        <a class="productNames" href="/product/show/${oneProduct.id}"><img
                                src='<c:out value="${oneProduct.images}"/>' alt="testing"
                                class="productImgs">
                            <p class="text-light">
                                <c:out value="${oneProduct.name}"></c:out>
                            </p>
                            <p class="text-light">
                                <c:out
                                    value="${currencyFormat.format(oneProduct.price.unitAmount)}">
                                </c:out>
                            </p>
                        </a>
                    </div>
                </c:forEach>

            </div>
        </div>
    </div>
</body>

</html>