

<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/stripeCheckout.css" />
    <!-- MAKE SURE STRIPE.JS IS ABOVE OUR JS FILE && BELOW ALL THE CSS FILES -->
    <script src="https://js.stripe.com/v3/"></script>
    <script src="/js/checkout.js" defer></script>
    <title>Radiant Apparel</title>
</head>
<body>
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