"use strict";

$(function () {
    console.log("loaded");

    $(window).on('load', start);

    $(window).on('resize', resize);

    $('#reglink').on('click', (e)=>{
        e.preventDefault();
        $('.login').fadeOut(1000, ()=>{
            $('.register';).fadeIn(1000);
})
})

    /* TODO: edit for real registration */
    /* TODO: on succes go back to login tab with succes message */
    $('#register').on('click', (e)=>{
        e.preventDefault();
        $('.register').fadeOut(1000, ()=>{
            $('.login';).fadeIn(1000);
})
})

    /* TODO: edit for real login */
    /* TODO: add profile pic path, username, level, needen exp, current exp and clanbanner to local storage for later use */
    $('#login').on('click', (e)=>{
        e.preventDefault();
        
        window.location.href = "index.html";
})
    $('.btn').on('click', function(e){
        e.preventDefault();
        if ( $(this).hasClass('play') ) {
            $(this).removeClass('play');
            $(this).addClass('pause');
        } else {
            $(this).removeClass('pause');
            $(this).addClass('play');
        }

        pauseAnim();
    });


});