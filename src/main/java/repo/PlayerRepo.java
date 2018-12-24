package repo;

import data.Player;

import java.util.List;

public interface PlayerRepo {

    Player getPlayerById(int id);

    Player getPlayerByName(String name);

    List<Player> getAllPlayers();

    Player getPlayerByMail(String email);

    void addPlayer(Player player);

    void deletePlayer(Player player);

    List<Player> getplayersBylevel();

    List<Player> getplayersByhighscore();
}
