package data;

import java.util.HashMap;
import java.util.List;

/**
 * Created by bruce on 6/11/2018.
 */
public class MultiPlayerField extends Field {
    private List<Player> listOfPlayers;
    private HashMap<Player, Integer> playerLevels;
    private HashMap<Player, Integer> playerLinesCleared;
    private HashMap<Player, Integer> playerScores;

    public MultiPlayerField(String gameMode,
                            int elapsedTimeSeconds,
                            HashMap<Integer, String> playfield,
                            List<Player> listOfPlayers) {
        super(gameMode, elapsedTimeSeconds, playfield);
        this.listOfPlayers = listOfPlayers;
    }

    //You'll get a list of players from frontend that are converted into player objects
    public void initiateField() {
        for (Player player : listOfPlayers) {
            playerLevels.put(player, 0);
            playerLinesCleared.put(player, 0);
            playerScores.put(player, 0);
        }
    }

    public List<Player> getListOfPlayers() {
        return listOfPlayers;
    }

    public HashMap<Player, Integer> getPlayerLevels() {
        return playerLevels;
    }

    public HashMap<Player, Integer> getPlayerLinesCleared() {
        return playerLinesCleared;
    }

    public HashMap<Player, Integer> getPlayerScores() {
        return playerScores;
    }

}
