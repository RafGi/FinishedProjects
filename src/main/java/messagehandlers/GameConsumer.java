package messagehandlers;

import com.google.gson.Gson;
import data.Game;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import org.pmw.tinylog.Logger;

public class GameConsumer {
    private Game g;
    private Game g2;

    public <T> void handler(Message<T> message) {
        final JsonObject json = (JsonObject) message.body();

        Logger.info("[EVENTBUS] (Player) received from JSON", json.getString("content"));
        message.reply("Player message well received");
    }


    public <T> void startGame(Message<T> message) {
        g = new Game();
        g2 = new Game();
        final JsonObject json = (JsonObject) message.body();
        Logger.info("[EVENTBUS] Game started", json.getString("content"));
        String toSend = new Gson().toJson(g.getMap());
        message.reply(toSend);
    }


    public <T> void getGame(Message<T> message) {
        final JsonObject json = (JsonObject) message.body();
        String toSend = new Gson().toJson(g.getMap());
        message.reply(toSend);
    }

    public <T> void getGame2(Message<T> message) {
        final JsonObject json = (JsonObject) message.body();
        String toSend = new Gson().toJson(g2.getMap());
        message.reply(toSend);
    }

    public <T> void getScore(Message<T> message) {
        final JsonObject json = (JsonObject) message.body();
        String toSend = new Gson().toJson(g.getScore());
        message.reply(toSend);
    }

    public <T> void getScore2(Message<T> message) {
        final JsonObject json = (JsonObject) message.body();
        String toSend = new Gson().toJson(g2.getScore());
        message.reply(toSend);
    }

    public <T> void userInput(Message<T> message) {
        final JsonObject json = (JsonObject) message.body();
        String input;
        try {
            input = (json.getInteger("content")).toString();
        } catch (Exception e) {
            input = "$";
        }
        switch (input) {
            case "37":
                g2.moveLeft();
                break;
            case "38":
                g2.turn();
                break;
            case "39":
                g2.moveRight();
                break;
            case "40":
                g2.moveDown();
                break;
            case "80":
                //heropower game 2
                if (g2.getTimezone() == 1 && g2.readyForPower()) {
                    g.turn();
                }
                g2.usePower();
                break;

            case "81":
                g.moveLeft();
                break;
            case "90":
                g.turn();
                break;
            case "68":
                g.moveRight();
                break;
            case "83":
                g.moveDown();
                break;
            case "70":
                //hero power game 1
                if (g.getTimezone() == 1 && g.readyForPower()) {
                    g2.turn();
                }
                g.usePower();
                break;
            default:
                break;
        }
    }

    public <T> void getGameover(Message<T> message) {
        final JsonObject json = (JsonObject) message.body();
        String toSend;
        if (g.getGameOver() && g2.getGameOver()) {
            toSend = new Gson().toJson(true);
        } else {
            toSend = new Gson().toJson(false);
        }

        message.reply(toSend);
    }

    public <T> void getCooldown(Message<T> message) {
        String toSend;
        toSend = new Gson().toJson(g.getCooldown());
        message.reply(toSend);
    }

    public <T> void getCooldown2(Message<T> message) {
        String toSend;
        toSend = new Gson().toJson(g2.getCooldown());
        message.reply(toSend);
    }

    public <T> void getTimezone(Message<T> message) {
        String toSend;
        toSend = new Gson().toJson(g.getTimezone());
        message.reply(toSend);
    }

    public <T> void getTimezone2(Message<T> message) {
        String toSend;
        toSend = new Gson().toJson(g2.getTimezone());
        message.reply(toSend);
    }
}
