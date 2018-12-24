package repo;

import data.Player;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class MySqlPlayerRepoTest {

    @Test
    void getPlayerById() {
        Player player = Repositories.getPlayerRepository().getPlayerById(2);
        System.out.println(player);
        assertEquals(2, player.getID(), "The id must be 2");
    }

    @Test
    void getPlayerByName() {
        Player player = Repositories.getPlayerRepository().getPlayerByName("test");
        System.out.println(player);
        assertEquals("test", player.getUsername(), "Username must be test");
    }

    @Test
    void getAllPlayers() {
        List<Player> players = Repositories.getPlayerRepository().getAllPlayers();
        System.out.println(players.toString());
        assertEquals(2, players.get(0).getID(), "ID must be 2");
    }

    @Test
    void addPlayer() {
        Player player = new Player("test2", "", "test@test");
        Repositories.getPlayerRepository().addPlayer(player, "testeroo");
        List<Player> allPlayers = Repositories.getPlayerRepository().getAllPlayers();
        assertEquals("test2", allPlayers.get(allPlayers.size() - 1).getUsername(), "Username must be test2");
    }

    @Test
    void deletePlayer() {
        Player player = new Player("test3", "", "test2@test");
        Repositories.getPlayerRepository().addPlayer(player, "testeroo");
        Player newPlayer = Repositories.getPlayerRepository().getPlayerByName("test3");
        List<Player> WithNewPlayer = Repositories.getPlayerRepository().getAllPlayers();
        System.out.println(WithNewPlayer);
        Repositories.getPlayerRepository().deletePlayer(newPlayer);
        List<Player> DeletedPlayer = Repositories.getPlayerRepository().getAllPlayers();

        assertNotEquals(WithNewPlayer.size(), DeletedPlayer.size(), "Lists must not be the same");
    }
}