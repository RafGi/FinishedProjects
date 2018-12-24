package data;

/**
 * Created by bruce on 6/11/2018.
 */
public class HeroPower implements HeroPowerEffect {
    private int heroPowerID;
    private String name;
    private double cooldown;
    private boolean isOffensive;
    private String effect;

    public HeroPower(int heroPowerID, String name, double cooldown, boolean isOffensive, String effect) {
        this.heroPowerID = heroPowerID;
        this.name = name;
        this.cooldown = cooldown;
        this.isOffensive = isOffensive;
        this.effect = effect;
    }

    public int getHeroPowerID() {
        return heroPowerID;
    }

    public String getName() {
        return name;
    }

    public double getCooldown() {
        return cooldown;
    }

    public boolean isOffensive() {
        return isOffensive;
    }

    public String getEffect() {
        return effect;
    }


    @Override
    public void offensive() {

    }

    @Override
    public void defensive() {

    }
}

