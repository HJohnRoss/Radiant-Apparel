<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="/css/style.css">

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
    <div class="d-flex justify-content-center ">
        <div>
            <form:form action="/register" method="post" modelAttribute="newUser" class="">
                <p>
                    <form:label path="firstName"> firstName </form:label>
                    <form:errors path="firstName" />
                    <form:input path="firstName" />
                </p>
                <p>
                    <form:label path="lastName"> lastName </form:label>
                    <form:errors path="lastName" />
                    <form:input path="lastName" />
                </p>
                <p>
                    <form:label path="email"> email </form:label>
                    <form:errors path="email" />
                    <form:input path="email" />
                </p>
                <p>
                    <form:label path="password"> password </form:label>
                    <form:errors path="password" />
                    <form:input type="password" path="password" />
                </p>
                <p>
                    <form:label path="confirm">confirm password </form:label>
                    <form:errors path="confirm" />
                    <form:input type="password" path="confirm" />
                </p>
                <input type="submit" value="submit" />
            </form:form>
        </div>
        <div>
            <form:form action="/loguser" modelAttribute="newLogin" class="">
                <p>
                    <form:label path="email"> email </form:label>
                    <form:errors path="email" />
                    <form:input path="email" />
                </p>
                <p>
                    <form:label path="password"> password </form:label>
                    <form:errors path="password" />
                    <form:input type="password" path="password" />
                </p>
                <input type="submit" value="submit" />
            </form:form>
        </div>
    </div>
    <script type="text/javascript" src="/js/app.js"></script>
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>