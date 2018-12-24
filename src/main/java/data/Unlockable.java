package data;

public class Unlockable {

    private int id;
    private String name;
    private int type;
    private String linkToMedia;
    private int cost;

    public Unlockable(int id, String name, int type, String linkToMedia, int cost) {
        this.setId(id);
        this.setName(name);
        this.setType(type);
        this.setLinkToMedia(linkToMedia);
        this.setCost(cost);
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getLinkToMedia() {
        return linkToMedia;
    }

    public void setLinkToMedia(String linkToMedia) {
        this.linkToMedia = linkToMedia;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Name :" + this.name + System.lineSeparator()
                + " Cost: " + this.cost + System.lineSeparator()
                + " Type: " + this.type;
    }
}
