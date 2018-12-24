"use strict";

document.addEventListener("DOMContentLoaded", init);

function init() {
    document.querySelectorAll("button").forEach(function(item){item.addEventListener("click",refer)});
}

function refer(e){
    e.preventDefault();
    console.log(e.target);
}
