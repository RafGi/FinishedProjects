"use strict";
const eb = new EventBus("/tetris-28/socket/");

$(function () {
    console.log("loaded");

    $(window).on('load', start);

    $(window).on('resize', resize);

    $("#login").on('click', initLogin);
    $("#register").on('click', initRegister);

    $('#reglink').on('click', (e) => {
        e.preventDefault();
        $('.login').fadeOut(1000, () => {
            $('.register').fadeIn(1000);
        });
    });
    $('.btn').on('click', function (e) {
        e.preventDefault();
        if ($(this).hasClass('play')) {
            $(this).removeClass('play');
            $(this).addClass('pause');
        } else {
            $(this).removeClass('pause');
            $(this).addClass('play');
        }

        pauseAnim();
    });
});

function initLogin(e) {
    e.preventDefault();
    console.log("login");
    eb.onopen = loginPlayer();
}

function initRegister(e) {
    e.preventDefault();
    console.log("register");
    eb.onopen = registerPlayer();
}

function loginPlayer() {
    const username = $("#login_username").val();
    const pwd = $("#login_password").val();
    eb.send("tetris.events.loginplayer", {username: username, password: pwd}, (error, reply) => {
        console.log("sent");
        //let JsonData=JSON.parse(reply["body"]);
        console.log(reply["body"]);
        const response = reply["body"];
        if (response === "OK") {
            sessionStorage.setItem("username", username);
            window.location.replace("/static/index.html");
        } else {
            $("#loginfail").html("Failed to login");
        }
    })
}

function registerPlayer() {
    const username = document.getElementById("reg_username").value;
    const email = document.getElementById("mail").value;
    const password = document.getElementById("reg_password").value;
    const pwCheck = document.getElementById("password_rep").value;

    console.log(password + "    " + pwCheck);

    if (password == pwCheck) {
        eb.send('tetris.events.registerplayer', {
            username: username,
            email: email,
            password: password,
            pwcheck: pwCheck
        }, (error, reply) => {
            const response = reply["body"];
            if (response === "OK") {
                $('.register').fadeOut(1000, () => {
                    $('.login').fadeIn(1000);
            })
            } else {
                $("#registerfail").html("Failed to register");
            }
        })
    }
}