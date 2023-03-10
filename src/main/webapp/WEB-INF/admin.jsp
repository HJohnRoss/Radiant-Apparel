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
    <link rel="stylesheet" href="/css/admin.css">
    <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Admin</title>
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

    <div class="pt-10 d-flex justify-content-around">
        <div class="bottle">
            <h2>Add a Product</h2>
            <form:form action="/product/create" method="post" modelAttribute="product">
                <form:errors class="text-danger" path="name"></form:errors>
                <div>
                    <form:label path="name">Name of product:</form:label>
                    <form:input path="name" name="name" type="text"></form:input>
                </div>
                <form:errors class="text-danger" path="images"></form:errors>
                <div>
                    <form:label path="images">Url for an image:</form:label>
                    <form:input path="images" name="images" type="text"></form:input>
                </div>
                <button>Add Product</button>
            </form:form>


            <h2>Add a Price</h2>
            <form:form action="/price/create" method="post" modelAttribute="price">
                <form:errors class="text-danger" path="unitAmount"></form:errors>
                <div>
                    <form:label path="unitAmount">Price of Product:</form:label>
                    <form:input path="unitAmount" name="unitAmount" type="text" placeholder="1.00"/>
                </div>
                <div>
                    <form:label path="product">Product:</form:label>
                    <form:select path="product" name="product">
                        <c:forEach var="oneProduct" items="${allProducts}">
                            <c:if test="${oneProduct.price == null}">
                                <option value="${oneProduct.id}">
                                    <c:out value="${oneProduct.name}"></c:out>
                                </option>
                            </c:if>
                        </c:forEach>
                    </form:select>
                </div>
                <button>Add Price</button>
            </form:form>
        </div>
        <div class="bottle">
            <div>
                <h2>Create a Type of Category:</h2>
                <form:form action="/type/create" method="post" modelAttribute="type">
                    <form:errors class="text-danger" path="name"></form:errors>
                    <div>
                        <form:label path="name">Name of Type:</form:label>
                        <form:input path="name" type="text"></form:input>
                    </div>
                    <button>Create Type</button>
                </form:form>
            </div>
            <div>
                <h2>Create a Category:</h2>
                <form:form action="/category/create" method="post" modelAttribute="category">
                    <form:errors class="text-danger" path="name"></form:errors>
                    <div>
                        <form:label path="name">Name of Category:</form:label>
                        <form:input path="name" type="text"></form:input>
                    </div>
                    <div>
                        <form:label path="type">Type of Category:</form:label>
                        <form:select path="type">
                            <c:forEach var="oneType" items="${allTypes}">
                                <option value="${oneType.id}">
                                    <c:out value="${oneType.name}"></c:out>
                                </option>
                            </c:forEach>
                        </form:select>
                    </div>
                    <button>Add Category</button>
                </form:form>
            </div>
            <div>
                <h2>Add a product to a category:</h2>
                <h4>Select a Type of category:</h4>
                <ul>
                    <c:forEach var="oneType" items="${allTypes}">
                        <li><a href="/type/${oneType.id}">
                                <c:out value="${oneType.name}"></c:out>
                            </a></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <div class="bottle">
            <h2>Delete:</h2>
            <ul>
                <li><a href="/products/delete">products</a></li>
                <li><a href="/type/delete">Type of category</a></li>
                <li><a href="/category/delete">Category</a></li>
            </ul>
        </div>
    </div>
</body>

</html>