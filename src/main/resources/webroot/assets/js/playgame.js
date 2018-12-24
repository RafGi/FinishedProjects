"use strict";

$(function () {
    let time = 5;
    let klok = window.setInterval(timer, 1000);

    function timer() {
        if (time > 0) {
            document.getElementById("timer").innerHTML = time + "s ";
            time = time - 1;
        }
        else {
            document.getElementById("timer").innerHTML = time + "s ";
            window.clearInterval(klok);
            window.location.href = "game.html";
        }
    }
});