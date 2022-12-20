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
                        <title>
                            <c:out value="${product.name}"></c:out>
                        </title>
                        <link rel="stylesheet" href="/css/library.css">
                        <link rel="stylesheet"
                            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
                        <link rel="stylesheet" href="/css/product.css">
                    </head>

                    <body>
                        <div class="navextension bgdark d-flex align-items-center">
                        </div>
                        <nav class="navbar bgdark">
                            <div class="navcontentstart bgdark">
                                <img src="/img/thirdlogodark.png" alt="Radiance Logo" id="logo">
                            </div>
                            <div class="navcontentend bgdark">
                                <a href="#" class="text-white">Home</a>
                                <p>|</p>
                                <a href="#" class="text-white">About</a>
                                <p>|</p>
                                <a href="/shop" class="text-white ">Shop</a>
                                <p>|</p>
                                <a href="/admin" class="text-white">Help</a>
                                <a href="/cart" class="pointer-select"
                                    style="border: none; background-color: transparent; color: white; font-size:24px"><i
                                        class="fa fa-shopping-cart"></i> Cart</a>
                            </div>
                        </nav>
                        <div>
                            <div class="d-flex justify-content-center p-10">
                                <div>
                                    <img class="productImage" src='<c:out value="${product.images}"/>' alt="testing">
                                    <form:form action="/product/review/${product.id}" method="post"
                                        modelAttribute="review">
                                        <input type="hidden" value="${product.id}" name="product">
                                        <input type="hidden" value="${userId}" name="reviewer">
                                        <form:label path="name">Add a review</form:label>
                                        <form:textarea class="input" path="name" />
                                        <form:errors path="name" />
                                        <input type="submit" value="Post" />
                                    </form:form>
                                </div>
                                <div class="ml-5 productOptions">
                                    <div class="d-flex flex-column ">
                                        <h1>
                                            <c:out value="${product.name}"></c:out>
                                        </h1>
                                        <h1 class="mt-10">
                                            <c:out value="${currencyFormat.format(product.price.unitAmount)}"></c:out>
                                        </h1>
                                        <form action="/cart/add/${product.id}" method="post">
                                            <label for="quantity">Quantity:</label>
                                            <select name="quantity">
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
                                            <div class="mt-10 d-flex">
                                                <div class="cartBtn">
                                                    <button class="productBtn">Add to Cart</button>
                                                </div>
                                        </form>
                                        <div class="cartBtn">
                                            <form action="/wishlist/add/${product.id}" method="post">
                                                <button class="productBtn">Add to Wishlist</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                <div class="reviews">
                                    <c:forEach var="review" items="${productReviews}">
                                        <c:if test="${review.reviewer != null}">
                                            <div class="oneReview">
                                                <h4>Posted by <c:out value="${review.reviewer.firstName}">
                                                    </c:out>: </h4>
                                                <h5 class="dateReviewed">Reviewed on
                                                    <fmt:formatDate value="${review.createdAt}"
                                                        pattern="MMMM dd yyyy" />
                                                </h5>
                                                <p>
                                                    <c:out value="${review.name}"></c:out>
                                                </p>
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </body>
                    <!-- <div class="reviews">
                        <c:forEach var="review" items="${productReviews}">
                            <c:if test="${review.reviewer != null}">
                                <div class="oneReview">
                                    <h4>Posted by <c:out value="${review.reviewer.firstName}"></c:out>: </h4>
                                    <h5 class="dateReviewed">Reviewed on
                                        <fmt:formatDate value="${review.createdAt}" pattern="MMMM dd yyyy" />
                                    </h5>
                                    <p>
                                        <c:out value="${review.name}"></c:out>
                                    </p>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div> -->

                    </html>