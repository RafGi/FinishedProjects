"use strict";

let eb = new EventBus("/tetris-28/socket/");

document.addEventListener("DOMContentLoaded", init);

function init() {
    //document.getElementById("playersbyhighscore").innerHTML+= "<p><em>1.</em><span id='username'>username 1</span> Highscore: <em>1000010000</em></p>"
    //document.getElementById("playersbylvl").innerHTML+= "<p><em>1.</em><span id='username'>username 1</span> Level: <em>1000</em></p>"
    console.log('listening to messages');

    eb.onopen = handleServerMessages;
}


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
    getplayersByhighscore();
    getplayersBylevel();

}

function getplayersByhighscore() {
    console.log('[EVENTBUS] (players) getting playersByhighscore');
    eb.send("tetris.events.getplayersByhighscore",{content: "requesting all the players"}, function (error, reply) {
        let JsonData = JSON.parse((reply)["body"]);
        console.log(JsonData);

        populateHighscore(JsonData);
    })
}

function getplayersBylevel() {
    console.log('[EVENTBUS] (players) getting playersBylevel');
    eb.send("tetris.events.getplayersBylevel",{content: "requesting all the players"}, function (error, reply) {
        let JsonData = JSON.parse((reply)["body"]);
        console.log(JsonData);

        populateLevel(JsonData);
    })
}


function populateHighscore(data) {
    for (let i=0;i < Object.keys(data).length;i++){
        document.getElementById("playersbyhighscore").innerHTML += "<p><em>" + (i+1) + "</em><span id='username'>" + data[i]["username"] + "</span> Highscore: <em>" + data[i]["highscore"] + "</em></p>";
    }
}

function populateLevel(data) {
    for (let i=0;i < Object.keys(data).length;i++) {
        document.getElementById("playersbylvl").innerHTML += "<p><em>" + (i+1) + "</em><span id='username'>" + data[i]["username"] + "</span> Level: <em>" + data[i]["experience"] + "</em></p>";
    }
}