package jdbcinteractor;

public class Strings {

    public final static String SELECT_PLAYERS = "SELECT * FROM player";
    public final static String SELECT_PLAYER_BY_ID = "SELECT * FROM player WHERE playerid = ?";
    public final static String SELECT_PLAYER_BY_NAME = "SELECT * FROM player WHERE username = ?";
    public final static String ADD_PLAYER = "insert into player(username,password,email,currency,experience)"
        + " values (?,?,?,0,0)";
    public final static String DELETE_PLAYER = "delete from player where playerid = ?";

    //heroes
    public final static String SELECT_HEROES = "SELECT * FROM heroes";
    public final static String SELECT_HEROES_BY_PLAYER =
            "SELECT hero.* "
                    + "FROM playerheroes WHERE playerid = ?"
                    + "INNER JOIN hero ON playerheroes.heroid = hero.heroid";
    public final static String SELECT_HERO_BY_ID = "SELECT * FROM hero WHERE heroid = ?";
    public final static String SELECT_HERO_BY_COST = "SELECT * FROM hero WHERE cost = ?";
    public final static String SELECT_HERO_BY_REQLVL = "SELECT * FROM hero WHERE reqlvl = ?";
    public final static String ADD_HERO = "INSERT INTO hero(name,cost,avatar,eraid,requirdlevel) values (?,?,?,?,?)";
    public final static String DELETE_HERO = "DELETE FROM hero where heroid = ?";

    //unlockable
    public final static String SELECT_UNLOCKABLES = "SELECT * FROM unlockable";
    public final static String SELECT_UNLOCKABLES_BY_ID = "SELECT * FROM unlockable WHERE unlockableid = ?";
    public final static String SELECT_UNLOCKABLES_BY_TYPE = "SELECT * FROM unlockable WHERE type = ?";
    public final static String SELECT_UNLOCKABLES_BY_PLAYER =
            "SELECT unlockable.* "
                    + "FROM playerunlockables WHERE playerid = ?"
                    + "INNER JOIN unlockable ON playerunlockables.unlockableid = unlockable.unlockableid";


}
