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
            <a href="#" class = "text-white">Home</a>
            <p>|</p>
            <a href="#" class = "text-white">About</a>
            <p>|</p>
            <a href="#" class = "text-white">Shop</a>
            <p>|</p>
            <a href="#" class = "text-white">Help</a>
            <button style="border: none; background-color: transparent; color: white; font-size:24px"><i class="fa fa-shopping-cart"></i> Cart</button>
            
            
        </div>
        
    </nav>
    <div class="height">
        <h1 class="text-danger mt-10">CHANGE STRIPE KEY!!!</h1>
    </div>
</body>
</html>