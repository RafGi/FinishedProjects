package repo;

import data.Achievement;

public interface AchievementRepo {
    Achievement getAchievementsByPlayerId(int playerid);
}
