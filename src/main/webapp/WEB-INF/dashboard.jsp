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
    <link rel="stylesheet" href="/css/dashboard.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Radiant Apparel</title>
</head>
<body>
    <div class = "navextension bgdark d-flex align-items-center">
    </div>
    <nav class="navbar bgdark">
        <div class = "navcontentstart bgdark">
            <img src="/img/thirdlogodark.png" alt="Radiance Logo" id = "logo">
            <div class = "searchbox">
                <form action="#">
                    <!-- <input type="image" name = "searchimg" src="/img/magnifying-glass.png" alt="Icon made by Chanut from www.flaticon.com" id = searchimg> -->
                    <input type="text" name = "search" class = "searchbar" placeholder="Search....">
                    <input type="submit" class="searchbtn bgblue">
                </form>
            </div>
        </div>
        <div class = "navcontentend bgdark">
            <div class="d-flex align-items-center justify-content-between">
                <a href="/" class = "nav-tag pointer-select">Home</a>
                <p class = "nav-space">|</p>
                <a href="/about" class = "nav-tag">About</a>
                <p class = "nav-space">|</p>
                <a href="/shop" class = "nav-tag">Shop</a>
                <p class = "nav-space">|</p>
                <a href="/admin" class = "nav-tag">Admin</a>
                <p class = "nav-space">|</p>
                <a href="#" class = "nav-tag">Help</a>
            </div>
            <div class = "navcart">
                <form action="#">
                    <button type = "submit" style="cursor:pointer; border: none; background-color: transparent; color: rgb(198,241,241); font-size:24px"><i class="fa fa-shopping-cart"></i> Cart</button>
                </form>
            </div>
            
            
        </div>
        
    </nav>
    <div class="gradient2 height2 pt-10">
        <div class="text-center">
            <h1 class="head">Radient Apparel</h1>
            <h1 class="head text-danger">CHANGE STRIPE KEY!!!</h1>
            <div class="d-flex justify-content-center">
            </div>
            <div class="d-flex justify-content-center">

                <div class="carousel d-flex align-items-center justify-content-center">
                    <button class="carouselbuttonleft" onclick="scrollleft()"> < </button>
                    <div class="carousel-container d-flex" id="carousel-container">
                        <div class="pb-10 carousel1 d-flex" >
                            <c:forEach var="oneProduct" items="${allProducts}">
                                <div>
                                    <a href="/product/show/${oneProduct.id}">
                                        <img src='<c:out value="${oneProduct.images}"/>' alt="testing" class="productImgs item${oneProduct.id}">
                                    </a>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <button class="carouselbuttonright" onclick="scrollright()" > > </button>
                    
                </div>
                
            </div>
        </div>

    </div>
    <div class="gradient height text-center">
        <h1 class="text-light title pt-10">Featured Products</h1>
        <div class="d-flex justify-content-center">

            <div class="carousel2 d-flex align-items-center justify-content-center">
                <button class="carouselbuttonleft" onclick="scrollleft2()"> < </button>
                <div class="carousel-container2 d-flex" id="carousel-container2">
                    <div class="pb-10 carousel1 d-flex" >
                        <c:forEach var="oneProduct" items="${allProducts}">
                            <div class="center-text">
                                <a class="producta" href="/product/show/${oneProduct.id}">
                                    <img src='<c:out value="${oneProduct.images}"/>' alt="testing" class="productImgs2 ">
                                    <p class="producttag"><c:out value="${oneProduct.name}"></c:out></p>
                                    <p class="producttag"><c:out value="${currencyFormat.format(oneProduct.price.unitAmount)}"></c:out></p>
                                </a>
                            </div>
                            <div class="featured"></div>
                        </c:forEach>
                    </div>
                </div>
                <button class="carouselbuttonright" onclick="scrollright2()" > > </button>
                
            </div>
        </div>

    </div>
    <div class="gradient3 height3">
        <h1 class="title text-center text-light">Best Reviewed Products</h1>
        <div class="BRP mt-10">
            <c:forEach var = "oneProduct" items = "${allProducts}">
                <c:choose>
                    <c:when test ="${allProducts.indexOf(oneProduct) == 0}">
                        <div>
                            <img src='<c:out value="${oneProduct.images}"/>' alt="testing" class="productImgs3">
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="text-center">
                            <a class="producta" href="/product/show/${oneProduct.id}">
                                <img src='<c:out value="${oneProduct.images}"/>' alt="testing" class="productImgs4 ">
                                <p class="producttag3"><c:out value="${oneProduct.name}"></c:out></p>
                                <p class="producttag4"><c:out value="${currencyFormat.format(oneProduct.price.unitAmount)}"></c:out></p>
                            </a>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </div>
    <script src="/js/dashboard.js"></script>
</body>
</html>