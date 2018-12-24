package messagehandlers;


import com.google.gson.Gson;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import org.pmw.tinylog.Logger;
import repo.Repositories;

public class HeroConsumer {

    public <T> void getAllHeroesHandler(Message<T> message) {
        final JsonObject json = (JsonObject) message.body();

        String toSend = new Gson().toJson(Repositories.getHeroRepository().getHeroes());
        message.reply(toSend);

    }

    public <T> void getHeroByPlayerId(Message<T> message) {
        final JsonObject json = (JsonObject) message.body();
        int playerid = json.getInteger("playerid");

        String toSend = new Gson().toJson(Repositories.getHeroRepository().getHeroesByPlayer(playerid));
        message.reply(toSend);

    }

    private void log(JsonObject json) {
        Logger.info("[EVENTBUS] (Hero) received from JSON", json.getString("content"));
    }
}
