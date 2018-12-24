package repo;

import data.Stat;

import java.util.List;


public interface StatRepo {

    Stat getStatsByPlayerId(int id);

    List<Stat> getStatByGamesplayed(int gamesplayed);

    List<Stat> getStatByWins(String wins);

    List<Stat> getStatByHighscore(int highscore);

    List<Stat> getStatsByAmountoflines(int amountoflines);

    List<Stat> getStatsByAmountoftetris(int amountoftetris);

    List<Stat> getStatsByWinstreak(int winstreak);

    void addStat(Stat stat);

    void deleteStat(Stat stat);
}
