package data;

public class Event {

    private int id;
    private String name;
    private String effect;
    private double cost;

    public Event(int id, String name, String effect, double cost) {
        this.setId(id);
        this.setName(name);
        this.setEffect(effect);
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

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "name: " + this.name + System.lineSeparator()
                + "effect: " + this.effect + System.lineSeparator()
                + "cost: " + this.cost;
    }
}
