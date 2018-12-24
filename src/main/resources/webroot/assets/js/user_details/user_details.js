"use strict";
const eb = new EventBus("/tetris-28/socket/");

const bar = new ProgressBar.Line('.progress-bar', {
    strokeWidth: 4,
    easing: 'easeInOut',
    duration: 1400,
    color: '#FFEA82',
    trailColor: '#fff',
    trailWidth: 1,
    svgStyle: {
        width: '100%',
        height: '100%'
    }
});

$(function () {
    if (sessionStorage.getItem('user_details') == null) {
        console.log("need player data");
        eb.onopen = getPlayerData;
    } else {
        console.log('existing session');
        insertUserData(JSON.parse(sessionStorage.getItem('user_details')));
    }
});

function getPlayerData() {
    const playerName = sessionStorage.getItem("username");
    console.log(playerName);

    eb.send("tetris.events.getplayerbyname", {username: playerName}, function (error, reply) {

        const JsonData = JSON.parse((reply)["body"]);
        sessionStorage.setItem('user_details', JSON.stringify(JsonData));
        sessionStorage.setItem('userid', JsonData.id);

        insertUserData(JsonData);
    })
}

function insertUserData(data) {
    const lvl = calcLevel(data.expierence);

    $('#username').html(data.username);
    $('#level').html(`lvl ${lvl.lvl}`);

    bar.animate(lvl.remaining);
}

function calcLevel(exp) {
    let lvl = 0;
    let temp = exp;

    while (temp > 1000) {
        lvl++;
        temp -= 1000;
    }

    temp /= 1000;

    return {lvl: lvl, remaining: temp};
}