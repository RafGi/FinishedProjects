package data;

public class Stat {
    private int playerId;
    private int gamesPlayed;
    private int wins;
    private int highscore;
    private int amountOfLines;
    private int amountOfTetris;
    private int winStreak;

    public Stat(int playerId,
                int gamesPlayed,
                int wins,
                int highscore,
                int amountOfLines,
                int amountOfTetris,
                int winStreak) {
        this.playerId = playerId;
        this.gamesPlayed = gamesPlayed;
        this.wins = wins;
        this.highscore = highscore;
        this.amountOfLines = amountOfLines;
        this.amountOfTetris = amountOfTetris;
        this.winStreak = winStreak;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    public int getAmountOfLines() {
        return amountOfLines;
    }

    public void setAmountOfLines(int amountOfLines) {
        this.amountOfLines = amountOfLines;
    }

    public int getAmountOfTetris() {
        return amountOfTetris;
    }

    public void setAmountOfTetris(int amountOfTetris) {
        this.amountOfTetris = amountOfTetris;
    }

    public int getWinStreak() {
        return winStreak;
    }

    public void setWinStreak(int winStreak) {
        this.winStreak = winStreak;
    }
}
