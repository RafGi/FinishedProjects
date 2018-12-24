package data;

import java.util.HashMap;

/**
 * Created by bruce on 6/11/2018.
 */
public class SinglePlayerField extends Field {
    private Player player;
    private int level;
    private int linesCleared;
    private int score;
    private int bestScore;


    public SinglePlayerField(Player player,
                             String gameMode,
                             int level,
                             int bestScore,
                             int elapsedTimeSeconds,
                             HashMap<Integer, String> playfield) {
        super(gameMode, elapsedTimeSeconds, playfield);
        this.player = player;
        //todo: replace level by calculations done with player.getexperience
        this.level = level;
        this.linesCleared = 0;
        this.score = 0;
        this.bestScore = bestScore;
    }

    protected int addScore(int amount) {
        return score += amount;
    }

    public void changeGameTime(int gameTime) {
        super.changeGameTime(gameTime);
    }

    public Player getPlayer() {
        return player;
    }

    public int getLevel() {
        return level;
    }

    public int getLinesCleared() {
        return linesCleared;
    }

    public int getScore() {
        return score;
    }

    public int getBestScore() {
        return bestScore;
    }

    public void updateHighScore() {
        if (score > bestScore) {
            bestScore = score;
        }
    }
}
