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
                                    <a href="/" class="nav-tag">Home</a>
                                    <p>|</p>
                                    <a href="#" class="nav-tag">About</a>
                                    <p>|</p>
                                    <a href="/shop" class="nav-tag">Shop</a>
                                    <p>|</p>
                                    <a href="/admin" class="nav-tag">Admin</a>
                                    <p>|</p>
                                    <a href="#" class="nav-tag">Help</a>
                                </div>
                                <div class="navcart">
                                    <button
                                        style="border: none; background-color: transparent; color: rgb(198,241,241); font-size:24px"><i
                                            class="fa fa-shopping-cart"></i> Cart</button>
                                </div>


                            </div>

                        </nav>

                        <div class="d-flex flex-column justify-content-center align-items-center mt-10 ">
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
                                <!-- For Each -->
                                <div class="d-flex typeList">
                                    <c:forEach var="type" items="${allTypes}">
                                        <!-- <div class="dropdown"> -->

                                                <h3 class="pointer-select">
                                                    <c:out value="${type.name}"></c:out>&nbsp;<c:if
                                                        test="${allTypes.indexOf(type) != allTypes.size()- 1}">|</c:if>
                                                    &nbsp;
                                                </h3>
                                            <div class="dropdown-content">
                                                <div class="d-flex justify-content-center">
                                                    <c:forEach var="category" items="${type.categories}">
                                                        <a href="/category/show/${category.id}">
                                                            <c:out value="${category.name}"></c:out>
                                                        </a>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                            
                                        <!-- </div> -->
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                        <div class="listProducts d-flex justify-content-center">
                            <div class="d-flex justify-content-between productColumns">
                                <c:forEach var="oneProduct" items="${categoryProducts}">
                                    <div>
                                        <a class="productNames" href="/product/show/${oneProduct.id}"><img
                                                src='<c:out value="${oneProduct.images}"/>' alt="testing"
                                                class="productImgs">
                                            <p class="">
                                                <c:out value="${oneProduct.name}"></c:out>
                                                <br>
                                                <c:out value="${currencyFormat.format(oneProduct.price.unitAmount)}">
                                                </c:out>
                                        </a>
                                        </p>
                                    </div>
                                </c:forEach>

                            </div>
                        </div>
                    </body>

                    </html>