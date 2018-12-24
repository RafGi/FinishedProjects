"use strict";

const eb = new EventBus("/tetris-28/socket/");

$(function () {
    $(".gamemode").on("click", selectGamemode);
    eb.onopen = handleServerMessages;
});

function selectGamemode(e) {
    e.preventDefault();
    sessionStorage.setItem("gamemode", this.getAttribute("id"));
    top.location.replace("/static/playgame.html");
}

function handleServerMessages() {
    eb.send('tetris.events.info', "Give me game info");
    getAllQuests();

}

function getAllQuests() {
    eb.send("tetris.events.getallquests", {content: "requesting all the players"}, function (error, reply) {
        let jsonData = JSON.parse((reply)["body"]);

        populateQuests(jsonData);
    });
}

function populateQuests(jsonData) {
    let html = document.getElementById("quests");
    for (let key in jsonData) {
        if(jsonData.hasOwnProperty(key)) {
        let questname = jsonData[key].name;
        let description = jsonData[key].description;
        let experience = jsonData[key].questrewardexperience;

                html.innerHTML+= "<li><p><span>" + questname + "</span><span class='completion'>completed</span></p>" +
                "<p><span>" + description + "</span><span class='completion'>" + experience + " exp</span></p>" +
                "<div class='progress-bar'></div></li>";

        }
    }
}