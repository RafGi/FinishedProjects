/* exported sendMessage */
/* global EventBus, document, console, messagelist */
/*jshint globalstrict: true*/
/* jshint esnext: true */
"use strict";
let eb = new EventBus("/tetris-28/socket/");

document.addEventListener('DOMContentLoaded',init);



function init() {
    eb.onopen = handleServerMessages;
    $("#login").on('click',initlogin);
    $("#register").on('click',initRegister);
}

function initlogin(e) {
    e.preventDefault();
    eb.onopen = loginPlayer();
}

function initRegister(e) {
    e.preventDefault();
    eb.onopen = registerPlayer();
}

function initGame(e) {
    e.preventDefault();
    eb.onopen = startGame();
}

function handleServerMessages() {

    eb.registerHandler('tetris.events.gameinfo', function (error, message) {
        if (error) {
        }


        //document.querySelector('.receivedmessage').innerHTML = JSON.stringify(message.body);
    });

    eb.send('tetris.events.info', "Give me game info");
    sendPlayerMessage();
    //getAllPlayers();
    startGame();
}

function sendPlayerMessage() {
    eb.send("tetris.events.isloggedin",{body: sessionStorage.getItem("username")},function(error,reply){
        if(reply["body"] === "not logged in" && top.location.pathname !== "/static/login.html"){
            window.location.replace("/static/login.html");
        }
    })

}

function getAllPlayers() {
    eb.send("tetris.events.getallplayers", {content: "requesting all the players"}, function (error, reply) {
        let JsonData = JSON.parse((reply)["body"]);
        //document.getElementById("username").innerHTML = JsonData[0]["username"]
    })
}

function loginPlayer() {
    const username = document.getElementById("login_username").value;
    const pwd = document.getElementById("login_password").value;
    eb.send("tetris.events.loginplayer", {username: username, password: pwd}, (error, reply) => {
        //let JsonData=JSON.parse(reply["body"]);
        const response = reply["body"];
        if(response==="OK"){
            sessionStorage.setItem("username",username);
            window.location.replace("/static/index.html");
        }else{
            $("#loginfail").html("Failed to login");
        }
    })
}

function registerPlayer() {
    const username = document.getElementById("reg_username").value;
    const email = document.getElementById("mail").value;
    const password = document.getElementById("reg_password").value;
    const pwCheck = document.getElementById("password_rep").value;

    if (password == pwCheck) {
        eb.send('tetris.events.registerplayer',{username:username,email:email,password:password,pwcheck:pwCheck},(error,reply) =>{
            const response = reply["body"];
            if (response === "OK") {
                //go back to login
            } else {
                //failed login
            }
        })
    }
}

function drawGame(hashmap) {

    $.each(hashmap,function(index,value){
        var x = index;
        var y = 0;
        while (x >= 10){
            x -= 10;
            y++;
        }
        drawSquare(x, y, value);
    });

}

function drawGame2(hashmap) {

    $.each(hashmap,function(index,value){
        var x = index;
        var y = 0;
        while (x >= 10){
            x -= 10;
            y++;
        }
        drawSquare2(x, y, value);
    });

}

function drawSquare2(x, y, value){
    var canvas = document.querySelector("#player_two .field");
    var ctx = canvas.getContext("2d");
    ctx.fillStyle = "transparent";
    ctx.clearRect(x*20, y*20, 20, 20);
    ctx.lineWidth = 1;
    ctx.strokeStyle = "black";
    ctx.strokeRect(x*20, y*20, 20, 20);
    ctx.fillStyle= value;
    ctx.fillRect(x*20 + 1 , y*20 + 1, 18, 18);


}

function drawSquare(x, y, value){
    var canvas = document.querySelector("#player_one .field");
    var ctx = canvas.getContext("2d");
    ctx.fillStyle = "transparent";
    ctx.clearRect(x*20, y*20, 20, 20);
    ctx.lineWidth = 1;
    ctx.strokeStyle = "black";
    ctx.strokeRect(x*20, y*20, 20, 20);
    ctx.fillStyle= value;
    ctx.fillRect(x*20 + 1 , y*20 + 1, 18, 18);


}
let intervalClearValue1;
let intervalClearValue2;
function startGame() {
    if (top.location.pathname == "/static/game.html") {
        eb.send('tetris.events.startgame', {content: "1"}, (error, reply) => {
            const response = reply["body"];
            let hashmap = JSON.parse((reply)["body"]);

            drawGame(hashmap);
            intervalClearValue1 = setInterval(getGame, 75);
            intervalClearValue2 = setInterval(getGame2, 75);
        })
    }
}
function gameOver(bool){
    if (bool){
        clearInterval(intervalClearValue1);
        clearInterval(intervalClearValue2);
        $('.game_over').fadeIn();
//        document.querySelector("#score_player_one").innerHTML += "score player one " + scorePlayerOne;
//        document.querySelector("#score_player_two").innerHTML += "score player two " + scorePlayerTwo;


        eb.close();
    }
}
function getGame() {
    if (top.location.pathname == "/static/game.html") {
        eb.send('tetris.events.getgame', {content: "1"}, (error, reply) => {
            const response = reply["body"];
            let hashmap = JSON.parse((reply)["body"]);
            drawGame(hashmap);
            getScore();
            getCooldown();
            getTimezone();
            getGameover();
        })
    }
}

function getGame2() {
    if (top.location.pathname == "/static/game.html") {
        eb.send('tetris.events.getgame2', {content: "1"}, (error, reply) => {
            const response = reply["body"];
            let hashmap = JSON.parse((reply)["body"]);
            drawGame2(hashmap);
            getScore2();
            getCooldown2();
            getTimezone2();
            getGameover();
        })
    }
}

function getGameover() {
    if (top.location.pathname == "/static/game.html") {
        eb.send('tetris.events.getgameover', {content: "1"}, (error, reply) => {
            const response = reply["body"];
            let bool = JSON.parse((reply)["body"]);
            gameOver(bool);
        })
    }
}

function getScore() {
    if (top.location.pathname == "/static/game.html") {
        eb.send('tetris.events.getscore', {content: "1"}, (error, reply) => {
            const reponse = reply["body"];
            let score = JSON.parse((reply)["body"]);
            setScore(score);
        })
    }
}

function getScore2() {
    if (top.location.pathname == "/static/game.html") {
        eb.send('tetris.events.getscore2', {content: "1"}, (error, reply) => {
            const reponse = reply["body"];
            let score = JSON.parse((reply)["body"]);
            setScore2(score);
        })
    }
}

function getCooldown(){
    if (top.location.pathname == "/static/game.html") {
        eb.send('tetris.events.getcooldown', {content: "1"}, (error, reply) => {
            const reponse = reply["body"];
            let cooldown = JSON.parse((reply)["body"]);
            setCooldown(cooldown);
        })
    }
}

function getCooldown2(){
    if (top.location.pathname == "/static/game.html") {
        eb.send('tetris.events.getcooldown2', {content: "1"}, (error, reply) => {
            const reponse = reply["body"];
            let cooldown = JSON.parse((reply)["body"]);
            setCooldown2(cooldown);
        })
    }
}

function getTimezone(){
    if (top.location.pathname == "/static/game.html") {
        eb.send('tetris.events.gettimezone', {content: "1"}, (error, reply) => {
            const reponse = reply["body"];
            let timezone = JSON.parse((reply)["body"]);
            setTimezone(timezone);
        })
    }
}

function getTimezone2(){
    if (top.location.pathname == "/static/game.html") {
        eb.send('tetris.events.gettimezone2', {content: "1"}, (error, reply) => {
            const reponse = reply["body"];
            let timezone = JSON.parse((reply)["body"]);
            setTimezone2(timezone);
        })
    }
}

function setTimezone(timezone){
    if (timezone === 1){
        document.querySelector("#player_one .hero img").src = "./assets/img/pharao.png";
        document.querySelector("#player_one .hero_attack img").src = "./assets/img/pharao_weapon.png";
        //document.querySelector("#player_one").style.background = "url('./assets/img/egyptBackground.jpg')";
    } else if (timezone === 2){
        document.querySelector("#player_one .hero img").src = "./assets/img/blacksmith.png";
        document.querySelector("#player_one .hero_attack img").src = "./assets/img/blacksmith_weapon.png";
        document.querySelector("#player_one").style.background = "url('./assets/img/medievalBackground.jpg')";
    } else if (timezone === 3){
        document.querySelector("#player_one .hero img").src = "./assets/img/cyborg.png";
        document.querySelector("#player_one .hero_attack img").src = "./assets/img/ai.png";
        document.querySelector("#player_one").style.background = "url('./assets/img/futureBackground.jpeg')";
    }
}

function setTimezone2(timezone){
    if (timezone === 1){
        document.querySelector("#player_two .hero img").src = "./assets/img/pharao.png";
        document.querySelector("#player_two .hero_attack img").src = "./assets/img/pharao_weapon.png";
        //document.querySelector("#player_two").style.background = "url('./assets/img/egyptBackground.jpg')";
    } else if (timezone === 2){
        document.querySelector("#player_two .hero img").src = "./assets/img/blacksmith.png";
        document.querySelector("#player_two .hero_attack img").src = "./assets/img/blacksmith_weapon.png";
        document.querySelector("#player_two").style.background = "url('./assets/img/medievalBackground.jpg')";
    }else if (timezone === 3){
        document.querySelector("#player_two .hero img").src = "./assets/img/cyborg.png";
        document.querySelector("#player_two .hero_attack img").src = "./assets/img/ai.png";
        document.querySelector("#player_two").style.background = "url('./assets/img/futureBackground.jpeg')";
    }
}



function setCooldown(cooldown){
    if (cooldown === 0){
        document.querySelector("#player_one .heropowertext").innerHTML = "[F]"
    } else {
        document.querySelector("#player_one .heropowertext").innerHTML = cooldown;
    }
}

function setCooldown2(cooldown){
    if (cooldown === 0){
        document.querySelector("#player_two .heropowertext").innerHTML = "[P]"
    } else {
        document.querySelector("#player_two .heropowertext").innerHTML = cooldown;
    }
}

function setScore(score){
    document.querySelector("#player_one .score").innerHTML = score;
    if (score < 250){
        document.querySelector("#player_one .scoreToEvolve").innerHTML = "Evolution at 250 points.";
    } else if (score < 600){
        document.querySelector("#player_one .scoreToEvolve").innerHTML = "Evolution at 600 points.";
    }else {
        document.querySelector("#player_one .scoreToEvolve").innerHTML = "";
    }
}

function setScore2(score){
    document.querySelector("#player_two .score").innerHTML = score;
    if (score < 250){
        document.querySelector("#player_two .scoreToEvolve").innerHTML = "Evolution at 250 points.";
    } else if (score < 600){
        document.querySelector("#player_one .scoreToEvolve").innerHTML = "Evolution at 600 points.";
    } else {
        document.querySelector("#player_one .scoreToEvolve").innerHTML = "";
    }
}

function userInput(keycode){
    if (top.location.pathname == "/static/game.html") {
        eb.send('tetris.events.userinput', {content: keycode}, (error, reply) => {
        })
        getGame();
        getGame2();
    }
}




