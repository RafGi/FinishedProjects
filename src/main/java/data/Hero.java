package data;

public class Hero {

    private int id;
    private String name;
    private int reqLevel;
    private double cost;
    private String linkToAvatar;
    private int eraID;

    public Hero(int id, String name, int reqLevel, double cost, String linkToAvatar, int eraID) {

        this.setId(id);
        this.setName(name);
        this.setCost(cost);
        this.setReqLevel(reqLevel);
        this.setLinkToAvatar(linkToAvatar);
        this.setEraID(eraID);
    }


    public Hero(String name, int reqLevel, double cost, String linkToAvatar, int eraID) {

        this.setName(name);
        this.setCost(cost);
        this.setReqLevel(reqLevel);
        this.setLinkToAvatar(linkToAvatar);
        this.setEraID(eraID);
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

    public int getReqLevel() {
        return reqLevel;
    }

    public void setReqLevel(int reqLevel) {
        this.reqLevel = reqLevel;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getLinkToAvatar() {
        return linkToAvatar;
    }

    public void setLinkToAvatar(String linkToAvatar) {
        this.linkToAvatar = linkToAvatar;
    }

    @Override
    public String toString() {
        return "Username :" + this.name + System.lineSeparator()
                + " Cost: " + this.cost + System.lineSeparator()
                + " Required level: " + this.reqLevel + System.lineSeparator()
                + "EraID: " + this.getEraID();
    }

    public int getEraID() {
        return eraID;
    }

    public void setEraID(int eraID) {
        this.eraID = eraID;
    }
}
