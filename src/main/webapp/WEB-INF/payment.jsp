<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
                <!DOCTYPE html>
                <html>

                <head>
                    <meta charset="UTF-8">
                    <meta http-equiv="X-UA-Compatible" content="IE=edge">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <!-- this is the stylesheet for the stripe checkout u can change it to our needs (from stripe) -->
                    <!-- <link rel="stylesheet" href="/css/stripeCheckout.css" /> -->
                    <script src="https://js.stripe.com/v3/"></script>
                    <script src="/js/checkout.js" defer></script>
                    <title>Radiant Apparel</title>
                </head>

                <body>
                    <c:choose>
                        <c:when test="${cart != []}">
                            <c:forEach var="oneObject" items="${cart}">
                                <c:forEach var="oneKey" items="${oneObject.entrySet()}">
                                    <h1>
                                        <c:out value="${oneKey.getKey().name}"></c:out>
                                    </h1>
                                    <img src="${oneKey.getKey().image}" />
                                    <h4>Price: <c:out
                                            value="${currencyFormat.format(oneKey.getKey().price.unitAmount * oneKey.getValue())}">
                                        </c:out>
                                    </h4>
                                </h2>
                            </c:forEach>
                        </c:forEach>
                        <h2>Total: <c:out value="${currencyFormat.format(total)}"></c:out>
                        </c:when>
                        <c:otherwise>
                            <p>Cart is empty</p>
                        </c:otherwise>
                    </c:choose>
                    <!-- Display a payment form -->
                    <form id="payment-form">
                        <div id="payment-element">

                        </div>
                        <button id="submit">
                            <div class="spinner hidden" id="spinner"></div>
                            <span id="button-text">Pay now</span>
                        </button>
                        <div id="payment-message" class="hidden"></div>
                    </form>
                </body>

                </html>