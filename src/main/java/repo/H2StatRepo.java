package repo;

import data.Stat;
import jdbcinteractor.JDBCInteractor;
import org.pmw.tinylog.Logger;
import repo.util.Strings;
import util.TetrisException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class H2StatRepo implements StatRepo {

    private int statId = 0;

    private static final String FIELD_PLAYERID = "playerid";
    private static final String FIELD_GAMESPLAYED = "gamesplayed";
    private static final String FIELD_WINS = "wins";
    private static final String FIELD_HIGHSCORE = "highscore";
    private static final String FIELD_AMOUNTOFLINES = "amountoflines";
    private static final String FIELD_AMOUNTOFTETRIS = "amountoftetris";
    private static final String FIELD_WINSTREAK = "winstreak";

    public Stat getStatsByPlayerId(int id) {
        try (Connection con = JDBCInteractor.getConnection();
             PreparedStatement prep = con.prepareStatement(Strings.SELECT_STATS_BY_PLAYERID)) {

            prep.setInt(1, id);

            try (ResultSet rs = prep.executeQuery()) {
                Stat stat = null;
                while (rs.next()) {
                    stat = this.resultset2Stat(rs);

                }
                return stat;
            }
        } catch (SQLException e) {
            throw new TetrisException("Couldn't retrieve stats by playerid", e);
        }
    }

    @Override
    public List<Stat> getStatByGamesplayed(int gamesplayed) {
        try (PreparedStatement prep = JDBCInteractor.getConnection()
                .prepareStatement(Strings.SELECT_STATS_BY_GAMESPLAYED)) {

            prep.setInt(1, gamesplayed);
            ResultSet rs = prep.executeQuery();
            List<Stat> allStatsByGamesplayed = new ArrayList<>();

            while (rs.next()) {
                Stat stat = this.resultset2Stat(rs);
                allStatsByGamesplayed.add(stat);
            }

            prep.close();
            return allStatsByGamesplayed;

        } catch (SQLException ex) {
            Logger.debug(ex.getStackTrace());
            throw new TetrisException("Couldn't retrieve allStatsByGamesplayed", ex);
        }
    }

    @Override
    public List<Stat> getStatByWins(String wins) {
        try (PreparedStatement prep = JDBCInteractor.getConnection()
                .prepareStatement(Strings.SELECT_STATS_BY_WINS)) {

            prep.setString(1, wins);
            ResultSet rs = prep.executeQuery();
            List<Stat> allStatsByWins = new ArrayList<>();

            while (rs.next()) {
                Stat stat = this.resultset2Stat(rs);
                allStatsByWins.add(stat);
            }

            prep.close();
            return allStatsByWins;

        } catch (SQLException ex) {
            Logger.debug(ex.getStackTrace());
            throw new TetrisException("Couldn't retrieve allStatsByWins", ex);
        }
    }

    @Override
    public List<Stat> getStatByHighscore(int highscore) {
        try (PreparedStatement prep = JDBCInteractor.getConnection()
                .prepareStatement(Strings.SELECT_STATS_BY_HIGHSCORE)) {

            prep.setInt(1, highscore);
            ResultSet rs = prep.executeQuery();
            List<Stat> allStatsByHighscore = new ArrayList<>();

            while (rs.next()) {
                Stat stat = this.resultset2Stat(rs);
                allStatsByHighscore.add(stat);
            }

            prep.close();
            return allStatsByHighscore;

        } catch (SQLException ex) {
            Logger.debug(ex.getStackTrace());
            throw new TetrisException("Couldn't retrieve allStatsByHighscore", ex);
        }
    }

    @Override
    public List<Stat> getStatsByAmountoflines(int amountoflines) {
        try (PreparedStatement prep = JDBCInteractor.getConnection()
                .prepareStatement(Strings.SELECT_STATS_BY_AMOUNTOFLINES)) {

            prep.setInt(1, amountoflines);
            ResultSet rs = prep.executeQuery();
            List<Stat> allStatsByAmountoflines = new ArrayList<>();

            while (rs.next()) {
                Stat stat = this.resultset2Stat(rs);
                allStatsByAmountoflines.add(stat);
            }

            prep.close();
            return allStatsByAmountoflines;

        } catch (SQLException ex) {
            Logger.debug(ex.getStackTrace());
            throw new TetrisException("Couldn't retrieve allStatsByAmountoflines", ex);
        }
    }

    @Override
    public List<Stat> getStatsByAmountoftetris(int amountoftetris) {
        try (PreparedStatement prep = JDBCInteractor.getConnection()
                .prepareStatement(Strings.SELECT_STATS_BY_AMOUNTOFTETRIS)) {

            prep.setInt(1, amountoftetris);
            ResultSet rs = prep.executeQuery();
            List<Stat> allStatsByAmountoftetris = new ArrayList<>();

            while (rs.next()) {
                Stat stat = this.resultset2Stat(rs);
                allStatsByAmountoftetris.add(stat);
            }

            prep.close();
            return allStatsByAmountoftetris;

        } catch (SQLException ex) {
            Logger.debug(ex.getStackTrace());
            throw new TetrisException("Couldn't retrieve allStatsByAmountoftetris", ex);
        }
    }

    @Override
    public List<Stat> getStatsByWinstreak(int winstreak) {
        try (PreparedStatement prep = JDBCInteractor.getConnection()
                .prepareStatement(Strings.SELECT_STATS_BY_WINSTREAK)) {

            prep.setInt(1, winstreak);
            ResultSet rs = prep.executeQuery();
            List<Stat> allStatsByWinstreak = new ArrayList<>();

            while (rs.next()) {
                Stat stat = this.resultset2Stat(rs);
                allStatsByWinstreak.add(stat);
            }

            prep.close();
            return allStatsByWinstreak;

        } catch (SQLException ex) {
            Logger.debug(ex.getStackTrace());
            throw new TetrisException("Couldn't retrieve allStatsByWinstreak", ex);
        }
    }

    @Override
    public void addStat(Stat stat) {
        try (Connection con = JDBCInteractor.getConnection();
             PreparedStatement prep = con.prepareStatement(Strings.ADD_STAT)) {
            prep.setInt(1, statId);
            prep.setInt(2, stat.getGamesPlayed());
            prep.setInt(3, stat.getWins());
            prep.setInt(4, stat.getHighscore());
            prep.setInt(5, stat.getAmountOfLines());
            prep.setInt(6, stat.getAmountOfTetris());
            prep.setInt(7, stat.getWinStreak());

            prep.executeUpdate();
            statId++;
        } catch (SQLException e) {
            throw new TetrisException("Unable to add stat", e);
        }
    }

    @Override
    public void deleteStat(Stat stat) {
        try (Connection con = JDBCInteractor.getConnection();
             PreparedStatement prep = con.prepareStatement(Strings.DELETE_STAT)) {
            prep.setInt(1, stat.getPlayerId());
            prep.executeUpdate();
        } catch (SQLException e) {
            throw new TetrisException("Unable to delete stat from database", e);
        }
    }


    private Stat resultset2Stat(ResultSet rs) throws SQLException {
        int playerid = rs.getInt(FIELD_PLAYERID);
        int gamesplayed = rs.getInt(FIELD_GAMESPLAYED);
        int wins = rs.getInt(FIELD_WINS);
        int highscore = rs.getInt(FIELD_HIGHSCORE);
        int amountoflines = rs.getInt(FIELD_AMOUNTOFLINES);
        int amountoftetris = rs.getInt(FIELD_AMOUNTOFTETRIS);
        int winstreak = rs.getInt(FIELD_WINSTREAK);

        return new Stat(playerid, gamesplayed, wins, highscore, amountoflines, amountoftetris, winstreak);
    }
}
