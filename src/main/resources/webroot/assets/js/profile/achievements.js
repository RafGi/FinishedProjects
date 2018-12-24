"use strict";

$(function () {
    eb.onopen = handleServerMessages;
});


function handleServerMessages() {

    eb.registerHandler('tetris.events.gameinfo', function (error, message) {
        if (error) {
            console.log('An error has occurred', error);
        }

        //document.querySelector('.receivedmessage').innerHTML = JSON.stringify(message.body);
    });

    eb.send('tetris.events.info', "Give me game info");
    getAchievementsByPlayerId();
}

function getAchievementsByPlayerId() {
    let session = JSON.parse(sessionStorage.getItem("user_details"));
    let playerid = session["ID"];
    eb.send("tetris.events.getachievementsbyplayerid", {playerid: playerid}, function (error, reply) {
        let JsonData = JSON.parse((reply)["body"]);

        populatestats(JsonData);
    })
}


function populatestats(data) {
    document.getElementById("achievements").innerHTML += "<p>Achievement name: <span>" + data.achievementname + "</span></p>";
    document.getElementById("achievements").innerHTML += "<p>Achievement description: <span>" + data.achievementdescription + "</span></p>";
    document.getElementById("achievements").innerHTML += "<p>Ahievement requirement: <span>" + data.achievementrequirement + "</span></p>";
    document.getElementById("achievements").innerHTML += "<p>Achievement coin reward: <span>" + data.achievementcoinreward + "</span></p>";
    document.getElementById("achievements").innerHTML += "<p>Achievement exp reward: <span>" + data.achievementexpreward + "</span></p>";

}


