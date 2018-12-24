"use strict";

document.addEventListener("DOMContentLoaded", init);

function init() {
    //selected heroes accentueren
    selectedHeroes();
    document.querySelector("main").querySelectorAll("figure").forEach(function (item) {
        item.addEventListener("click", chooseHero)
    });
}

function selectedHeroes() {
    document.querySelector("main").querySelectorAll("figure").forEach(function (item) {
        item.classList.remove("selected")
    });
    document.getElementById("alien").classList.add("selected");
    document.getElementById("crusader").classList.add("selected");
    document.getElementById("sumo").classList.add("selected");
    document.getElementById("warrior").classList.add("selected");
}

function chooseHero(e) {
    e.preventDefault();
    if ((e.target.parentElement.className) == "buy") {
        console.log("you have not unlocked this hero yet")
    } else {
        e.target.parentElement.parentElement.parentElement.querySelectorAll("figure").forEach(function (item) {item.classList.remove("selected")});
        e.target.parentElement.classList.add("selected");
    }

}