"use strict";

document.addEventListener("DOMContentLoaded", init);

function init(){
    document.addEventListener("keydown", keydown);
    document.addEventListener("keyup", keyup);
}

function keydown(e){
    e = e || window.event;
    userInput(e.keyCode);
}

function keyup(e){
    e = e || window.event;
    userInput("$");
}