package webapi;

import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

/**
 * Created by bruce on 6/11/2018.
 */
public class Routes {

    void rootHandler(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        response.setChunked(true);
        response.write("Hello this VertexTest game will be legendary");
        response.end();
    }

    void testHandler(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        response.sendFile("webroot/index.html");
    }

    void chooseGamemodePageHandler(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        response.sendFile("webroot/choose-game.html");
    }

    void toShopHandler(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        response.sendFile("webroot/shop/index.html");
    }

    void toProfileHandler(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        response.sendFile("webroot/profile/index.html");
    }

    void toLeaderboardsHandler(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        response.sendFile("webroot/leaderboards.html");
    }

    void postHandler(RoutingContext routingContext) {
        String post = routingContext.request().getParam("message");
        HttpServerResponse response = routingContext.response();
        System.out.println(post);

        response.end(post);
    }

    void jsonSendHandler(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        JsonObject values = new JsonObject();
        values.put("test1", "groentjes");
        values.put("test2", "thuis");
        JsonArray arr = new JsonArray();
        arr.add(values);
        JsonObject toSend = new JsonObject();
        toSend.put("testJson", arr);
        response.putHeader("content-type", "application/json; charset-utf-8");
        response.end(toSend.encodePrettily());
    }
}
