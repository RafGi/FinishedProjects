package messagehandlers;

import com.google.gson.Gson;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import org.pmw.tinylog.Logger;
import repo.Repositories;


public class QuestConsumer {

    public <T> void getAllQuestsHandler(Message<T> message) {
        final JsonObject json = (JsonObject) message.body();

        Logger.info("[EVENTBUS] (Quest) received from JSON", json.getString("content"));
        String toSend = new Gson().toJson(Repositories.getQuestRepository().getAllQuests());
        message.reply(toSend);
    }
}
