"use strict";

$(function () {
    $("#logout").on('click', logout);

    $('#settings').on('click', (e) => {
        $('.modal_container').fadeIn();
});
    $('#close').on('click', (e) => {
        $('.modal_container').fadeOut();
});
});

function logout(e) {
    e.preventDefault();
    sessionStorage.clear();
    document.location.replace("/static/login.html");
}