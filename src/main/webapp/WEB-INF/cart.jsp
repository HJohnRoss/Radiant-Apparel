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
    <link rel="stylesheet" href="/css/library.css">
    <link rel="stylesheet" href="/css/cart.css">
    <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Your Cart</title>
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
    
    <div class="gradient">
        <c:choose>
            <c:when test="${cart != []}">
                <div class="bottle d-flex justify-content-around carttotalwidth margin-auto">
                    <div>
                        <c:forEach var="oneObject" items="${cart}">
                            <c:forEach var="oneKey" items="${oneObject.entrySet()}">
                                <div class="d-flex justify-content-between margin-auto cartwidth cart-item-margin">
                                    <img class="cartimg" src="${oneKey.getKey().image}" />
                                    <div>
                                        <h1 class="text-light cart-name-width">
                                            <c:out value="${oneKey.getKey().name}"></c:out>
                                        </h1>
                                        <h4 class="text-light">Price: <c:out
                                                value="${currencyFormat.format(oneKey.getKey().price.unitAmount * oneKey.getValue())}">
                                            </c:out>
                                        </h4>
                                        <form action="/cart/update/${oneKey.getKey().id}" method="post">
                                            <div class="d-flex align-items-center quantity-margin">
                                                <label for="quantity" class="text-light">Quantity:</label>
                                                <select name="quantity" class="cartslct">
                                                    <option value="${oneKey.getValue()}">
                                                        <c:out value="${oneKey.getValue()}"></c:out>
                                                    </option>
                                                    <option value="1">1</option>
                                                    <option value="2">2</option>
                                                    <option value="3">3</option>
                                                    <option value="4">4</option>
                                                    <option value="5">5</option>
                                                    <option value="6">6</option>
                                                    <option value="7">7</option>
                                                    <option value="8">8</option>
                                                    <option value="9">9</option>
                                                    <option value="10">10</option>
                                                </select>
                                                <div class="cartBtn">
                                                    <button class="productBtn cartbtn">Update Quantity</button>
                                                </div>
                                            </div>
                                        </form>
                                        <form action="/cart/delete/${oneKey.getKey().id}" method="post">
                                            <button class="cartbtn"><img class="deletebtn" src="/img/delete.png"
                                                    alt="Delete icons created by Ilham Fitrotul Hayat - Flaticon"></button>
                                        </form>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:forEach>
                    </div>
                    <div>
                        <h2 class="text-light checkout-margin">Total: <c:out value="${currencyFormat.format(total)}"></c:out>
                        </h2>
                        <a href="/checkout" class="cartbtn">Checkout</a>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="text-center">
                    <p class="text-light emptycart">Cart is empty</p>
                    <p class="text-light emptycart2">But it doesn't have to be</p>
                    <a href="/shop" class="cartbtn">Shop Now!</a>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</body>

</html>