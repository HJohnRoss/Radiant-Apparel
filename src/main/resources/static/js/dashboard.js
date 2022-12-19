
var scroll = 20;
var slider1 = document.querySelector("#carousel-container");
function scrollright(){
    slider1.scrollBy(100, 0)
    console.log("it works")
}

function scrollleft(){
    slider1.scrollBy(-100, 0)
    console.log("it works")
}