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
    <title><c:out value="${product.name}"></c:out></title>
    <link rel="stylesheet" href="/css/library.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
    <div class = "navextension bgdark d-flex align-items-center">
    </div>
    <nav class="navbar bgdark">
        <div class = "navcontentstart bgdark">
            <img src="/img/thirdlogodark.png" alt="Radiance Logo" id = "logo">
        </div>
        <div class = "navcontentend bgdark">
            <a href="#" class = "text-white">Home</a>
            <p>|</p>
            <a href="#" class = "text-white">About</a>
            <p>|</p>
            <a href="/shop" class = "text-white ">Shop</a>
            <p>|</p>
            <a href="/admin" class = "text-white">Help</a>
            <a href="/cart" class="pointer-select" style="border: none; background-color: transparent; color: white; font-size:24px"><i class="fa fa-shopping-cart"></i> Cart</a>
        </div>
    </nav> 
    <div>
        <div class="mt-10 d-flex">
            <div>
                <img src='<c:out value="${product.images}"/>' alt="testing">
            </div>
            <div>
                <div>
                    <h1><c:out value="${product.name}"></c:out></h1>
                    <p>Reviews</p>
                </div>
                <h1><c:out value="${currencyFormat.format(product.price.unitAmount)}"></c:out></h1>
                <form action="/cart/add/${product.id}" method="post">
                    <button>Add to Cart</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>