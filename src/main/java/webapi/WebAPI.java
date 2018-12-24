package webapi;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import jdbcinteractor.JDBCInteractor;
import jdbcinteractor.TetrisRepository;
import messagehandlers.GameConsumer;
import messagehandlers.HeroConsumer;
import messagehandlers.PlayerConsumer;
import messagehandlers.QuestConsumer;
import org.pmw.tinylog.Logger;


/**
 * Created by bruce on 6/11/2018.
 */
public class WebAPI extends AbstractVerticle {

    @Override
    public void start() {
        // JVD: start webserver
        this.initWebserver();

        // JVD: start database
        this.initDB();

        // JVD: Start event bus
        this.initEventBus();

    }

    @Override
    public void stop() {
        //dbServer.stop();
        //webDB.stop();
    }


    private void initWebserver() {
        Logger.info("Deploying Verticle Tetris Webserver");
        final HttpServer server = vertx.createHttpServer();
        final Router router = Router.router(vertx);

        // JVD: afhandelen als mensen naar de root surfen
        router.route("/").handler(routingContext -> {
            // JVD: je hebt een response object nodig om "iets" terug te sturen naar de client die connecteert
            final HttpServerResponse response = routingContext.response();
            response.setChunked(true);
            response.write("Hands off the root");
            response.end();
        });

        //JVD: Handle static files -> zorgt ervoor dat je kan surfen naar localhost:eenpoort/ en dat
        // [RULE]: Statuc url MOET "/static/*" zijn
        router.route("/static/*").handler(StaticHandler.create());

        // JVD: socket server opzetten tetris.events.anything waarvan minstens 1 char
        // [RULE]: Socket url MOET tetris-xx/socket/ zijn (xx is groepsnummer met leading zero) zijn
        router.route("/tetris-28/socket/*").handler(new TetrisSockJSHandler(vertx).create("tetris\\.events\\..+"));

        //JVD: effectief opstarten van de server
        // [RULE]: poort MOET 80xx (xx is groepsnummer met leading zero) zijn
        server.requestHandler(router::accept).listen(8028);

    }

    private void initDB() {
        new JDBCInteractor().startDBServer();
        TetrisRepository.populateDB();
    }

    private void initEventBus() {
        final EventBus eb = vertx.eventBus();
        Logger.info("[EVENTBUS] Listening to messages");
        //JVD: Dit stuk is illustratief, optioneel om jullie op weg te helpen.
        // Uiteraard kan je dit uit elkaar trekken in verschillende klasses
        final PlayerConsumer playerConsumer = new PlayerConsumer();
        final GameConsumer gameConsumer = new GameConsumer();
        final HeroConsumer heroConsumer = new HeroConsumer();
        final QuestConsumer questConsumer = new QuestConsumer();

        //JVD: kritieke systemen in een klasse abstrageren
        eb.consumer("tetris.events.isloggedin", playerConsumer::handler);

        eb.consumer("tetris.events.getallplayers", playerConsumer::getAllPlayersHandler);

        //JVD: Niet kritieke zaken met een lambda functie aghandelen
        eb.consumer("tetris.events.info", message -> {
            eb.publish("tetris.events.gameinfo", "The game is running");
            message.reply("OK");
            Logger.info("[EVENTBUS] (Info) message received in info ", message.body());

        });

        eb.consumer("tetris.events.loginplayer", playerConsumer::login);

        eb.consumer("tetris.events.registerplayer", playerConsumer::register);

        eb.consumer("tetris.events.startgame", gameConsumer::startGame);

        eb.consumer("tetris.events.getplayerbyname", playerConsumer::getPlayerByNameHandler);

        eb.consumer("tetris.events.getstatsbyplayerid", playerConsumer::getStatsByplayerId);


        eb.consumer("tetris.events.getgame", gameConsumer::getGame);
        eb.consumer("tetris.events.getgame2", gameConsumer::getGame2);

        eb.consumer("tetris.events.getachievementsbyplayerid", playerConsumer::getAchievementsByplayerId);
        eb.consumer("tetris.events.getscore2", gameConsumer::getScore2);
        eb.consumer("tetris.events.getscore", gameConsumer::getScore);

        eb.consumer("tetris.events.getplayersBylevel", playerConsumer::getplayersBylevel);
        eb.consumer("tetris.events.getcooldown2", gameConsumer::getCooldown2);
        eb.consumer("tetris.events.getcooldown", gameConsumer::getCooldown);

        eb.consumer("tetris.events.getplayersByhighscore", playerConsumer::getplayersByhighscore);
        eb.consumer("tetris.events.gettimezone2", gameConsumer::getTimezone2);
        eb.consumer("tetris.events.gettimezone", gameConsumer::getTimezone);

        //HeroConsumer
        eb.consumer("tetris.events.getallheroes", heroConsumer::getAllHeroesHandler);
        eb.consumer("tetris.events.heroesbyplayerid", heroConsumer::getHeroByPlayerId);

        eb.consumer("tetris.events.getgameover", gameConsumer::getGameover);

        eb.consumer("tetris.events.userinput", gameConsumer::userInput);

        //QuestConsumer
        eb.consumer("tetris.events.getallquests", questConsumer::getAllQuestsHandler);
    }
}
