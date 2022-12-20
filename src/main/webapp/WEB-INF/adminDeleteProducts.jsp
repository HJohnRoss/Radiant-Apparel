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
        <link rel="stylesheet" href="/css/admin.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Admin Types</title>
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
                    <a href="/" class = "nav-tag">Home</a>
                    <p class="nav-tag">|</p>
                    <a href="/about" class = "nav-tag">About</a>
                    <p class="nav-tag">|</p>
                    <a href="/shop" class = "nav-tag">Shop</a>
                    <p class="nav-tag">|</p>
                    <a href="/admin" class = "nav-tag">Admin</a>
                    <p class="nav-tag">|</p>
                    <a href="#" class = "nav-tag">Help</a>
                </div>
                <div class = "navcart">
                    <button style="border: none; background-color: transparent; color: rgb(198,241,241); font-size:24px" ><i class="fa fa-shopping-cart"></i> Cart</button>
                </div>
                
                
            </div>
            
        </nav>
        <div class="pd-admin text-center bottle set-width">
            <div class="d-flex align-items-center justify-content-center">
                <h2>Delete Products</h2>
                <a href="/admin" class="adminbtn">go back</a>
            </div>
            <ul class="d-flex wrap">
                <c:forEach var="oneProduct" items="${allProducts}">
                    <li class="d-flex">
                        <img src='<c:out value="${oneProduct.images}"/>' alt="product image" class="adminimg">
                        <div class="adminwidth">
                            <p class="adminpmargin"><c:out value="${oneProduct.name}"></c:out></p>
                            <form action="/product/delete/${oneProduct.id}" method="post">
                                <input type="hidden" name="_method" value="delete"/>
                                <input type="hidden" name="stripeProductId" value="${oneProduct.stripeProductId}">
                                <button class="btn btn-danger">Delete</button>
                            </form>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
</body>
</html>