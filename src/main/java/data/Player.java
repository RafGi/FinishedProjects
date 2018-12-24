package data;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private int id;
    private String username;
    private double coins;
    private double experience;
    private String linkToAvatar;
    private List<Integer> friends = new ArrayList<>();
    private List<Integer> equippedHeroes = new ArrayList<>();
    private int clanID;
    private String email;
    private String password;
    private double highscore;

    public Player(int id, String username,
                  double coins,
                  double experience,
                  String linkToAvatar,
                  int clanID,
                  String email,
                  String hashedPassword) {
        this.setId(id);
        this.setUsername(username);
        this.setCoins(coins);
        this.setExpierence(experience);
        this.setLinkToAvatar(linkToAvatar);
        this.clanID = clanID;
        this.setEmail(email);
        this.setPassword(hashedPassword);
    }

    public Player(String username, String password, String email) {
        this.setUsername(username);
        this.setPassword(password);
        this.setEmail(email);
    }

    public Player(int id,
                  String username,
                  double coins,
                  double experience,
                  String email,
                  String password,
                  double highscore) {
        this.setId(id);
        this.setUsername(username);
        this.setCoins(coins);
        this.setExpierence(experience);
        this.setEmail(email);
        this.setPassword(password);
        this.setHighscore(highscore);
    }

    private void setHighscore(double highscore) {
        this.highscore = highscore;
    }

    public double getHighscore() {
        return highscore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getCoins() {
        return coins;
    }

    public void setCoins(double coins) {
        this.coins = coins;
    }

    public double getExpierence() {
        return experience;
    }

    public void setExpierence(double expierence) {
        this.experience = expierence;
    }

    public String getLinkToAvatar() {
        return linkToAvatar;
    }

    public void setLinkToAvatar(String linkToAvatar) {
        this.linkToAvatar = linkToAvatar;
    }


    @Override
    public String toString() {
        return "Username :" + this.username + System.lineSeparator()
                + " Amount of coins: " + this.coins + System.lineSeparator()
                + " Expierence: " + this.experience
                + "pw: " + this.getPassword();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
