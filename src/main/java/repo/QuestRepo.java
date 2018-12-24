package repo;

import data.Quest;

import java.util.List;

public interface QuestRepo {
    List<Quest> getAllQuests();

    Quest getQuestById(int id);

    Quest getQuestsByName(String name);

    Quest getQuestByDescription(String description);

    Quest getQuestByRequirement(String requirement);

    void addQuest(Quest quest);

    void deleteQuest(Quest quest);
}
