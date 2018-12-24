"use strict";

$(function () {
    eb.onopen = handleServerMessages;
});

function handleServerMessages() {
    console.log("[EVENTBUS]  attempting to contact socket server");

    eb.registerHandler('tetris.events.gameinfo', function (error, message) {
        if (error) {
            console.log('An error has occurred', error);
        }

        console.log('[EVENTBUS] (Gameinfo) received message from vertx Info ' + JSON.stringify(message.body));
        //document.querySelector('.receivedmessage').innerHTML = JSON.stringify(message.body);
    });

    console.log('[EVENTBUS] (Info) sending message to vertx Info');
    eb.send('tetris.events.info', "Give me game info");
    getStatsByPlayerId();
}

function getStatsByPlayerId() {
    let session = JSON.parse(sessionStorage.getItem("user_details"));
    let playerid = session["id"];
    console.log(playerid);
    eb.send("tetris.events.getstatsbyplayerid", {playerid: playerid}, function (error, reply) {
        let JsonData = JSON.parse((reply)["body"]);
        console.log(JsonData);

        populatestats(JsonData);
    })
}


function populatestats(data) {
    document.getElementById("stats").innerHTML += "<p>Games played: <span>" + data.gamesPlayed + "</span></p>";
    document.getElementById("stats").innerHTML += "<p>Games won: <span>" + data.wins + "</span></p>";
//        document.getElementById("stats").innerHTML+= "<p>Blocks played: <span>" + data.highscore + "</span></p>";
    document.getElementById("stats").innerHTML += "<p>Lines made: <span>" + data.amountOfLines + "</span></p>";
    document.getElementById("stats").innerHTML += "<p>Tetrises made: <span>" + data.amountOfTetris + "</span></p>";
    document.getElementById("stats").innerHTML += "<p>High score: <span>" + data.highscore + "</span></p>";
    document.getElementById("stats").innerHTML += "<p>Longest winstreak: <span>" + data.winStreak + "</span></p>";
}
