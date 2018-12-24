package repo;

import data.Player;
import jdbcinteractor.JDBCInteractor;
import org.pmw.tinylog.Logger;
import repo.util.Strings;
import util.TetrisException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class H2PlayerRepo implements PlayerRepo {

    private static final String FIELD_ID = "playerid";
    private static final String FIELD_USERNAME = "username";
    private static final String FIELD_PWD = "password";
    private static final String FIELD_EMAIL = "email";
    private static final String FIELD_CURRENCY = "currency";
    private static final String FIELD_XP = "experience";
    private static final String FIELD_AVATAR = "avatar";
    private static final String FIELD_CLAN = "clanid";
    private static final String FIELD_HIGHSCORE = "highscore";
    private int playerid = 2;

    H2PlayerRepo() {
    }

    @Override
    public Player getPlayerById(int id) {
        try (Connection con = JDBCInteractor.getConnection();
             PreparedStatement prep = con.prepareStatement(Strings.SELECT_PLAYER_BY_ID)) {
            prep.setInt(1, id);

            try (ResultSet rs = prep.executeQuery()) {
                Player player = null;
                while (rs.next()) {
                    player = this.resultset2Player(rs);

                }
                return player;
            }
        } catch (SQLException e) {
            throw new TetrisException("Couldn't retrieve player by ID", e);
        }
    }

    @Override
    public Player getPlayerByName(String name) {
        try (Connection con = JDBCInteractor.getConnection()) {
            PreparedStatement prep = con.prepareStatement(Strings.SELECT_PLAYER_BY_NAME);
            prep.setString(1, name);
            ResultSet rs = prep.executeQuery();
            Player player = null;
            while (rs.next()) {
                player = this.resultset2Player(rs);
            }
            con.close();
            return player;

        } catch (SQLException ex) {
            throw new TetrisException("Couldn't retrieve player by name", ex);
        }
    }

    @Override
    public List<Player> getAllPlayers() {

        try (Statement stmt = JDBCInteractor.getConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery(Strings.SELECT_PLAYERS);
            List<Player> allPlayers = new ArrayList<>();
            while (rs.next()) {
                Player player = this.resultset2Player(rs);
                allPlayers.add(player);
            }
            stmt.close();
            return allPlayers;

        } catch (SQLException ex) {
            Logger.warn("Error populating db {}", ex.getLocalizedMessage());
            Logger.debug(ex.getStackTrace());
            throw new TetrisException("Couldn't retrieve all players", ex);
        }

    }

    @Override
    public Player getPlayerByMail(String email) {
        try (Connection con = JDBCInteractor.getConnection()) {
            PreparedStatement prep = con.prepareStatement(Strings.SELECT_PLAYER_BY_MAIL);
            prep.setString(1, email);
            ResultSet rs = prep.executeQuery();
            Player player = null;
            while (rs.next()) {
                player = this.resultset2Player(rs);
            }
            con.close();
            return player;

        } catch (SQLException ex) {
            throw new TetrisException("Couldn't retrieve player by name", ex);
        }
    }

    @Override
    public void addPlayer(Player player) {
        try (Connection con = JDBCInteractor.getConnection();
             PreparedStatement prep = con.prepareStatement(Strings.ADD_PLAYER)) {
            prep.setInt(1, playerid);
            prep.setString(2, player.getUsername());
            prep.setString(3, player.getPassword());
            prep.setString(4, player.getEmail());

            prep.executeUpdate();
            playerid++;
        } catch (SQLException e) {
            throw new TetrisException("Unable to add player", e);
        }

    }

    @Override
    public void deletePlayer(Player player) {

        try (Connection con = JDBCInteractor.getConnection();
             PreparedStatement prep = con.prepareStatement(Strings.DELETE_PLAYER)) {
            prep.setInt(1, player.getId());
            prep.executeUpdate();
        } catch (SQLException e) {
            throw new TetrisException("Unable to delete player from database", e);
        }

    }

    @Override
    public List<Player> getplayersBylevel() {
        try (Statement stmt = JDBCInteractor.getConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery(Strings.SELECT_PLAYERS_BY_LEVEL);
            List<Player> allPlayers = new ArrayList<>();
            while (rs.next()) {
                Player player = this.resultset2Player(rs);
                allPlayers.add(player);
            }
            stmt.close();
            System.out.println("allplayers:" + allPlayers);
            return allPlayers;

        } catch (SQLException ex) {
            Logger.warn("Error populating db {}", ex.getLocalizedMessage());
            Logger.debug(ex.getStackTrace());
            throw new TetrisException("Couldn't retrieve all players by level", ex);
        }
    }

    @Override
    public List<Player> getplayersByhighscore() {
        try (Statement stmt = JDBCInteractor.getConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery(Strings.SELECT_PLAYERS_BY_HIGHSCORE);
            List<Player> allPlayers = new ArrayList<>();
            while (rs.next()) {
                Player player = this.resultset2PlayerHighscore(rs);
                allPlayers.add(player);
            }
            stmt.close();
            return allPlayers;

        } catch (SQLException ex) {
            Logger.warn("Error populating db {}", ex.getLocalizedMessage());
            Logger.debug(ex.getStackTrace());
            throw new TetrisException("Couldn't retrieve all players by highscore", ex);
        }
    }

    private Player resultset2Player(ResultSet rs) throws SQLException {
        int id = rs.getInt(FIELD_ID);
        String username = rs.getString(FIELD_USERNAME);
        double currency = rs.getDouble(FIELD_CURRENCY);
        String avatar = rs.getString(FIELD_AVATAR);
        int clanId = rs.getInt(FIELD_CLAN);
        double xp = rs.getDouble(FIELD_XP);
        String email = rs.getString("email");
        String password = rs.getString("password");

        return new Player(id, username, currency, xp, avatar, clanId, email, password);
    }

    private Player resultset2PlayerHighscore(ResultSet rs) throws SQLException {
        int id = rs.getInt(FIELD_ID);
        String username = rs.getString(FIELD_USERNAME);
        double currency = rs.getDouble(FIELD_CURRENCY);
        double xp = rs.getDouble(FIELD_XP);
        double highscore = rs.getDouble(FIELD_HIGHSCORE);
        String email = rs.getString("email");
        String password = rs.getString("password");

        return new Player(id, username, currency, xp, email, password, highscore);
    }
}
