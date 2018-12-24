package jdbcinteractor;

import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.ResultSet;
import util.TetrisException;

import java.util.List;

public class HeroHandlers {

    private JDBCClient jdbcClient;

    HeroHandlers(final JDBCClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    void getHeroes(final Message message) {
        System.out.println("connected to db");

        jdbcClient.query(Strings.SELECT_HEROES, ar -> {
            if (ar.succeeded()) {
                if (ar.succeeded()) {
                    ResultSet rs = ar.result();
                    List<JsonArray> res = rs.getResults();
                    JsonArray resAsJson = res.get(0);
                    System.out.println(resAsJson.toString() + " resAsJson");
                    JsonObject resAsJsonObject = new JsonObject();
                    resAsJsonObject.put("test", resAsJson);
                    System.out.println("resAsJsonObject " + resAsJsonObject);

                    //CHECK DELIVERY OPTIONS
                    System.out.println(message.replyAddress() + " address");
                    message.reply(resAsJsonObject);
                } else {
                    throw new TetrisException("Unable to get all heroes from DB");
                }
            }
        });
    }


}
