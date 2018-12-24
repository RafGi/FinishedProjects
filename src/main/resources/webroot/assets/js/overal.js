"use strict";

$(function () {
    $('.button').on('click', (e) => {
        e.preventDefault();

        let location = e.currentTarget.getAttribute('href');
        window.location.href = location;
})
});