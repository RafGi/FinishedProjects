package data;

import java.util.HashMap;

/**
 * Created by bruce on 6/11/2018.
 */
public class Field {
    protected String gameMode;
    protected int elapsedTimeSeconds;
    protected HashMap<Integer, String> playfield;

    public Field(String gameMode, int elapsedTimeSeconds, HashMap<Integer, String> playfield) {
        this.gameMode = gameMode;
        this.elapsedTimeSeconds = elapsedTimeSeconds;
        this.playfield = playfield;
    }

    public void createPlayfield() {
        for (int i = 0; i < 200; i++) {
            playfield.put(i, "0");
        }
    }

    public HashMap<Integer, String> getPlayfield() {
        return playfield;
    }

    public void changeGameTime(int gameTime) {
        elapsedTimeSeconds = gameTime;
    }
}
