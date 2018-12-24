package messagehandlers;

import com.google.gson.Gson;
import data.Player;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import org.pmw.tinylog.Logger;
import repo.Repositories;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class PlayerConsumer {
    public <T> void handler(Message<T> message) {
        final JsonObject json = (JsonObject) message.body();
        String body = json.getString("body");
        Logger.info("[EVENTBUS] (Player) received from JSON " + body);
        try {
            if (!body.isEmpty()) {

                Player player = Repositories.getPlayerRepository().getPlayerByName(body);
                if (player.getUsername().equals(body)) {
                    message.reply("OK");
                } else {
                    message.reply("not logged in");
                }
            }
        } catch (NullPointerException ex) {
            message.reply("not logged in");
        }
    }

    public <T> void getAllPlayersHandler(Message<T> message) {
        final JsonObject json = (JsonObject) message.body();

        Logger.info("[EVENTBUS] (Player) received from JSON", json.getString("content"));
        String toSend = new Gson().toJson(Repositories.getPlayerRepository().getAllPlayers());
        message.reply(toSend);

    }

    public <T> void getPlayerByNameHandler(Message<T> message) {
        final JsonObject json = (JsonObject) message.body();

        Logger.info("[EVENTBUS] (Player) received from JSON", json.getString("username"));
        String playerName = json.getString("username");


        String toSend = new Gson().toJson(Repositories.getPlayerRepository().getPlayerByName(playerName));
        message.reply(toSend);

    }

    public <T> void login(Message<T> message) {
        final JsonObject json = (JsonObject) message.body();
        System.out.println("username:" + json.getString("username"));
        System.out.println("password:" + json.getString("password"));
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] pwd = json.getString("password").getBytes(StandardCharsets.UTF_8);
            byte[] digest = messageDigest.digest(pwd);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < digest.length; i++) {
                sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
            }
            String ShaPWD = sb.toString();
            String usernameInput = json.getString("username");
            System.out.println("sha256: " + ShaPWD);

            //Comparing frontend to database value
            Player player = Repositories.getPlayerRepository().getPlayerByName(usernameInput);
            if (player.getUsername().equals(usernameInput) && player.getPassword().toLowerCase().equals(ShaPWD.toLowerCase())) {
                message.reply("OK");
            } else {
                message.reply("FAIL");
            }

        } catch (NoSuchAlgorithmException | NullPointerException e) {
            message.reply("FAIL");
        }
    }

    public <T> void register(Message<T> message) {
        final JsonObject json = (JsonObject) message.body();
        String username = json.getString("username");
        String email = json.getString("email");
        MessageDigest messageDigest = null;
        System.out.println(json.getString("password"));
        if (validateRegister(json)) {
            try {

                messageDigest = MessageDigest.getInstance("SHA-256");
                byte[] pwd = json.getString("password").getBytes(StandardCharsets.UTF_8);
                byte[] digest = messageDigest.digest(pwd);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < digest.length; i++) {
                    sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
                }
                String shaPWD = sb.toString();
                Player player = new Player(username, shaPWD, email);
                Repositories.getPlayerRepository().addPlayer(player);
                message.reply("OK");
            } catch (NoSuchAlgorithmException e) {
                message.reply("Failed to register");
            }

        } else {
            message.reply("Failed to register");
        }
    }

    private boolean validateRegister(JsonObject json) {

        String username = json.getString("username");
        String pwd = json.getString("password");
        String pwdCheck = json.getString("email");
        String email = json.getString("pwcheck");
        boolean validated = false;
        System.out.println("[REGISTER VALIDATION] username " + json.getString("username"));
        System.out.println("[REGISTER VALIDATION] password " + json.getString("password"));
        System.out.println("[REGISTER VALIDATION] email " + json.getString("email"));
        System.out.println("[REGISTER VALIDATION] pwcheck " + json.getString("pwcheck"));

        if (!username.isEmpty()) {
            if (!email.isEmpty()) {
                if (!pwd.isEmpty()) {
                    if (!pwdCheck.isEmpty()) {
                        List<Player> players = Repositories.getPlayerRepository().getAllPlayers();
                        for (Player p : players) {
                            if (!p.getUsername().trim().contains(username) || !p.getEmail().trim().contains(email)) {
                                validated = true;
                            }
                        }

                    }
                }
            }
        }
        return validated;
    }


//    private boolean allowed(String username, String email) {
//        System.out.println("in de allowed function");
//        String uDB = Repositories.getPlayerRepository().getPlayerByName(username).getUsername();
//        String mDB = Repositories.getPlayerRepository().getPlayerByMail(email).getEmail();
//        System.out.println(uDB + mDB);
//        if (uDB.isEmpty() && mDB.isEmpty()) {
//            return true;
//        }
//        return false;
//    }

    //Profile

    public <T> void getStatsByplayerId(Message<T> message) {
        final JsonObject json = (JsonObject) message.body();

        Logger.info("[EVENTBUS] (Stats) received from JSON", json.getInteger("playerid"));
        int playerid = json.getInteger("playerid");
        System.out.println(playerid);


        String toSend = new Gson().toJson(Repositories.getStatRepository().getStatsByPlayerId(playerid));
        message.reply(toSend);

    }

    public <T> void getAchievementsByplayerId(Message<T> message) {
        final JsonObject json = (JsonObject) message.body();

        Logger.info("[EVENTBUS] (Achievements) received from JSON", json.getInteger("playerid"));
        int playerid = json.getInteger("playerid");
        System.out.println(playerid);


        String toSend = new Gson().toJson(Repositories.getAchievementRepository().getAchievementsByPlayerId(playerid));
        message.reply(toSend);

    }

    public <T> void getplayersBylevel(Message<T> message) {
        final JsonObject json = (JsonObject) message.body();

        Logger.info("[EVENTBUS] (Players) received from JSON", json.getString("content"));
        String toSend = new Gson().toJson(Repositories.getPlayerRepository().getplayersBylevel());
        message.reply(toSend);

    }

    public <T> void getplayersByhighscore(Message<T> message) {
        final JsonObject json = (JsonObject) message.body();

        Logger.info("[EVENTBUS] (Players) received from JSON", json.getString("content"));
        String toSend = new Gson().toJson(Repositories.getPlayerRepository().getplayersByhighscore());
        message.reply(toSend);
    }
}
