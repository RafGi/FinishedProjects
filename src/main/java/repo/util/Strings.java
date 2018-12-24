package repo.util;

public class Strings {

    //player
    public static final String SELECT_PLAYERS = "SELECT * FROM player";
    public static final String SELECT_PLAYER_BY_ID = "SELECT * FROM player WHERE playerid = ?";
    public static final String SELECT_PLAYER_BY_NAME = "SELECT * FROM player WHERE username = ?";
    public static final String SELECT_PLAYER_BY_MAIL = "SELECT * FROM player WHERE email = ?";
    public static final String ADD_PLAYER = "insert into player(playerid,username,password,email,currency,experience)"
        + " values (?,?,?,?,0,0)";
    public static final String DELETE_PLAYER = "delete from player where playerid = ?";
    public static final String UPDATE_PLAYER_STATS = "UPDATE PLAYER "
            + "SET currency = ?, experience = ?"
            + "WHERE playerid = ?";
    public static final String SELECT_PLAYERS_BY_LEVEL = "SELECT * FROM player order by experience";
    public static final String SELECT_PLAYERS_BY_HIGHSCORE = "SELECT * FROM player inner join stats "
            + "on player.playerid = stats.playerid order by highscore";

    //heroes
    public static final String SELECT_HEROES = "SELECT * FROM heroes";
    public static final String SELECT_HEROES_BY_PLAYER = "SELECT * "
                    + "FROM heroes "
                    + "WHERE heroid NOT IN ( SELECT heroid FROM playerheroes WHERE playerid = ?)";
    public static final String SELECT_HEROES_NOT_FROM_PLAYER = "SELECT * "
                    + "FROM heroes "
                    + "WHERE heroid NOT IN ( SELECT heroid FROM playerheroes WHERE playerid = ?)";
    public static final String SELECT_HERO_BY_ID = "SELECT * FROM heroes WHERE heroid = ?";
    public static final String SELECT_HERO_BY_COST = "SELECT * FROM heroes WHERE cost = ?";
    public static final String SELECT_HERO_BY_REQLVL = "SELECT * FROM heroes WHERE reqlvl = ?";
    public static final String SELECT_HERO_BY_ERA = "SELECT * FROM heroes inner join eras on heroes.eraid = eras.eraid WHERE eras.eraname = ?";
    public static final String ADD_HERO = "INSERT INTO heroes(name,cost,avatar,eraid,requirdlevel) values (?,?,?,?,?)";
    public static final String DELETE_HERO = "DELETE FROM heroes where heroid = ?";

    //unlockable
    public static final String SELECT_UNLOCKABLES = "SELECT * FROM unlockable";
    public static final String SELECT_UNLOCKABLES_BY_ID = "SELECT * FROM unlockable WHERE unlockableid = ?";
    public static final String SELECT_UNLOCKABLES_BY_TYPE = "SELECT * FROM unlockable WHERE type = ?";
    public static final String SELECT_UNLOCKABLES_BY_PLAYER = "SELECT unlockable.* "
                    + "FROM playerunlockables WHERE playerid = ?"
                    + "INNER JOIN unlockable ON playerunlockables.unlockableid = unlockable.unlockableid";
    public static final String ADD_UNLOCKABLE = "INSERT INTO unlockable(unlockabletype,unlockablename,unlockablemedia,unlockablecost) values (?,?,?,?)";
    public static final String DELETE_UNLOCKABLE = "DELETE FROM unlockable WHERE unlockableid = ?";

    //era
    public static final String SELECT_ERAS = "SELECT * FROM Era";
    public static final String SELECT_ERA_BY_ID = "SELECT * FROM Era WHERE eraid = ?";
    public static final String SELECT_ERA_BY_NAME = "SELECT * FROM Era WHERE eraname = ?";
    public static final String ADD_ERA = "INSERT INTO era(eraname,erabackground,eramusic) values (?,?,?)";
    public static final String DELETE_ERA = "DELETE FROM hero WHERE eraid = ?";

    //events
    public static final String SELECT_EVENTS = "SELECT * FROM events";
    public static final String SELECT_EVENTS_BY_COST = "SELECT * FROM events WHERE cost = ?";
    public static final String SELECT_EVENT_BY_ID = "SELECT * FROM events WHERE eventid = ?";
    public static final String ADD_EVENT = "INSERT into events(eventid,effect,eventname,cost) values (?,?,?,?)";
    public static final String DELETE_EVENT = "DELETE FROM events WHERE eventid = ?";

    //blocks
    public static final String SELECT_BLOCKS = "SELECT * FROM blocks";
    public static final String SELECT_BLOCKS_BY_ID = "SELECT * FROM blocks WHERE blockid = ?";
    public static final String SELECT_BLOCKS_BY_COLOR = "SELECT * FROM blocks WHERE blockcolor = ?";
    public static final String SELECT_BLOCKS_BY_VALUE = "SELECT * FROM blocks WHERE blockvalue = ?";

    //Stat
    public static final String SELECT_STATS_BY_PLAYERID = "SELECT * FROM stats WHERE playerid = ?";
    public static final String SELECT_STATS_BY_GAMESPLAYED = "SELECT * FROM stats WHERE gamesplayed = ?";
    public static final String SELECT_STATS_BY_WINS = "SELECT * FROM stats WHERE wins = ?";
    public static final String SELECT_STATS_BY_HIGHSCORE = "SELECT * FROM stats WHERE hghscore = ?";
    public static final String SELECT_STATS_BY_AMOUNTOFLINES = "SELECT * FROM stats WHERE amountoflines = ?";
    public static final String SELECT_STATS_BY_AMOUNTOFTETRIS = "SELECT * FROM stats WHERE amountoftetris = ?";
    public static final String SELECT_STATS_BY_WINSTREAK = "SELECT * FROM stats WHERE winstreak = ?";
    public static final String ADD_STAT = "INSERT into"
            + " stats(playerid,gamesplayed,wins,highscore,amountoflines,amountoftetris,winstreak)"
            + " values (?,?,?,?,?,?,?)";
    public static final String DELETE_STAT = "DELETE FROM stats WHERE statid = ?";

    //quests
    public static final String SELECT_QUESTS = "SELECT * FROM quests";
    public static final String SELECT_QUESTS_BY_ID = "SELECT * FROM quests WHERE questid = ?";
    public static final String SELECT_QUESTS_BY_NAME = "SELECT * FROM quests WHERE questname = ?";
    public static final String SELECT_QUESTS_BY_DESCRIPTION = "SELECT * FROM quests WHERE questdescription = ?";
    public static final String SELECT_QUESTS_BY_REQUIREMENT = "SELECT * FROM quests WHERE questrequirement = ?";
    public static final String ADD_QUEST = "INSERT into"
            + " quests(questid,questname,questdescription,requirement,questrewardexp)"
            + " values (?,?,?,?,?)";
    public static final String DELETE_QUEST = "DELETE FROM quests WHERE questid = ?";


    public static final String SELECT_ACHIEVEMENTS_BY_PLAYERID = "SELECT * FROM"
            + " achievements INNER JOIN playerachievements"
            + " ON playerachievements.achievementid = "
            + "achievements.achievementid WHERE playerachievements.playerid = ?";
}
