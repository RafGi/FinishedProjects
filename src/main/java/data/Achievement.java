package data;

public class Achievement {

    private int id;
    private String name;
    private String description;
    private String requirement;
    private int coinReward;
    private double expReward;

    public Achievement(int id, String name, String description, String requirement, int coinReward, double expReward) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.requirement = requirement;
        this.coinReward = coinReward;
        this.expReward = expReward;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public int getCoinReward() {
        return coinReward;
    }

    public void setCoinReward(int coinReward) {
        this.coinReward = coinReward;
    }

    public double getExpReward() {
        return expReward;
    }

    public void setExpReward(double expReward) {
        this.expReward = expReward;
    }
}
