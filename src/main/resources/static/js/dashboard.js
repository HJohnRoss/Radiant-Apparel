
var scroll = 20;
var slider1 = document.querySelector("#carousel-container");
var slider2 = document.querySelector("#carousel-container2")
function scrollright(){
    slider1.scrollBy(100, 0)
    console.log("it works")
}

function scrollleft(){
    slider1.scrollBy(-100, 0)
    console.log("it works")
}

function scrollright2(){
    slider2.scrollBy(100, 0)
    console.log("it works")
}

function scrollleft2(){
    slider2.scrollBy(-100, 0)
    console.log("it works")
}