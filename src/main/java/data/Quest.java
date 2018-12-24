package data;

/**
 * Created by bruce on 6/11/2018.
 */
public class Quest {
    private int questID;
    private String name;
    private String description;
    private String requirement;
    private String rewardtype;
    private int questDifficulty;
    private double questrewardexperience;

    public Quest(int questID, String name, String description, String requirement, double questrewardexperience) {
        this.questID = questID;
        this.name = name;
        this.description = description;
        this.requirement = requirement;
        this.questrewardexperience = questrewardexperience;
    }

    public Quest(int questID,
                 String name,
                 String description,
                 String requirement,
                 String rewardtype,
                 int questDifficulty) {
        this.questID = questID;
        this.name = name;
        this.description = description;
        this.requirement = requirement;
        this.rewardtype = rewardtype;
        this.questDifficulty = questDifficulty;
    }

    public int getQuestID() {
        return questID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getRequirement() {
        return requirement;
    }

    public String getRewardtype() {
        return rewardtype;
    }

    public int getQuestDifficulty() {
        return questDifficulty;
    }

    public double getQuestrewardexperience() {
        return questrewardexperience;
    }
}
