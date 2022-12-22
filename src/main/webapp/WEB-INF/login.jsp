<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="/css/library.css">
    <link rel="stylesheet" href="/css/login.css">
    <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

</head>

<body class="gradient">
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
    
    <div class="d-flex justify-content-center cardboard">
        <div class="bottle register">
            <h2>Register</h2>
            <form:form action="/register" method="post" modelAttribute="newUser" class="">
                    <div>
                        <p>First Name: </p>
                        <form:errors class="text-danger" path="firstName" />
                    </div>
                    <form:input path="firstName" />
                    <div>
                        <p>Last Name: </p>
                        <form:errors class="text-danger" path="lastName" />
                    </div>
                    <form:input path="lastName" />
                    <div>
                        <p> Email: </p>
                        <form:errors class="text-danger" path="email" />
                    </div>
                    <form:input path="email" />
                    <div>
                        <p> Password: </p>
                        <form:errors class="text-danger" path="password" />
                    </div>
                    <form:input type="password" path="password" />
                    <div>
                        <p> Confirm Password: </p>
                        <form:errors class="text-danger" path="confirm" />
                    </div>
                    <div>
                        <form:input type="password" path="confirm" />
                    </div>
                <button>Register</button>
            </form:form>
        </div>
        <div class="bottle login">
            <h2>Login</h2>
            <form:form action="/loguser" modelAttribute="newLogin" class="">
                <div>
                    <form:errors class="text-danger" path="email" />
                    <p>Email: </p>
                </div>
                <form:input path="email"/>
                <div>
                    <p> Password: </p>
                    <form:errors class="text-danger" path="password" />
                </div>
                <div>
                    <form:input type="password" path="password"/>
                </div>
            <button>Log in</button>
            </form:form>
        </div>
    </div>
    <script type="text/javascript" src="/js/app.js"></script>
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>