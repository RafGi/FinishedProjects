package repo;

import data.Hero;
import java.util.List;

public interface HeroRepo {


    List<Hero> getHeroes();

    List<Hero> getHeroesByPlayer(int playerid);

    Hero getHeroById(int id);

    List<Hero> getHeroByCost(int cost);

    List<Hero> getHeroByReqLevel(int reqLvl);

    void addHero(Hero hero);

    void deleteHero(Hero hero);

}
