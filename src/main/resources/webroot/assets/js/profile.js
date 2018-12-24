"use strict";

//let eb = new EventBus("/tetris-28/socket/");

document.addEventListener("DOMContentLoaded", init);

function init() {
//    selected heroes accentueren
//    selectedHeroes();


    eb.onopen = handleServerMessages;
}

function handleServerMessages(){
    eb.registerHandler('tetris.events.gameinfo', function(error,message){
        if(error){
            console.log('An error has occurred', error);
        }
    });

    eb.send('tetris.events.info',"Give me game info");
    getAllHeroes(populateHeroes);
    getHeroesByPlayerId(populateAvailableHeroes);

}

function populateHeroes(data) {
    let allHeroes = getAllHeroes();
    let playerHeroes = getHeroesByPlayerId();
    let eras = [egypt, japan, crusades, future];

    for(let key in data) {
        if(data.hasOwnProperty(key)) {
        let eraid = data[key].eraID;
        let html = eras[eraid];
        html.innerHTML +=
        '<section>' +
        '<h3>' + data[key].name +'</h3>'+
            '<figure id="'+ data[key].name +'"><img src=' + data[key].linkToAvatar + ' alt="'+ data[key].name +'" title="'+ data[key].name +'" /></figure>'+
        '</section>';
        }


    }

    selectedHeroes();

}


function getAllHeroes(callback) {
    eb.send("tetris.events.getallheroes",{content: "requesting all the heroes"},function(error,reply){

        callback(JSON.parse(reply["body"]));
        return JSON.parse(reply["body"]);
    })

}

function getHeroesByPlayerId(callback) {
        const session = JSON.parse(sessionStorage.getItem("user_details"));
        const playerid = session["id"];

        eb.send("tetris.events.heroesbyplayerid",{playerid:playerid},function(error,reply) {
            let JsonData=JSON.parse((reply)["body"]);

            callback(JsonData);
        })
}


function selectedHeroes() {
    document.querySelector("main").querySelectorAll("figure").forEach(function (item) {
        item.classList.remove("selected");
    });
//    document.getElementById("alien").classList.add("selected");
//    document.getElementById("crusader").classList.add("selected");
//    document.getElementById("sumo").classList.add("selected");
//    document.getElementById("warrior").classList.add("selected");

    selectHero();
}

function chooseHero(e) {
    e.preventDefault();
    if ((e.target.parentElement.className) == "buy") {
    } else {
        e.target.parentElement.parentElement.parentElement.querySelectorAll("figure").forEach(function (item) {item.classList.remove("selected")});
        e.target.parentElement.classList.add("selected");
        let era = e.target.parentElement.parentElement.parentElement.id;
        let hero = e.target.parentElement.id;

        sessionStorage.setItem(era, hero);
    }

}

function selectHero() {
    document.querySelector("main").querySelectorAll("figure").forEach(function (item) {
        item.addEventListener("click", chooseHero);

    });
}


function populateAvailableHeroes(data) {
        for(let key in data) {
            if(data.hasOwnProperty(key)) {
            let heroName = data[key].name;
            document.getElementById(heroName).classList.add("buy");
            }

     }
}